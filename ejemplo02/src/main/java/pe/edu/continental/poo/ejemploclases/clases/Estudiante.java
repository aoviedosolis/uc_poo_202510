package pe.edu.continental.poo.ejemploclases.clases;

import java.util.ArrayList;

public class Estudiante {
    private String nombre;

    public Estudiante(){
        this.nombre = "";
    }
    
    public Estudiante(String nombre, Direccion direccion) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
