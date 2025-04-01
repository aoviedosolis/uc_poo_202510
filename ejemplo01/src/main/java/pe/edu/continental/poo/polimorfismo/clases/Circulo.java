/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.polimorfismo.clases;

/**
 *
 * @author aoviedo
 */
public class Circulo extends Figura{
    private double radio;

    public Circulo() {
        super();
        this.radio = 0;
    }

    public Circulo(double radio, String color) {
        super(color);
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }
    
    @Override
    public double calcularArea(){
        return 3.14f * radio * radio;
    }
    
    @Override
    public void dibujar(){
        System.out.println("Dibujando un circulo de color: " + getColor() + ", radio: " + radio);
    }
    
}
