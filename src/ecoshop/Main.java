package ecoshop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/ecoshop/frontend/PanelProducto.fxml"));
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/RobotoCondensed-Regular.ttf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/Roboto-Thin.ttf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("frontend/estilos/fuentes/Roboto-Light.ttf").toExternalForm(), 12);

        Parent root = FXMLLoader.load(getClass().getResource("/ecoshop/frontend/Interfaz.fxml"));
        
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
    
}
