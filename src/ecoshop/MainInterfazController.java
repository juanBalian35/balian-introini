/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author agustinintroini
 */
public class MainInterfazController implements Initializable{
    
     @FXML
    private JFXButton botonAdmin;

    @FXML
    private JFXButton botonCliente;
    
    @FXML
    private Pane Carga;

   
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
     @FXML
    void clickBotonAdmin(MouseEvent event) throws InterruptedException {
      
       
        try {
         Parent root = FXMLLoader.load(getClass().getResource("/ecoshop/frontend/Interfaz.fxml"));
       
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
        
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
     @FXML
    void clickBotonCliente(MouseEvent event) throws InterruptedException {
      
       
        try {
         Parent root = FXMLLoader.load(getClass().getResource("/ecoshop/frontend/InterfazUsuario.fxml"));
       
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
        
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    }


