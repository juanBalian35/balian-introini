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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    @FXML
    private AnchorPane paneVistaProducto;
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
        // TODO: esto está mal
    int cantidadProductos=8;
    if((cantidadProductos%3)==0){
        cargarVistaProductos(COL1, cantidadProductos/3);
        cargarVistaProductos(COL2, cantidadProductos/3);
        cargarVistaProductos(COL3,cantidadProductos/3);
    }else if((cantidadProductos%2)==0){
         cargarVistaProductos(COL1, (cantidadProductos/3)+1);
        cargarVistaProductos(COL2, (cantidadProductos/3)+1);
        cargarVistaProductos(COL3,cantidadProductos/3);
    }else{
        cargarVistaProductos(COL1, (cantidadProductos/3)+1);
        cargarVistaProductos(COL2, cantidadProductos/3);
        cargarVistaProductos(COL3,cantidadProductos/3);
    }
        
        
        

    }

    private void cargarVistaProductos(VBox col, int cantidad) {

        for (int i = 0; i < cantidad; i++) {
            Pane panelNuevo;
            try {
                panelNuevo = FXMLLoader.load(getClass().getResource("PanelProducto.fxml"));
                col.getChildren().add(panelNuevo);

            } catch (IOException ex) {
                Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
