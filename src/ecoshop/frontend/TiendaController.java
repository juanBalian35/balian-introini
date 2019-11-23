/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXSlider;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */



public class TiendaController implements Initializable {

    @FXML
    private JFXSlider slider;
      @FXML
    private Pane paneProductos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      Pane  panelNuevo ;
        try {
            panelNuevo = FXMLLoader.load(getClass().getResource("VistaDeProductos.fxml"));
                            paneProductos.getChildren().add(panelNuevo);

        } catch (IOException ex) {
            Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
