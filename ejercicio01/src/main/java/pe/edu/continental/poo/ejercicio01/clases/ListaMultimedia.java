package pe.edu.continental.poo.ejercicio01.clases;

public class ListaMultimedia {
    private int capacidad;
    private Multimedia[] lista;

    public ListaMultimedia(int capacidad) {
        this.capacidad = capacidad;
        this.lista = new Multimedia[this.capacidad];
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Multimedia[] getLista() {
        return lista;
    }

    public void setLista(Multimedia[] lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        String resultado = "ListaMultimedia{";
        
        for (Multimedia multimedia : lista) {
            resultado += multimedia.toString() + "\n";
        }
        return resultado;
    }
    
    
    
}
