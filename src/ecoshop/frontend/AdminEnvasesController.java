/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChip;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDefaultChip;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class AdminEnvasesController implements Initializable {

    private static final String NOMBRE_JSON = "productos";
    
    @FXML
    private JFXComboBox BoxBuscarPor;
    
    @FXML
    private JFXButton botonBuscar;
    
    @FXML
    private JFXTextField TFBuscar;
    
    @FXML
    private JFXTextField TBPrecio;
    
    @FXML
    private JFXTextArea TBDescripcion;
            
    @FXML
    private JFXTextField TBMaterial;
    
    @FXML
    private JFXTextField TBNombre;
    
    @FXML
    private JFXTextField TBId;
    
    @FXML
    private Label signoPeso;
    
    @FXML
    private Pane pane;
    
    @FXML
    private JFXButton botonAgregarProducto;
    
    @FXML
    private StackPane paneChipEnvases;
    
    @FXML
    private TableView<Producto> tableViewBorrar;
    
    @FXML 
    private TableColumn<Producto, String> columnId;
    
    @FXML 
    private TableColumn<Producto, String> columnNombre;
    
    @FXML 
    private TableColumn<Producto, String> columnMaterial;
    
    @FXML 
    private TableColumn<Producto, String> columnDescripcion;
    
    @FXML 
    private TableColumn<Producto, String> columnPrecio;
    
    @FXML 
    private TableColumn<Producto, String> columnEnvases;
    
    @FXML 
    private TableColumn<Producto, String> columnImagen;
    
    boolean controlSignoPeso = false;
    
    UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {
            if (t.isReplaced()) 
                if(t.getText().matches("[^0-9]"))
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));

            if (t.isAdded()) {
                if ((t.getControlText().contains(".") && t.getText().matches("[^0-9]")) 
                        || t.getText().matches("[^0-9.]")) {
                    t.setText("");
                }
            }

            return t;
        }
    };
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnEnvases.setCellValueFactory(new PropertyValueFactory<>("envases"));
        columnImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        
        
        //Formato de los textfields
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
          
        validarCampo(TBId, "recursos/attention.png", "Campo obligatorio");
        validarCampo(TBNombre, "recursos/attention.png", "Campo obligatorio");
    }
 
    @FXML
     private void validarCampo(JFXTextField campo, String rutaImagen, String mensaje){
            RequiredFieldValidator var = new RequiredFieldValidator();
        var.setMessage(mensaje);
     
        Image i = new Image(getClass().getResourceAsStream(rutaImagen));
        ImageView icono = new ImageView(i);
        icono.setFitHeight(13);
        icono.setFitWidth(13);
        var.setIcon(icono);
      
        campo.getValidators().add(var);
        campo.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                campo.validate();
                if(campo.validate()){
                    campo.getStyleClass().add("error");
                }
            }
        });
     
        }
 
    @FXML
    private  void accionBoxBuscarPor(ActionEvent event) {
        Object seleccion = BoxBuscarPor.getValue();
        TFBuscar.disableProperty().setValue(Boolean.FALSE);
        TFBuscar.promptTextProperty().setValue((String) seleccion);
    }
    
    @FXML
    private void clickBotonBuscar(ActionEvent event){
        String columna = ((String)BoxBuscarPor.getValue()).toLowerCase();
        
        JSONObject objeto = JSONAuxiliar.conseguirConColumna(TFBuscar.getText(), columna, NOMBRE_JSON);
        
        //Set<Map.Entry<String, String>> entrySet = objeto.entrySet();
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        //productos.add(productoDesdeEntrySet(objeto.entrySet()));
        
        tableViewBorrar.getItems().setAll(productos);
    }
    
    @FXML
    private void clickBotonAgregarProducto(MouseEvent event){
        JSONObject nuevo  = new JSONObject();
        nuevo.put("id", TBId.getText());
        nuevo.put("nombre", TBNombre.getText());
      
        
        if(JSONAuxiliar.existe(TBId.getText(), "id", NOMBRE_JSON)){
            //TODO: Escribir error de que esta ID ya esta en uso
            return; 
        }
        
        JSONAuxiliar.agregar(nuevo,NOMBRE_JSON);
    }
}
