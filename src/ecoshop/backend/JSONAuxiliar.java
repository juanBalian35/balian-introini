/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Juan Balian
 * @author Agustín Introini
 * 
 */
public class JSONAuxiliar {
    static final String RUTA = "src\\ecoshop\\backend";
    
    public static boolean escribir(JSONAware object, String nombre){
        File archivoJson = new File(Paths.get(RUTA).toAbsolutePath().toString(), nombre + ".json");
        
        try (FileWriter writer = new FileWriter(archivoJson)) {
            writer.write(object.toJSONString());
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public static boolean agregar(JSONObject object, String nombre){
        JSONParser parser  = new JSONParser();
        File archivoJson = new File(Paths.get(RUTA).toAbsolutePath().toString(), nombre + ".json");
        
        Object obj = null;
        try{
            obj = parser.parse(new FileReader(archivoJson));
        }
        catch (FileNotFoundException ex) {
        } 
        catch (IOException | ParseException ex2) {
            ex2.printStackTrace();
            return false;
        }
        
        JSONArray jsonArray = obj == null ? new JSONArray() : (JSONArray)obj;
            
        jsonArray.add(object);
        return escribir(jsonArray, nombre);
    }
    
    public static boolean borrar(){
        return true;
    }
}
