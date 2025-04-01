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

    public int size(){
        int cant = 0;
        for (int i = 0; i < this.capacidad; i++) {
            if(this.lista[i] instanceof Multimedia)
                cant++;
        }
        return cant;
    }
    
    public boolean add(Multimedia m){
        int pos = getUltimaPosicion();
        if(pos<this.capacidad){
            this.lista[pos] = m;
            return true;
        }else
            return false;
    }
    
    private int getUltimaPosicion() {
        int pos = 0;
        while (pos < this.capacidad && this.lista[pos]!=null) {
            pos++;
        }
        return pos;
    }
    
    public Multimedia get(int pos){
        return this.lista[pos];
    }
    
    @Override
    public String toString() {
        String resultado = "ListaMultimedia{";
        
        for (int i = 0; i<this.capacidad;i++) {
            if(this.lista[i] instanceof Multimedia)
                resultado += "-> " + this.lista[i].toString() + ",\n";
        }
        return resultado + "}";
    }
    
}
