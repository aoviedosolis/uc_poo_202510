/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.taskapp.singleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author aoviedo
 */
public class EntityManagerSingleton {
    private static EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "MPU";
    private static EntityManagerSingleton instance;
    
    private EntityManagerSingleton(){
        if(emf == null)
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }
    
    public static EntityManagerSingleton getInstance(){
        if(instance == null){
            synchronized (EntityManagerSingleton.class) {
                if(instance == null)
                    instance = new EntityManagerSingleton();
            }
        }
        
        return instance;
    }
    
    public EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
    public void closeEntityManager(EntityManager em){
        if(em!=null && em.isOpen())
            em.close();
    }
    
    public void closeEntityManagerFactory(EntityManagerFactory emf){
        if(emf!=null && emf.isOpen())
            emf.close();
    }
}
