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
import pe.edu.continental.poo.taskapp.entidades.Etiqueta;

/**
 *
 * @author aoviedo
 */
public class EtiquetaJpaController implements Serializable {

    public EtiquetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Etiqueta etiqueta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(etiqueta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Etiqueta etiqueta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            etiqueta = em.merge(etiqueta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = etiqueta.getId();
                if (findEtiqueta(id) == null) {
                    throw new NonexistentEntityException("The etiqueta with id " + id + " no longer exists.");
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
            Etiqueta etiqueta;
            try {
                etiqueta = em.getReference(Etiqueta.class, id);
                etiqueta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The etiqueta with id " + id + " no longer exists.", enfe);
            }
            em.remove(etiqueta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Etiqueta> findEtiquetaEntities() {
        return findEtiquetaEntities(true, -1, -1);
    }

    public List<Etiqueta> findEtiquetaEntities(int maxResults, int firstResult) {
        return findEtiquetaEntities(false, maxResults, firstResult);
    }

    private List<Etiqueta> findEtiquetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Etiqueta.class));
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

    public Etiqueta findEtiqueta(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Etiqueta.class, id);
        } finally {
            em.close();
        }
    }

    public int getEtiquetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Etiqueta> rt = cq.from(Etiqueta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
