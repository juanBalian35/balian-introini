package ecoshop.backend;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class Preventa {
   private ArrayList<Producto> productos;
   private Date fecha;
   private Date fechaARetirar;
   private Usuario usuario;
   private Envase envase;
   private String idVenta;
   private PuntoDeVenta puntoDeVentaRetiro;

    public Preventa(ArrayList<Producto> productos, Date fecha, Usuario usuario) {
        this.productos = productos;
        this.fecha = fecha;
        this.usuario = usuario;
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
    
    
    
    
}
