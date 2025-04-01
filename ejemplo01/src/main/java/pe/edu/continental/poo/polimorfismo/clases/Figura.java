/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.polimorfismo.clases;

/**
 *
 * @author aoviedo
 */
public class Figura {
    private String color;
    
    public Figura(){
        this.color = "";
    }
    
    public Figura(String color){
        this.color=color;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public double calcularArea(){
        System.out.println("Calculando el area de una figura generica (metodo base)");
        return 0;
    }
    
    public void dibujar(){
        System.out.println("Dibujando una figura de color " + color);
    }
}
