/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.continental.poo.taskapp;

import jakarta.persistence.EntityManagerFactory;
import pe.edu.continental.poo.taskapp.controladores.UsuarioJpaController;
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
    }
}
