/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.taskapp.controladores;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import pe.edu.continental.poo.taskapp.controladores.exceptions.NonexistentEntityException;
import pe.edu.continental.poo.taskapp.entidades.Tarea;
import pe.edu.continental.poo.taskapp.entidades.TareaEtiqueta;

/**
 *
 * @author aoviedo
 */
public class TareaEtiquetaJpaController implements Serializable {

    public TareaEtiquetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TareaEtiqueta tareaEtiqueta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarea tarea = tareaEtiqueta.getTarea();
            if (tarea != null) {
                tarea = em.getReference(tarea.getClass(), tarea.getId());
                tareaEtiqueta.setTarea(tarea);
            }
            em.persist(tareaEtiqueta);
            if (tarea != null) {
                tarea.getEtiquetas().add(tareaEtiqueta);
                tarea = em.merge(tarea);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TareaEtiqueta tareaEtiqueta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TareaEtiqueta persistentTareaEtiqueta = em.find(TareaEtiqueta.class, tareaEtiqueta.getId());
            Tarea tareaOld = persistentTareaEtiqueta.getTarea();
            Tarea tareaNew = tareaEtiqueta.getTarea();
            if (tareaNew != null) {
                tareaNew = em.getReference(tareaNew.getClass(), tareaNew.getId());
                tareaEtiqueta.setTarea(tareaNew);
            }
            tareaEtiqueta = em.merge(tareaEtiqueta);
            if (tareaOld != null && !tareaOld.equals(tareaNew)) {
                tareaOld.getEtiquetas().remove(tareaEtiqueta);
                tareaOld = em.merge(tareaOld);
            }
            if (tareaNew != null && !tareaNew.equals(tareaOld)) {
                tareaNew.getEtiquetas().add(tareaEtiqueta);
                tareaNew = em.merge(tareaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tareaEtiqueta.getId();
                if (findTareaEtiqueta(id) == null) {
                    throw new NonexistentEntityException("The tareaEtiqueta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TareaEtiqueta tareaEtiqueta;
            try {
                tareaEtiqueta = em.getReference(TareaEtiqueta.class, id);
                tareaEtiqueta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tareaEtiqueta with id " + id + " no longer exists.", enfe);
            }
            Tarea tarea = tareaEtiqueta.getTarea();
            if (tarea != null) {
                tarea.getEtiquetas().remove(tareaEtiqueta);
                tarea = em.merge(tarea);
            }
            em.remove(tareaEtiqueta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TareaEtiqueta> findTareaEtiquetaEntities() {
        return findTareaEtiquetaEntities(true, -1, -1);
    }

    public List<TareaEtiqueta> findTareaEtiquetaEntities(int maxResults, int firstResult) {
        return findTareaEtiquetaEntities(false, maxResults, firstResult);
    }

    private List<TareaEtiqueta> findTareaEtiquetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TareaEtiqueta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TareaEtiqueta findTareaEtiqueta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TareaEtiqueta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTareaEtiquetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TareaEtiqueta> rt = cq.from(TareaEtiqueta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
