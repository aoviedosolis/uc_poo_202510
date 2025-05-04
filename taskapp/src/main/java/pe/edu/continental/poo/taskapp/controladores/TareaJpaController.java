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
import pe.edu.continental.poo.taskapp.entidades.TareaEtiqueta;
import java.util.ArrayList;
import java.util.List;
import pe.edu.continental.poo.taskapp.controladores.exceptions.NonexistentEntityException;
import pe.edu.continental.poo.taskapp.entidades.Tarea;

/**
 *
 * @author aoviedo
 */
public class TareaJpaController implements Serializable {

    public TareaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tarea tarea) {
        if (tarea.getEtiquetas() == null) {
            tarea.setEtiquetas(new ArrayList<TareaEtiqueta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TareaEtiqueta> attachedEtiquetas = new ArrayList<TareaEtiqueta>();
            for (TareaEtiqueta etiquetasTareaEtiquetaToAttach : tarea.getEtiquetas()) {
                etiquetasTareaEtiquetaToAttach = em.getReference(etiquetasTareaEtiquetaToAttach.getClass(), etiquetasTareaEtiquetaToAttach.getId());
                attachedEtiquetas.add(etiquetasTareaEtiquetaToAttach);
            }
            tarea.setEtiquetas(attachedEtiquetas);
            em.persist(tarea);
            for (TareaEtiqueta etiquetasTareaEtiqueta : tarea.getEtiquetas()) {
                Tarea oldTareaOfEtiquetasTareaEtiqueta = etiquetasTareaEtiqueta.getTarea();
                etiquetasTareaEtiqueta.setTarea(tarea);
                etiquetasTareaEtiqueta = em.merge(etiquetasTareaEtiqueta);
                if (oldTareaOfEtiquetasTareaEtiqueta != null) {
                    oldTareaOfEtiquetasTareaEtiqueta.getEtiquetas().remove(etiquetasTareaEtiqueta);
                    oldTareaOfEtiquetasTareaEtiqueta = em.merge(oldTareaOfEtiquetasTareaEtiqueta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tarea tarea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tarea persistentTarea = em.find(Tarea.class, tarea.getId());
            List<TareaEtiqueta> etiquetasOld = persistentTarea.getEtiquetas();
            List<TareaEtiqueta> etiquetasNew = tarea.getEtiquetas();
            List<TareaEtiqueta> attachedEtiquetasNew = new ArrayList<TareaEtiqueta>();
            for (TareaEtiqueta etiquetasNewTareaEtiquetaToAttach : etiquetasNew) {
                etiquetasNewTareaEtiquetaToAttach = em.getReference(etiquetasNewTareaEtiquetaToAttach.getClass(), etiquetasNewTareaEtiquetaToAttach.getId());
                attachedEtiquetasNew.add(etiquetasNewTareaEtiquetaToAttach);
            }
            etiquetasNew = attachedEtiquetasNew;
            tarea.setEtiquetas(etiquetasNew);
            tarea = em.merge(tarea);
            for (TareaEtiqueta etiquetasOldTareaEtiqueta : etiquetasOld) {
                if (!etiquetasNew.contains(etiquetasOldTareaEtiqueta)) {
                    etiquetasOldTareaEtiqueta.setTarea(null);
                    etiquetasOldTareaEtiqueta = em.merge(etiquetasOldTareaEtiqueta);
                }
            }
            for (TareaEtiqueta etiquetasNewTareaEtiqueta : etiquetasNew) {
                if (!etiquetasOld.contains(etiquetasNewTareaEtiqueta)) {
                    Tarea oldTareaOfEtiquetasNewTareaEtiqueta = etiquetasNewTareaEtiqueta.getTarea();
                    etiquetasNewTareaEtiqueta.setTarea(tarea);
                    etiquetasNewTareaEtiqueta = em.merge(etiquetasNewTareaEtiqueta);
                    if (oldTareaOfEtiquetasNewTareaEtiqueta != null && !oldTareaOfEtiquetasNewTareaEtiqueta.equals(tarea)) {
                        oldTareaOfEtiquetasNewTareaEtiqueta.getEtiquetas().remove(etiquetasNewTareaEtiqueta);
                        oldTareaOfEtiquetasNewTareaEtiqueta = em.merge(oldTareaOfEtiquetasNewTareaEtiqueta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tarea.getId();
                if (findTarea(id) == null) {
                    throw new NonexistentEntityException("The tarea with id " + id + " no longer exists.");
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
            Tarea tarea;
            try {
                tarea = em.getReference(Tarea.class, id);
                tarea.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarea with id " + id + " no longer exists.", enfe);
            }
            List<TareaEtiqueta> etiquetas = tarea.getEtiquetas();
            for (TareaEtiqueta etiquetasTareaEtiqueta : etiquetas) {
                etiquetasTareaEtiqueta.setTarea(null);
                etiquetasTareaEtiqueta = em.merge(etiquetasTareaEtiqueta);
            }
            em.remove(tarea);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tarea> findTareaEntities() {
        return findTareaEntities(true, -1, -1);
    }

    public List<Tarea> findTareaEntities(int maxResults, int firstResult) {
        return findTareaEntities(false, maxResults, firstResult);
    }

    private List<Tarea> findTareaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarea.class));
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

    public Tarea findTarea(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    public int getTareaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tarea> rt = cq.from(Tarea.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
