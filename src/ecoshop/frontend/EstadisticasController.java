/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXMasonryPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class EstadisticasController implements Initializable {
@FXML
    private AnchorPane pane;


    @FXML
    private FlowPane masonryPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        for (int i = 0; i < 6; i++) {
             try {
            Pane panelNuevo;
            panelNuevo = FXMLLoader.load(getClass().getResource("PanelProducto.fxml"));
            masonryPane.getChildren().add(panelNuevo);
        } catch (IOException ex) {
            Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
          
    }    
    
}
