package ecoshop.backend;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class Envase {
    
    private String nombre;
    private int id;
    private String imagen;
    private String categoria;
    private int contadorReuso;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getContadorReuso() {
        return contadorReuso;
    }

    public void setContadorReuso(int contadorReuso) {
        this.contadorReuso = contadorReuso;
    }
   

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
}
