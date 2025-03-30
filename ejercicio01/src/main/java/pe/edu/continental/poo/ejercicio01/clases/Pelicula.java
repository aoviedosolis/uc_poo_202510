package pe.edu.continental.poo.ejercicio01.clases;

public class Pelicula extends Multimedia{
    private String actorPrincipal;
    private String actrizPrincipal;

    public Pelicula() {
        super();
        this.actorPrincipal = "";
        this.actrizPrincipal = "";
    }

    public Pelicula(String actorPrincipal, String actrizPrincipal, String titulo, String autor, String formato, float duracion) {
        super(titulo, autor, formato, duracion);
        this.actorPrincipal = actorPrincipal;
        this.actrizPrincipal = actrizPrincipal;
    }

    public String getActorPrincipal() {
        return actorPrincipal;
    }

    public void setActorPrincipal(String actorPrincipal) {
        this.actorPrincipal = actorPrincipal;
    }

    public String getActrizPrincipal() {
        return actrizPrincipal;
    }

    public void setActrizPrincipal(String actrizPrincipal) {
        this.actrizPrincipal = actrizPrincipal;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + getTitulo() + ", autor=" + getAutor() + ", formato=" + getFormato() + ", duracion=" + getDuracion() + "actorPrincipal=" + actorPrincipal + ", actrizPrincipal=" + actrizPrincipal + '}';
    }
    
    
    
}
