package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXChip;
import com.jfoenix.controls.JFXChipView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDefaultChip;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.skins.JFXChipViewSkin;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import com.sun.javafx.scene.control.skin.LabeledText;
import ecoshop.backend.Envase;
import ecoshop.backend.ImagenesAuxiliar;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.json.simple.JSONObject;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;

/*
 * Clase controladora de productos diseñada para el administrador del sistema
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class AdminProductosController implements Initializable {
    private static final String NOMBRE_JSON = "productos";
    
    @FXML private JFXComboBox BoxBuscarPor;
    @FXML private JFXButton botonBuscar;
    @FXML private JFXTextField TFBuscar;
    @FXML private JFXTextField TBPrecio;
    @FXML private JFXTextArea TBDescripcion;
    @FXML private JFXTextField TBMaterial;
    @FXML private JFXTextField TBNombre;
    @FXML private JFXTextField TBId;
    @FXML private Label signoPeso;
    @FXML private Pane pane;
    @FXML private JFXButton botonEliminarProducto;
    @FXML private ImageView imageViewImagen;
    @FXML private JFXButton botonAgregarProducto;
    @FXML private ListView listViewEnvases;
    
    @FXML private TableView<Producto> tableViewBorrar;
    @FXML private TableColumn<Producto, String> columnId;
    @FXML private TableColumn<Producto, String> columnNombre;
    @FXML private TableColumn<Producto, String> columnMaterial;
    @FXML private TableColumn<Producto, String> columnDescripcion;
    @FXML private TableColumn<Producto, String> columnPrecio;
    @FXML private TableColumn<Producto, String> columnEnvases;
    @FXML private TableColumn<Producto, String> columnImagen;
    
    private RepetidoValidator validadorRepeticionId;
    
    private boolean controlSignoPeso = false;
    
    private boolean imagenSeleccionada = false;
    private ArrayList<Envase> envases;
    
    private static final UnaryOperator<TextFormatter.Change> FILTRO = (TextFormatter.Change t) -> {
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
        TBPrecio.setTextFormatter(new TextFormatter<>(FILTRO));    
      
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
        
        tableViewBorrar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            botonEliminarProducto.setDisable(newSelection == null);
        });
    
       validadorRepeticionId = new RepetidoValidator();
       
        validarCampo(TBPrecio, 
               new String[]{"Campo obligatorio", "Campo debe ser un entero"}, 
               new ValidatorBase[]{new RequiredFieldValidator(), new NumberValidator()});
        validarCampo(TBMaterial, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        validarCampo(TBId, 
               new String[]{"Campo obligatorio", "Campo debe ser un entero", "Esa ID ya esta en uso"}, 
               new ValidatorBase[]{new RequiredFieldValidator(), new NumberValidator(), validadorRepeticionId});
        validarCampo(TBNombre, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        
        envases = JSONAuxiliar.procesarArchivo("envases", Envase::parsearEntrySet);
       
        listViewEnvases.getItems().setAll(envases.stream().map(x -> x.getNombre()).collect(Collectors.toList()));
        listViewEnvases.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        actualizarDatos();
    }
    
    private void actualizarDatos(){
        ArrayList<Producto> productos = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, Producto::parsearEntrySet);
        validadorRepeticionId.setExistentes((ArrayList<String>)(productos.stream().map(x -> x.getId() + "").collect(Collectors.toList())));
        tableViewBorrar.getItems().setAll(productos);
    }
    
    private void validarCampo(JFXTextField campo, 
            String[] mensajes,
            ValidatorBase[] validators){
        for(int i = 0; i < validators.length; ++i){
            validators[i].setMessage(mensajes[i]);
            campo.getValidators().add(validators[i]);
            
            Image image = new Image(getClass().getResourceAsStream("recursos/attention.png"));
            ImageView icono = new ImageView(image);
            icono.setFitHeight(13);
            icono.setFitWidth(13);
            validators[i].setIcon(icono);
        }
      
        campo.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal && campo.validate()) {
                campo.getStyleClass().add("error");
            }
        });
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
    private void clickImagen(MouseEvent event){
        imageViewImagen.setImage(ImagenesAuxiliar.abrirImagen());
        imagenSeleccionada = true;
    }
    
    @FXML
    private void clickBotonBuscar(ActionEvent event){
        String columna = ((String)BoxBuscarPor.getValue()).toLowerCase();
        
        JSONArray resultado = JSONAuxiliar.conseguirConColumna(TFBuscar.getText(), columna, NOMBRE_JSON, columna.equals("id"));
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        for(int i = 0; i < resultado.size(); ++i){
            productos.add(Producto.parsearEntrySet(((JSONObject)resultado.get(i)).entrySet()));
        }
        
        tableViewBorrar.getItems().setAll(productos);
    }
    
    @FXML
    private void clickBotonAgregarProducto(MouseEvent event){
        boolean idValida = TBId.validate();
        boolean nombreValido = TBNombre.validate();
        boolean materialValido = TBMaterial.validate();
        boolean precioValido = TBPrecio.validate();
        if(!(idValida && nombreValido && materialValido && precioValido)){
            return;
        }
        
        JSONObject nuevo  = new JSONObject();
        nuevo.put("id", TBId.getText());
        nuevo.put("nombre", TBNombre.getText());
        nuevo.put("material", TBMaterial.getText());
        nuevo.put("precio", TBPrecio.getText().substring(4));
        nuevo.put("descripcion", TBDescripcion.getText());
        
        String rutaImagen =  "";
        if(imagenSeleccionada)
            rutaImagen = ImagenesAuxiliar.guardarImagen(imageViewImagen.getImage());
        
        nuevo.put("imagen", rutaImagen);
        
        JSONArray envasesArray = new JSONArray();
        List l = listViewEnvases.getSelectionModel().getSelectedIndices();
        for(int i = 0; i < l.size(); ++i){
            int id = envases.get((int)l.get(i)).getId();
            JSONObject envaseId = new JSONObject();
            envaseId.put("id", id + "");
            envasesArray.add(envaseId);
        }
        
        nuevo.put("envases", envasesArray);
        
        JSONAuxiliar.agregar(nuevo,NOMBRE_JSON);
        
        volverEstadoInicial();
        actualizarDatos();
    }
    
    private void volverEstadoInicial(){
        TBId.clear();
        TBNombre.clear();
        TBMaterial.clear();
        TBPrecio.setText("    ");
        TBDescripcion.clear();
        
        listViewEnvases.selectionModelProperty().set(null);
        
        imagenSeleccionada = false;
        try{
            imageViewImagen.setImage(new Image(getClass().getResourceAsStream("recursos/empty-image.png")));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void clickBotonEliminar(ActionEvent event){
        Producto producto = tableViewBorrar.getSelectionModel().getSelectedItem();
        JSONObject o = (JSONObject) JSONAuxiliar.conseguirConColumna(producto.getId() + "", "id", NOMBRE_JSON, true).get(0);
        JSONAuxiliar.borrar(NOMBRE_JSON, o);
            
        actualizarDatos();
    }
}
