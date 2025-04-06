/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.continental.poo.ejemploclases.clases;

/**
 *
 * @author aoviedo
 */
public class Vehiculo {
    private String marca;
    private Motor motor;

    public Vehiculo() {
        this.marca="";
        this.motor=null;
    }

    public Vehiculo(String marca,Motor motor) {
        this.marca = marca;
        this.motor = motor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    
    
}
