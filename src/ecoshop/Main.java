package ecoshop;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class Main extends Application  {
   
   
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/ecoshop/MainInterfaz.fxml"));
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/RobotoCondensed-Regular.ttf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/Roboto-Thin.ttf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/Roboto-Light.ttf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/Roboto-Regular.ttf").toExternalForm(), 12);
        
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/Roboto-Medium.ttf").toExternalForm(), 12);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
       
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     /**
     * Initializes the controller class.
     */
   
    
}
