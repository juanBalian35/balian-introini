/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class AdminProductosController implements Initializable {

    @FXML
    private JFXComboBox BoxBuscarPor;
    
    @FXML
    private JFXButton botonBuscar;
    
    @FXML
    private JFXTextField TFBuscar;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
      @FXML
  private  void accionBoxBuscarPor(ActionEvent event) {
       Object seleccion = BoxBuscarPor.getValue();
       TFBuscar.disableProperty().setValue(Boolean.FALSE);
       TFBuscar.promptTextProperty().setValue((String) seleccion);
       botonBuscar.disableProperty().setValue(Boolean.FALSE);

    }
  
  
    @FXML
    void cambioTexto(ActionEvent event) {
      
    }
    
}
