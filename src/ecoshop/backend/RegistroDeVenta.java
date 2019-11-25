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
    private Usuario usuario;
    private double total;
    private Date fechaVenta; 
    private Preventa preventa;

    public RegistroDeVenta(Preventa preventa, Date fechaVenta) {
        this.preventa = preventa;
        this.fechaVenta = fechaVenta;
    }

    public Preventa getPreventa() {
        return preventa;
    }

    public void setPreventa(Preventa preventa) {
        this.preventa = preventa;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
