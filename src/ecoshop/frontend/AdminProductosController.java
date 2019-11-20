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
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.json.simple.JSONObject;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Clase controladora de productos diseñada para el administrador del sistema
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class AdminProductosController implements Initializable {
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
        
        //Inicializa los ChipViews  
        JFXChipView<String> chipView = new JFXChipView<>();
        chipView.getChips().addAll("WEF", "WWW", "JD");
        chipView.getSuggestions().addAll("HELLO", "TROLL", "WFEWEF", "WEF");
        chipView.setStyle("-fx-background-color:#eaf3f9;");
        
        chipView.getChips().forEach((tab)-> {
            System.out.println("stuff with" + tab);
        });
        
        chipView.setChipFactory( (view, item) -> {
            JFXChip<String> chip = new JFXDefaultChip<>(view, item);
            chip.setOnMouseClicked(event -> chip.setStyle("-fx-background-color: RED;"));
            return chip;
        });
       
        paneChipEnvases.getChildren().add(chipView);
        paneChipEnvases.setMargin(chipView, new Insets(10));
        
        //Formato de los textfields
        TBPrecio.setTextFormatter(new TextFormatter<>(filter));    
      
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
        
        TBPrecio.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if(!controlSignoPeso && newValue.length()<=3){
                    TBPrecio.setText(oldValue);
                }
                controlSignoPeso = false;
            }
        });
          
        TBPrecio.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean wasFocused, Boolean isNowFocused) {
                if(isNowFocused){
                    signoPeso.setVisible(true);
                    if(!(TBPrecio.getText().length()>3)){
                        TBPrecio.setText("    ");
                    }
                }
                else if(TBPrecio.getText().length() == 4){
                    signoPeso.setVisible(false);
                    controlSignoPeso = true;
                    TBPrecio.setText("");
                }
            } 
        });
    
       validarCampo(TBPrecio, "recursos/attention.png", "Campo obligatorio");
       validarCampo(TBMaterial, "recursos/attention.png", "Campo obligatorio");
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
        
        Set<Entry<String, String>> entrySet = objeto.entrySet();
        
        ArrayList<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();
        for(Entry<String,String> entry : entrySet){
            switch(entry.getKey().toLowerCase()){
                case "nombre":
                    producto.setNombre(entry.getValue());
                    break;
                case "id":
                    producto.setId(Integer.parseInt(entry.getValue()));
                    break;
                case "precio":
                    producto.setPrecio(Double.parseDouble(entry.getValue()));
                    break;
                case "material":
                    producto.setMaterial(entry.getValue());
                    break;
                case "descripcion":
                    producto.setDescripcion(entry.getValue());
                    break;
                case "imagen":
                    producto.setImagen(entry.getValue());
                    break; 
                //case "envases":
                    //producto.setEnvases(entry.getValue());
                default:
                    // TODO: Preguntar si es necesario siempre poner un default?
            }
        }
        
        productos.add(producto);
        
        tableViewBorrar.getItems().setAll(productos);
    }
    
    @FXML
    private void clickBotonAgregarProducto(MouseEvent event){
        if(!TBId.validate() && !TBNombre.validate() && !TBMaterial.validate() && !TBPrecio.validate()){
            return;
        }   
       
        
        JSONObject nuevo  = new JSONObject();
        nuevo.put("id", TBId.getText());
        nuevo.put("nombre", TBNombre.getText());
        nuevo.put("material", TBMaterial.getText());
        nuevo.put("precio", TBPrecio.getText().substring(4));
        nuevo.put("descripcion", TBDescripcion.getText());
        
        if(JSONAuxiliar.existe(TBId.getText(), "id", NOMBRE_JSON)){
            //TODO: Escribir error de que esta ID ya esta en uso
            return; 
        }
        
        JSONAuxiliar.agregar(nuevo,NOMBRE_JSON);
            System.out.println("agrego producto");
    }
         
}
