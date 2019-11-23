/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Juanchi
 */
public class ImagenesAuxiliar {
    private static final String ruta = "imagenes/";
    private static final String extension = ".jpg";
    
    public static String guardarImagen(Image image){
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        final String rutaArchivo = ruta + uuid + extension;
        try{
            File file = new File(rutaArchivo);

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            BufferedImage imageRGB = new BufferedImage(
                bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.OPAQUE);
            Graphics2D graphics = imageRGB.createGraphics();
            graphics.drawImage(bufferedImage, 0, 0, null);

            ImageIO.write(imageRGB, "jpg", file);
        }
        catch(IOException e){
            return null;
        }
        
        return rutaArchivo;
    }
    
    public static Image abrirImagen(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(new Stage());  
        
        return archivoAImagen(file);
    }
    
    public static Image archivoAImagen(File file){
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException ex) {
        }
        
        return null;
    }
}
