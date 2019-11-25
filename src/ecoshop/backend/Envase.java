package ecoshop.backend;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

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
    
    public int sumarUnoContadorReuso(){
        return ++this.contadorReuso;
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
    
    public static Envase parsearEntrySet(Set<Map.Entry<String, String>> entrySet){
        Envase envase = new Envase();
        for(Map.Entry<String,String> entry : entrySet){
            switch(entry.getKey().toLowerCase()){
                case "nombre":
                    envase.setNombre(entry.getValue());
                    break;
                case "id":
                    envase.setId(Integer.parseInt(entry.getValue()));
                    break;
                case "categoria":
                    envase.setCategoria(entry.getValue());
                    break;
                case "imagen":
                    envase.setImagen(entry.getValue());
                    break; 
                case "contadorreuso":
                    int i = Integer.parseInt(entry.getValue());
                    envase.setContadorReuso(i);
                    break;
                default:
                    throw new IllegalArgumentException("Argumento invalido " + entry.getKey());
            }
        }
        
        return envase;
    }
}
