/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class RegistrarVentaController implements Initializable {
    private static final String NOMBRE_JSON = "productos";
    
    @FXML JFXButton botonConfirmarCompra;
    @FXML JFXComboBox BoxBuscarPor;
    @FXML JFXTextField TFBuscar;
    
    @FXML TableView<Producto> tableViewBuscar;
    @FXML private TableColumn<Producto, String> columnId;
    @FXML private TableColumn<Producto, String> columnNombre;
    @FXML private TableColumn<Producto, String> columnPrecio;
    @FXML private TableColumn<Producto, String> columnEnvases;
    
    @FXML private JFXButton botonAgregarProducto1;
    @FXML private Pane paneDetallesVenta;
    @FXML private JFXButton botonBuscar;
    
    @FXML TableView<Producto> tableViewProductos;
    @FXML private TableColumn<Producto, String> columnId1;
    @FXML private TableColumn<Producto, String> columnNombre1;
    @FXML private TableColumn<Producto, String> columnPrecioUnit;
    @FXML private TableColumn<Producto, String> columnEnvases1;
    @FXML private TableColumn<Producto, String> columnCantidad;
    @FXML private TableColumn<Producto, String> columnPrecioTotal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewBuscar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            botonAgregarProducto1.setDisable(newSelection == null);
        });
        
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
        
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnEnvases.setCellValueFactory(new PropertyValueFactory<>("envases"));
        
        columnId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPrecioUnit.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnEnvases1.setCellValueFactory(new PropertyValueFactory<>("envases"));
        //columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));
        //columnCantidad.setCellValueFactory(features -> new SimpleStringProperty(features.getValue().getDireccion()));
        
        actualizarDatos();
    }    
    
    private void actualizarDatos(){
        ArrayList<Producto> productos = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, Producto::parsearEntrySet);
        tableViewBuscar.getItems().setAll(productos);
    }
    
    @FXML
    private  void accionBoxBuscarPor(ActionEvent event) {
        String seleccion = ((String)BoxBuscarPor.getValue()).toLowerCase();
        if("todos".equals(seleccion)){
            TFBuscar.disableProperty().setValue(true);
            TFBuscar.promptTextProperty().setValue(null);
            TFBuscar.clear();
            actualizarDatos();
        }
        else{
            TFBuscar.disableProperty().setValue(false);
            TFBuscar.promptTextProperty().setValue((String) seleccion);
        }
    }
    
    @FXML
    private void clickBotonBuscar(ActionEvent event){
        String columna = ((String)BoxBuscarPor.getValue()).toLowerCase();
        
        JSONArray resultado = JSONAuxiliar.conseguirConColumna(TFBuscar.getText(), columna, NOMBRE_JSON, columna.equals("id"));
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        for(int i = 0; i < resultado.size(); ++i){
            productos.add(Producto.parsearEntrySet(((JSONObject)resultado.get(i)).entrySet()));
        }
        
        tableViewBuscar.getItems().setAll(productos);
    }
    
    @FXML
    private void clickBotonAgregarProducto(ActionEvent event){
        paneDetallesVenta.setDisable(false);
        
        Producto producto = tableViewBuscar.getSelectionModel().getSelectedItem();
        tableViewProductos.getItems().add(producto);
    }
    
    @FXML
    void botonConfirmarCompra(ActionEvent event) {
    }
    
    /*private class C{
        private int id;
        private double precio;
        private String material;
        private String descripcion;
        private String imagen;
        private String nombre;
        private ArrayList<Integer> envases;
        private int cantidad;
        
        
    }*/
}
