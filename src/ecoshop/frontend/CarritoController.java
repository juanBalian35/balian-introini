/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */

public class CarritoController implements Initializable {
@FXML
private VBox COL1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        for (int i = 0; i < 7; i++) {
            Pane panelNuevo;
            try {
                panelNuevo = FXMLLoader.load(getClass().getResource("PanelProductoCarrito.fxml"));
                COL1.getChildren().add(panelNuevo);

            } catch (IOException ex) {
                Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }    
    
}
