package ecoshop.backend;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class RegistroDeVenta {
    
    private ArrayList<Producto> productos;
    private Date fecha; 
    private Usuario usuario;
    private Envase envase;
    
    public RegistroDeVenta(ArrayList<Producto> productos, Date fecha, Usuario usuario, Envase envase) {
        this.productos = productos;
        this.fecha = fecha;
        this.usuario = usuario;
        this.envase = envase;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }
   
    
}
