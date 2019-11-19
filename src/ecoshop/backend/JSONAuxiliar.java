/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import org.json.simple.JsonObject;

/**
 *
 * @author Juan Balian
 * @author Agustín Introini
 * 
 */
public class JSONAuxiliar {
    static final String RUTA = "src\\ecoshop\\backend";
    
    public static boolean guardar(JsonObject object){
        File archivoJson = new File(Paths.get(RUTA).toAbsolutePath().toString(), "datos.json");
        
        try (FileWriter writer = new FileWriter(archivoJson)) {
            writer.write(object.toJson());
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
