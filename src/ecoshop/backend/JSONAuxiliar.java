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
    static final String RUTA = "src/ecoshop/backend";
    
    /**
     * @param JSONAware JSONObject o JSONArray a ser escrito
     * @param String nombre del archivo, depende del tipo de dato el archivo al que ira
     * @return booleano que indica si pudo escribir correctamente
    */
    public static boolean escribir(JSONAware object, String nombre){
        File archivoJson = new File(nombre + ".json");
        
        try (FileWriter writer = new FileWriter(archivoJson)) {
            writer.write(object.toJSONString());
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    /**
     * Si ya existe un archivo, agrega al archivo el objeto deseado, y si no
     * existe el archivo, lo crea con el nuevo dato.
     * 
     * @param String nombre del archivo, depende del tipo de dato el archivo al que ira.
     * @return JSONAware que sera el contenido del archivo. (JSONObject o JSONArray o null)
    */
    public static JSONAware leer(String nombre) throws FileNotFoundException, IOException, ParseException{
        JSONParser parser  = new JSONParser();
        File archivoJson = new File(nombre + ".json");
        
        Object obj = parser.parse(new FileReader(archivoJson));
        
        return obj == null ? null : (JSONAware)obj;
    }
    
    /**
     * Si ya existe un archivo, agrega al archivo el objeto deseado, y si no
     * existe el archivo, lo crea con el nuevo dato.
     * 
     * @param JSONObject JSONObject a agregar a un archivo
     * @param String nombre del archivo, depende del tipo de dato el archivo al que ira
     * @return booleano que indica si pudo agregar correctamente
    */
    public static boolean agregar(JSONObject object, String nombre){
        JSONArray jsonArray = null;
        try{
            jsonArray = (JSONArray)leer(nombre);
        }
        catch (FileNotFoundException ex) {
            jsonArray = new JSONArray();
        } 
        catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return false;
        }
            
        jsonArray.add(object);
        return escribir(jsonArray, nombre);
    }
    
    /**
     * 
     * @param Object Objeto a comparar en el archivo json
     * @param String columna
     * @param String nombre del archivo, depende del tipo de dato el archivo al que ira
     * @return booleano que indica si existe en el archivo `ruta+nombre+".json"` 
     *          el dato que tenga en la columna `columna` el objeto `objeto`
    */
    public static boolean existe(Object objeto, String columna, String nombre){
        return conseguirConColumna(objeto, columna, nombre) != null;
    }
    
    /**
     * 
     * @param Object Objeto a comparar en el archivo json
     * @param String columna
     * @param String nombre del archivo, depende del tipo de dato el archivo al que ira
     * @return JSONObject que se encuentra en el archivo `ruta+nombre+".json"` y 
     *         contiene en columna `columna`  el objeto `objeto`
    */
    public static JSONObject conseguirConColumna(Object objeto, String columna, String nombre){
        JSONArray jsonArray = null;
        try{
            jsonArray = (JSONArray)leer(nombre);
        }
        catch (FileNotFoundException ex) {
            return null;
        } 
        catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return null;
        }
        
        for (int i = 0; i < jsonArray.size(); i++) {
            if(((JSONObject)jsonArray.get(i)).get(columna).equals(objeto))
                return (JSONObject)jsonArray.get(i);
        }
        
        return null;
    }
    
    /**
     * Si ya existe un archivo, agrega al archivo el objeto deseado, y si no
     * existe el archivo, lo crea con el nuevo dato.
     * 
     * @param 
     * @param 
     * @return booleano que indica si pudo borrar correctamente
    */
    public static boolean borrar(){
        return true;
    }
}
