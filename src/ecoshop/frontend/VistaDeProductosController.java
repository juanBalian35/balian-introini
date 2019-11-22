/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import static javafx.animation.Interpolator.EASE_BOTH;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class VistaDeProductosController implements Initializable {
@FXML
    private AnchorPane pane;
@FXML
    private VBox COL1;
@FXML
    private VBox COL2;

    @FXML
    private VBox COL3;
  
        @FXML
    private ScrollPane scrollPane;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           // JFXMasonryPane masonryPane = new JFXMasonryPane();
        for (int i = 0; i < 3; i++) {
            Pane panelNuevo;
            try {
                panelNuevo = FXMLLoader.load(getClass().getResource("PanelProducto.fxml"));
                COL1.getChildren().add(panelNuevo);
            
            } catch (IOException ex) {
                Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         for (int i = 0; i < 3; i++) {
            Pane panelNuevo;
            try {
                panelNuevo = FXMLLoader.load(getClass().getResource("PanelProducto.fxml"));
                COL2.getChildren().add(panelNuevo);
            
            } catch (IOException ex) {
                Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
          for (int i = 0; i < 3; i++) {
            Pane panelNuevo;
            try {
                panelNuevo = FXMLLoader.load(getClass().getResource("PanelProducto.fxml"));
                COL3.getChildren().add(panelNuevo);
            
            } catch (IOException ex) {
                Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
        
    }
    
    
}
