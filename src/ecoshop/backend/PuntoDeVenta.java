package ecoshop.backend;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class PuntoDeVenta {
    private String direccion;
    private String nombre;

    public PuntoDeVenta(String direccion, String nombre) {
        this.direccion = direccion;
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
