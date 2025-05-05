/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.continental.poo.taskapp;

import jakarta.persistence.EntityManagerFactory;
import pe.edu.continental.poo.taskapp.controladores.ListaTareasJpaController;
import pe.edu.continental.poo.taskapp.controladores.TareaJpaController;
import pe.edu.continental.poo.taskapp.controladores.UsuarioJpaController;
import pe.edu.continental.poo.taskapp.entidades.ListaTareas;
import pe.edu.continental.poo.taskapp.entidades.Tarea;
import pe.edu.continental.poo.taskapp.entidades.Usuario;
import pe.edu.continental.poo.taskapp.iu.FrmPrincipal;
import pe.edu.continental.poo.taskapp.singleton.EntityManagerSingleton;

/**
 *
 * @author aoviedo
 */
public class Taskapp {

    public static void main(String[] args) {
         /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        inicializarDatos();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    private static void inicializarDatos() {
        EntityManagerFactory emf = EntityManagerSingleton.getInstance().getEntityManagerFactory();
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        
        Usuario u1 = new Usuario();
        u1.setUsuario("aoviedo");
        u1.setClave("123");
        u1.setNombres("Alex");
        u1.setApPaterno("Oviedo");
        u1.setApMaterno("Solis");
        u1.setCorreo("aoviedo@continental.edu.pe");
        
        Usuario u2 = new Usuario();
        u2.setUsuario("jperez");
        u2.setClave("123");
        u2.setNombres("Juan");
        u2.setApMaterno("Perez");
        
        ujc.create(u1);
        ujc.create(u2);
        
        ListaTareasJpaController ltjc = new ListaTareasJpaController(emf);
        
        ListaTareas lt1 = new ListaTareas();
        lt1.setNombre("Lista de tareas 1");
        lt1.setDescripcion("Mi lista");
        lt1.setUsuario(u1);
        
        ListaTareas lt2 = new ListaTareas();
        lt2.setNombre("Lista de tareas 2");
        lt2.setDescripcion("Otra lista de tareas");
        lt2.setUsuario(u1);
        
        ltjc.create(lt1);
        ltjc.create(lt2);
        
        TareaJpaController tjc = new TareaJpaController(emf);
        
        Tarea t1 = new Tarea();
        t1.setNombre("Tarea 1");
        t1.setDescripcion("Tarea 1");
        t1.setLista(lt1);
        
        Tarea t2 = new Tarea();
        t2.setNombre("Tarea 2");
        t2.setDescripcion("Tarea 2");
        t2.setLista(lt1);
        
        Tarea t3 = new Tarea();
        t3.setNombre("Tarea 3");
        t3.setDescripcion("Tarea 3");
        t3.setLista(lt2);
        
        tjc.create(t1);
        tjc.create(t2);
        tjc.create(t3);
    }
}
