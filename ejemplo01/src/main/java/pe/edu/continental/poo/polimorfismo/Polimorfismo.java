/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package pe.edu.continental.poo.polimorfismo;

import pe.edu.continental.poo.polimorfismo.clases.Circulo;
import pe.edu.continental.poo.polimorfismo.clases.Figura;
import pe.edu.continental.poo.polimorfismo.clases.Rectangulo;

/**
 *
 * @author aoviedo
 */
public class Polimorfismo {

    public static void main(String[] args) {
        // Creamos un arreglo de figuras
        Figura[] figuras = new Figura[3];
        
        figuras[0] = new Figura("Verde"); // Creamos una figura
        figuras[1] = new Rectangulo(10,20, "Azul"); // Creado un rectangulo
        figuras[2] = new Circulo(5,"Amarillo"); // Creado un circulo
        
        for (Figura figura : figuras) {
            System.out.println("\nProcesando figura de color: " + figura.getColor());
            figura.dibujar();
            System.out.println("Area de la figura: " + figura.calcularArea());
        }
    }
}
