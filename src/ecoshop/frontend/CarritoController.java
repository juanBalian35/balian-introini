/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import ecoshop.backend.CarritoSingleton;
import ecoshop.backend.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
        CarritoSingleton carritoSingleton = CarritoSingleton.getInstancia();
        ArrayList<Producto> productos = carritoSingleton.getProductos();
        for (int i = 0; i < productos.size(); i++) {
            Pane panelNuevo;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PanelProductoCarrito.fxml")); 
                panelNuevo = fxmlLoader.load();
                PanelProductoCarritoController controller = fxmlLoader.<PanelProductoCarritoController>getController();
                controller.setProducto(productos.get(i));
                COL1.getChildren().add(panelNuevo);
            } catch (IOException ex) {
                Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
