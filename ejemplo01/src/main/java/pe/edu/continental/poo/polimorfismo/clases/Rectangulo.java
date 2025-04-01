/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.polimorfismo.clases;

/**
 *
 * @author aoviedo
 */
public class Rectangulo extends Figura{
    private double base;
    private double altura;

    public Rectangulo() {
        super();
        this.base = 0;
        this.altura = 0;
    }

    public Rectangulo(double base, double altura, String color) {
        super(color);
        this.base = base;
        this.altura = altura;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
    
    @Override
    public double calcularArea(){
        return base * altura;
    }
    
    @Override
    public void dibujar(){
        System.out.println("Dibujando un rectangulo de color: " + getColor() + ", base: " + base + ", altura: "+altura);
    }
}
