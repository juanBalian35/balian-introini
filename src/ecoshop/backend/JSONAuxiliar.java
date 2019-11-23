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
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Function;
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
     * Convierte el archivo al Objeto JSON (JSONObject o JSONArray) que corresponda.
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
        return conseguirConColumna(objeto, columna, nombre,true) != null;
    }
    
    /**
     * 
     * @param objeto a comparar en el archivo json
     * @param columna
     * @param nombre nombre del archivo, depende del tipo de dato el archivo al que ira
     * @param estricto si es verdadero entonces la igualdad debe de ser
     *                exacta, sino con que sean parecidas es suficiente. (Ejemplo,
     *                se puede buscar en un string de manear no estricta un caracter)
     * @return JSONObject que se encuentra en el archivo `ruta+nombre+".json"` y 
     *         contiene en columna `columna`  el objeto `objeto`
    */
    public static JSONArray conseguirConColumna(Object objeto, String columna, String nombre, boolean estricto){
        JSONArray contenidoArchivo = null;
        try{
            contenidoArchivo = (JSONArray)leer(nombre);
        }
        catch (FileNotFoundException ex) {
            return null;
        } 
        catch (IOException | ParseException ex) {
            ex.printStackTrace();
            return null;
        }
        
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < contenidoArchivo.size(); ++i) {
            String contenidoColumna = (String) (((JSONObject) contenidoArchivo.get(i)).get(columna));

            if ((estricto && contenidoColumna.equals(objeto)) || (!estricto && contenidoColumna.contains((CharSequence)objeto))) {
                jsonArray.add(contenidoArchivo.get(i));
            }
        }
        
        return jsonArray;
    }
    
    /**
     * Si ya existe un archivo, agrega al archivo el objeto deseado, y si no
     * existe el archivo, lo crea con el nuevo dato.
     * 
     * @param 
     * @param 
     * @return booleano que indica si pudo borrar correctamente
    */
    public static boolean borrar(String nombre, JSONObject objeto){
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
            
        jsonArray.remove(objeto);
        return escribir(jsonArray, nombre);
    }
    
    public static <T> ArrayList<T> procesarArchivo(String nombreArchivo,
            Function<Set<Map.Entry<String, String>>,T> convertidor){
        ArrayList<T> al = new ArrayList<>();
        try{
            JSONArray a = (JSONArray)JSONAuxiliar.leer(nombreArchivo);
            al = procesarJSONArray(a,convertidor);
        }
        catch(Exception e){ }
        
        return al;
    }
    
    public static <T> ArrayList<T> procesarJSONArray(JSONArray array,
            Function<Set<Map.Entry<String, String>>,T> convertidor){
        ArrayList<T> al = new ArrayList<>();
        for(int i = 0; i < array.size(); ++i){
            JSONObject objeto = (JSONObject) array.get(i);
            al.add((T) convertidor.apply(objeto.entrySet()));
        }
        
        return al;
    }
}
