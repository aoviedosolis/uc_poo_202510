/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.continental.poo.ejercicio01;

import pe.edu.continental.poo.ejercicio01.clases.ListaMultimedia;
import pe.edu.continental.poo.ejercicio01.clases.Pelicula;

/**
 *
 * @author aoviedo
 */
public class Ejercicio01 {

    public static void main(String[] args) {
        ListaMultimedia lm = new ListaMultimedia(10);
        
        lm.add(new Pelicula("Jean Pierre Jeunet", "Mathieu Kassowitz", "Amelie", "AndreyTautou", "mp4", 120));
        lm.add(new Pelicula("James Stewar", "Kim Novak", "Vertigo", "Alfred Hitchcock", "mp4", 120));
        lm.add(new Pelicula("Cary Grant", "Eva Mary Saint", "Con la muerte en los talones", "Alfred Hitchcock", "mp4", 120));
        
        System.out.println(lm.toString());
    }
}
