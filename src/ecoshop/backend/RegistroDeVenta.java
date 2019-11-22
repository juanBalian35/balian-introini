package ecoshop.backend;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class RegistroDeVenta {
    
    private Preventa preventa;
    private Date fechaVenta; 
    private ArrayList<Producto> productos;
    private double total;
    private Usuario usuario;
    private Envase envase;
    private String idVenta;

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
  
    
 
    
}
