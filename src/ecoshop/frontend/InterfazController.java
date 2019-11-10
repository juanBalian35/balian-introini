package ecoshop.frontend;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class InterfazController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    double x, y;
     
    @FXML
    private void dragged(MouseEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setX(event.getScreenX() - x);
         stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }
    private void cambiarBotones(Button b){
        if(b.getStyleClass().contains("botonNoSeleccionado")){
            b.getStyleClass().remove("botonNoSeleccionado");
            b.getStyleClass().add("botonSeleccionado");
        }
        else{
            b.getStyleClass().remove("botonSeleccionado");
            b.getStyleClass().add("botonNoSeleccionado");
        }
    }

    @FXML
    private void click_btn1(MouseEvent event) {
        Button tb = (Button)event.getSource();
        
        cambiarBotones(tb);
    }

    @FXML
    private void click_btn2(MouseEvent event) {
        Button tb = (Button)event.getSource();
        
        cambiarBotones(tb);
    }

    @FXML
    private void click_btn3(MouseEvent event) {
        Button tb = (Button)event.getSource();
        cambiarBotones(tb);
    }

    @FXML
    private void click_btn4(MouseEvent event) {
        Button tb = (Button)event.getSource();
        cambiarBotones(tb);
    }

    @FXML
    private void click_btn5(MouseEvent event) {
        Button tb = (Button)event.getSource();
        cambiarBotones(tb);
    }

    @FXML
    private void click_btn6(MouseEvent event) {
        Button tb = (Button)event.getSource();
        cambiarBotones(tb);
    }
}
