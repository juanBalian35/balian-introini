/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXSlider;
import ecoshop.backend.JSONAuxiliar;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class TiendaController implements Initializable {
    private static final String NOMBRE_JSON = "productos";
    
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
        try{
            ArrayList<Producto> p = JSONAuxiliar.procesarArchivo(NOMBRE_JSON, Producto::parsearEntrySet);
            int columna = 1;
            for(int i = 0; i < p.size(); ++i){
                cargarVistaProductos(columna == 1 ? COL1 : (columna == 2 ? COL2 : COL3), p.get(i));
                
                columna = columna >= 2 ? 0 : columna + 1;
            }
        }
        catch(Exception e){
            e.printStackTrace();;
        }
    }

    private void cargarVistaProductos(VBox col, Producto producto) {
        Pane panelNuevo;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PanelProducto.fxml")); 
            panelNuevo = fxmlLoader.load();
            PanelProductoController controller = fxmlLoader.<PanelProductoController>getController();
            controller.setProducto(producto);
            col.getChildren().add(panelNuevo);
        } catch (IOException ex) {
            Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
