/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import ecoshop.backend.Envase;
import ecoshop.backend.ImagenesAuxiliar;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
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
    private static final String NOMBRE_JSON = "envases";
    
    @FXML private JFXComboBox BoxBuscarPor;
    @FXML private JFXButton botonBuscar;
    @FXML private JFXTextField TFBuscar;
    @FXML private JFXTextField TBNombre;
    @FXML private JFXTextField TBId;
    @FXML private ImageView imageViewImagen;
    @FXML private JFXComboBox BoxCategoria;
    
    @FXML private TableView<Envase> tableViewBorrar;
    @FXML private TableColumn<Envase, String> columnId;
    @FXML private TableColumn<Envase, String> columnNombre;
    @FXML private TableColumn<Envase, String> columnCategoria;
    @FXML private TableColumn<Envase, String> columnImagen;
    
    boolean imagenSeleccionada = false;
    
    private RepetidoValidator validadorRepeticionId;
    
    private final static UnaryOperator<TextFormatter.Change> FILTRO = (TextFormatter.Change t) -> {
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
        columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        
        //Formato de los textfields
        TFBuscar.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            botonBuscar.disableProperty().setValue(newValue.length() == 0);
        });
          
        validadorRepeticionId = new RepetidoValidator();
       
        validarCampo(TBId, 
                new String[]{"Campo obligatorio", "Esa ID ya esta en uso"}, 
                new ValidatorBase[]{new RequiredFieldValidator(), validadorRepeticionId});
        validarCampo(TBNombre, 
                new String[]{"Campo obligatorio"}, 
                new ValidatorBase[]{new RequiredFieldValidator()});
        validarCampo(BoxCategoria,
                new String[]{"Campo obligatorio"},
                new ValidatorBase[]{new RequiredFieldValidator()});
        
        actualizarDatos();
    }
 
    private void validarCampo(IFXLabelFloatControl campo, 
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
      
        if(campo instanceof JFXTextField){
            ((JFXTextField)campo).focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal && campo.validate())
                    ((JFXTextField)campo).getStyleClass().add("error");
            });
        }
        else if(campo instanceof JFXComboBox){
            ((JFXComboBox)campo).focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal && campo.validate())
                    ((JFXComboBox)campo).getStyleClass().add("error");
            });
        }
    }
 
    @FXML
    private void clickImagen(MouseEvent event){
        imageViewImagen.setImage(ImagenesAuxiliar.abrirImagen());
        imagenSeleccionada = true;
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
        
        ArrayList<Envase> envases = new ArrayList<>();
        
        for(int i = 0; i < resultado.size(); ++i){
            envases.add(Envase.parsearEntrySet(((JSONObject)resultado.get(i)).entrySet()));
        }
        
        tableViewBorrar.getItems().setAll(envases);
    }
    
    @FXML
    private void clickBotonEliminar(ActionEvent event){
        Envase envase = tableViewBorrar.getSelectionModel().getSelectedItem();
        JSONObject o = (JSONObject) JSONAuxiliar.conseguirConColumna(envase.getId() + "", "id", NOMBRE_JSON, true).get(0);
        JSONAuxiliar.borrar(NOMBRE_JSON, o);
        Toast.show("El envase se eliminó correctamente", "OK", 5, TFBuscar);

            
        actualizarDatos();
    }
    
    private void actualizarDatos(){
        ArrayList<Envase> envases = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, Envase::parsearEntrySet);
        validadorRepeticionId.setExistentes((ArrayList<String>)(envases.stream().map(x -> x.getId() + "").collect(Collectors.toList())));
        tableViewBorrar.getItems().setAll(envases);
    }
    
    @FXML
    private void clickBotonAgregarEnvase(MouseEvent event){
        boolean idValida = TBId.validate();
        boolean nombreValido = TBNombre.validate();
        boolean categoriaValida = BoxCategoria.validate();
        if(!(idValida && nombreValido && categoriaValida)){
            return;
        }
        
        JSONObject nuevo  = new JSONObject();
        nuevo.put("id", TBId.getText());
        nuevo.put("nombre", TBNombre.getText());
        String categoria = ((Label)BoxCategoria.getSelectionModel().getSelectedItem()).getText();
        nuevo.put("categoria", categoria);
        
        String rutaImagen =  "";
        if(imagenSeleccionada)
            rutaImagen = ImagenesAuxiliar.guardarImagen(imageViewImagen.getImage());
        
        nuevo.put("imagen", rutaImagen);
        
        JSONAuxiliar.agregar(nuevo,NOMBRE_JSON);
        Toast.show("El envase "+ TBNombre.getText()+ " se agregó correctamente", "OK", 5, TFBuscar);

        volverEstadoInicial();
        actualizarDatos();
    }
    
    private void volverEstadoInicial(){
        TBId.clear();
        TBNombre.clear();
        BoxCategoria.getSelectionModel().clearSelection();
        
        //listViewEnvases.selectionModelProperty().set(null);
        
        imagenSeleccionada = false;
        try{
            imageViewImagen.setImage(new Image(getClass().getResourceAsStream("recursos/empty-image.png")));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
