package ecoshop.backend;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
    private String nombre;
    private ArrayList<Envase> envases;
   
    public Producto(){
        this(-1,-1,"","","","");
    }
    
    public Producto(int id, double precio, String nombre, String material, String descripcion, String imagen) {
        this.id = id;
        this.precio = precio;
        this.material = material;
        this.descripcion = descripcion;
        this.imagen= imagen;
        this.envases= new ArrayList();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public static Producto parsearEntrySet(Set<Map.Entry<String, String>> entrySet){
        Producto producto = new Producto();
        for(Map.Entry<String,String> entry : entrySet){
            switch(entry.getKey().toLowerCase()){
                case "nombre":
                    producto.setNombre(entry.getValue());
                    break;
                case "id":
                    producto.setId(Integer.parseInt(entry.getValue()));
                    break;
                case "precio":
                    producto.setPrecio(Double.parseDouble(entry.getValue()));
                    break;
                case "material":
                    producto.setMaterial(entry.getValue());
                    break;
                case "descripcion":
                    producto.setDescripcion(entry.getValue());
                    break;
                case "imagen":
                    producto.setImagen(entry.getValue());
                    break; 
                //case "envases":
                    //producto.setEnvases(entry.getValue());
                default:
                    // TODO: Preguntar si es necesario siempre poner un default?
            }
        }
        
        return producto;
    }
}
