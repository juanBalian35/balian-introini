package ecoshop.backend;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class PuntoDeVenta {
    private int id;
    private String nombre;
    private String departamento;
    private String ciudad;
    private String calle;
    private String numero;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDireccion(){
        return calle + " " + numero + ", " + ciudad + ", " + departamento;
    }
    
    public static PuntoDeVenta parsearEntrySet(Set<Map.Entry<String, String>> entrySet){
        PuntoDeVenta puntoDeVenta = new PuntoDeVenta();
        for(Map.Entry<String,String> entry : entrySet){
            switch(entry.getKey().toLowerCase()){
                case "nombre":
                    puntoDeVenta.setNombre(entry.getValue());
                    break;
                case "id":
                    puntoDeVenta.setId(Integer.parseInt(entry.getValue()));
                    break;
                case "calle":
                    puntoDeVenta.setCalle(entry.getValue());
                    break;
                case "departamento":
                    puntoDeVenta.setDepartamento(entry.getValue());
                    break; 
                case "ciudad":
                    puntoDeVenta.setCiudad(entry.getValue());
                    break; 
                case "numero":
                    puntoDeVenta.setNumero(entry.getValue());
                    break; 
                default:
                    throw new IllegalArgumentException("Argumento invalido en entry set");
            }
        }
        
        return puntoDeVenta;
    }
}
