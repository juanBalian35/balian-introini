package ecoshop.backend;

import java.util.ArrayList;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class Producto {
    private int id;
    private double precio;
    private String material;
    private String descripcion;
    private String imagen;
    private ArrayList<Envase> envases;
    
   
    public Producto(int id, double precio, String material, String descripcion, String imagen) {
        this.id = id;
        this.precio = precio;
        this.material = material;
        this.descripcion = descripcion;
        this.imagen= imagen;
        this.envases= new ArrayList();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Envase> getEnvases() {
        return envases;
    }

    public void setEnvases(ArrayList<Envase> envases) {
        this.envases = envases;
    }
    
}
