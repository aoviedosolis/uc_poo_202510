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
import java.util.ArrayList;
import java.util.List;
import pe.edu.continental.poo.taskapp.controladores.exceptions.NonexistentEntityException;
import pe.edu.continental.poo.taskapp.entidades.ListaTareas;

/**
 *
 * @author aoviedo
 */
public class ListaTareasJpaController implements Serializable {

    public ListaTareasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaTareas listaTareas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaTareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaTareas listaTareas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaTareas = em.merge(listaTareas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = listaTareas.getId();
                if (findListaTareas(id) == null) {
                    throw new NonexistentEntityException("The listaTareas with id " + id + " no longer exists.");
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
            ListaTareas listaTareas;
            try {
                listaTareas = em.getReference(ListaTareas.class, id);
                listaTareas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaTareas with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaTareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaTareas> findListaTareasEntities() {
        return findListaTareasEntities(true, -1, -1);
    }

    public List<ListaTareas> findListaTareasEntities(int maxResults, int firstResult) {
        return findListaTareasEntities(false, maxResults, firstResult);
    }

    private List<ListaTareas> findListaTareasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaTareas.class));
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

    public ListaTareas findListaTareas(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaTareas.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaTareasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaTareas> rt = cq.from(ListaTareas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<ListaTareas> findListaTareasByUsuario(Long id){
        ArrayList<ListaTareas> listas = new ArrayList<>(findListaTareasEntities());
        ArrayList<ListaTareas> lst = new ArrayList<>();
        
        for (ListaTareas lista : listas) {
            if(lista.getUsuario().getId() == id){
                lst.add(lista);
            }
        }
        
        return lst;
    }
    
    public ListaTareas getListaTareas(Long id,String nombre){
        ArrayList<ListaTareas> listas = new ArrayList<>(findListaTareasEntities());
        ListaTareas resultado = null;
        
        for (ListaTareas lista : listas) {
            if(lista.getUsuario().getId().equals(id) && lista.getNombre().equalsIgnoreCase(nombre)){
                resultado = lista;
                break;
            }
        }
        
        return resultado;
    }
}
