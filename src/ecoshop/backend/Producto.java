package ecoshop.backend;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
    private ArrayList<Integer> envases;
   
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

    public ArrayList<Integer> getEnvases() {
        return envases;
    }

    public void setEnvases(ArrayList<Integer> envases) {
        this.envases = envases;
    }
    
    public static <T> Producto parsearEntrySet(Set<Map.Entry<String, T>> entrySet){
        Producto producto = new Producto();
        for(Map.Entry<String,T> entry : entrySet){
            switch(entry.getKey().toLowerCase()){
                case "nombre":
                    producto.setNombre((String)entry.getValue());
                    break;
                case "id":
                    producto.setId(Integer.parseInt((String)entry.getValue()));
                    break;
                case "precio":
                    producto.setPrecio(Double.parseDouble((String)entry.getValue()));
                    break;
                case "material":
                    producto.setMaterial((String)entry.getValue());
                    break;
                case "descripcion":
                    producto.setDescripcion((String)entry.getValue());
                    break;
                case "imagen":
                    producto.setImagen((String)entry.getValue());
                    break; 
                case "envases":
                    JSONArray envasesArray = (JSONArray)entry.getValue();
                    ArrayList<Integer> al = new ArrayList<>();
                    for(int i = 0; i < envasesArray.size(); ++i){
                        JSONObject o = (JSONObject)envasesArray.get(i);
                        al.add(Integer.parseInt((String)o.get("id")));
                    }
                    
                    producto.setEnvases(al);
                    break;
                default:
                    throw new IllegalArgumentException("Argumento invalido en entry set");
            }
        }
        
        return producto;
    }
    
    @Override
    public boolean equals(Object other){
        if (other == null) 
            return false;
        
        if (other == this) 
            return true;
        
        if (!(other instanceof Producto))
            return false;
        
        Producto otro = (Producto)other;
        return getId() == otro.getId();
    }
}
