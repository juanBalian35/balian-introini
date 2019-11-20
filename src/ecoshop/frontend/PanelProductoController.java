/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */


public class PanelProductoController implements Initializable {
  @FXML
  private Pane paneImagen;
  @FXML
  private Label lblDescripcion;
  
     @FXML
    private Pane paneFondo;

    @FXML
    private Pane paneSecundario;

  
      @FXML
    private JFXButton botonVerMas;
   private StringProperty labelString = new SimpleStringProperty();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        labelString.setValue("Semillas de chia traidas de china (china: pais del conintente asiatico, de donde provienen los CHINOS) son muy ricas. Semillas de chia traidas de china (china: pais del conintente asiatico, de donde provienen los CHINOS) son muy ricas . Semillas de chia traidas de china (china: pais del conintente asiatico, de donde provienen los CHINOS) son muy ricas "
                + "Semillas de chia traidas de china (china: pais del conintente asiatico, de donde provienen los CHINOS) son muy ricas."
                + "Semillas de chia traidas de china (china: pais del conintente asiatico, de donde provienen los CHINOS) son muy ricas."
                + "Semillas de chia traidas de china (china: pais del conintente asiatico, de donde provienen los CHINOS) son muy ricas.");
        lblDescripcion.textProperty().bind(labelString);
        
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("recursos/imagenes/chia.jpg")),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          new BackgroundSize(100, 100,true, true, true,true));
        //then you set to your node
        paneImagen.setBackground(new Background(myBI));

    }    
    
    @FXML
    private void BotonApretado(MouseEvent event) {
        paneFondo.setPrefHeight(430);
        // Hay que agrandar la ventana a tamaño pane fondo + tamaño sombra de ventana
        paneFondo.getScene().getWindow().setHeight(445);
        paneSecundario.setVisible(true);
    }
    
}
