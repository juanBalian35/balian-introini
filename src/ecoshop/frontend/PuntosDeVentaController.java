/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import ecoshop.backend.Envase;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import ecoshop.backend.PuntoDeVenta;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class PuntosDeVentaController implements Initializable {
    private static final String NOMBRE_JSON = "puntosDeVenta";
    
    @FXML private WebView webView;
    
    @FXML private Pane paneWebView;
    
    @FXML private JFXTextField TFCalle;
    @FXML private JFXTextField TFNumero;
    @FXML private JFXTextField TFId;
    @FXML private JFXTextField TFNombre;
    @FXML private JFXTextField TFCiudad;
    @FXML private JFXComboBox BoxDepartamento;
    
    @FXML private JFXTextField TFBuscar;
    @FXML private JFXComboBox BoxBuscarPor;
    @FXML private Button botonBuscar;
    @FXML private Button botonEliminarPunto;

    @FXML private TableView<PuntoDeVenta> tableViewBorrar;
    @FXML private TableColumn<PuntoDeVenta, String> columnId;
    @FXML private TableColumn<PuntoDeVenta, String> columnNombre;
    @FXML private TableColumn<PuntoDeVenta, String> columnDireccion;
    
    private RepetidoValidator validadorRepeticionId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
        
        tableViewBorrar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            botonEliminarPunto.setDisable(newSelection == null);
        });
        
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnDireccion.setCellValueFactory(features -> new SimpleStringProperty(features.getValue().getDireccion()));
        
        MyBrowser myBrowser = new MyBrowser();
        
        paneWebView.getChildren().add(myBrowser);
        
        validadorRepeticionId = new RepetidoValidator();
                
        validarCampo(TFId, 
               new String[]{"Campo obligatorio", "Campo debe ser un entero", "Esa ID ya esta en uso"}, 
               new ValidatorBase[]{new RequiredFieldValidator(), new NumberValidator(), validadorRepeticionId});
        validarCampo(TFNombre, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        validarCampo(TFCiudad, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        validarCampo(TFNumero, 
               new String[]{"Campo obligatorio", "Campo debe ser un entero"}, 
               new ValidatorBase[]{new RequiredFieldValidator(), new NumberValidator()});
        validarCampo(TFCalle, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        validarCampo(BoxDepartamento, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
    }    
    
    @FXML
    private  void accionBoxBuscarPor(ActionEvent event) {
        String seleccion = ((String)BoxBuscarPor.getValue()).toLowerCase();
        if("todos".equals(seleccion)){
            TFBuscar.disableProperty().setValue(true);
            TFBuscar.promptTextProperty().setValue(null);
            TFBuscar.clear();
            actualizarDatos(false);
        }
        else{
            TFBuscar.disableProperty().setValue(false);
            TFBuscar.promptTextProperty().setValue((String) seleccion);
        }
    }
    
    private void actualizarDatos(boolean actualizarMapa){
        System.out.println("actualizando");
        
        ArrayList<PuntoDeVenta> puntos = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, PuntoDeVenta::parsearEntrySet);
            
        if(actualizarMapa){
            for(int i = 0 ; i < puntos.size(); ++i){
                agregarMarcador(puntos.get(i).getCalle(), puntos.get(i).getNumero(), 
                        puntos.get(i).getCiudad(), puntos.get(i).getDepartamento(),
                        puntos.get(i).getId(), true);
            }
        }
        validadorRepeticionId.setExistentes((ArrayList<String>)(puntos.stream().map(x -> x.getId() + "").collect(Collectors.toList())));
        tableViewBorrar.getItems().setAll(puntos);
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
    
    class MyBrowser extends Pane {
        WebEngine webEngine = webView.getEngine();

        public MyBrowser() {
            final URL urlGoogleMaps = getClass().getResource("demo.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
                @Override
                public void handle(WebEvent<String> e) {
                    System.out.println(e.toString());
                    
                    if(e.getData().contains("GEOCODING_ERROR")){
                        Toast.show("Error creando punto de venta", "OK", 5, TFId);
                    }
                    else if (e.getData().contains("AGREGADO_CORRECTAMENTE")){
                        String departamento = ((Label)BoxDepartamento.getValue()).getText();
                        JSONObject puntoDeVenta = new JSONObject();
                        puntoDeVenta.put("id", TFId.getText());
                        puntoDeVenta.put("nombre", TFNombre.getText());
                        puntoDeVenta.put("departamento", departamento);
                        puntoDeVenta.put("ciudad", TFCiudad.getText());
                        puntoDeVenta.put("calle", TFCalle.getText());
                        puntoDeVenta.put("numero", TFNumero.getText());

                        JSONAuxiliar.agregar(puntoDeVenta, NOMBRE_JSON);

                        actualizarDatos(false);
                        limpiarCampos();
                        
                        Toast.show("Punto de venta creado exitosamente", "OK", 5, TFId);
                    }
                }
            });

            getChildren().add(webView);
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, State oldState, State newState){
                    System.out.println(newState);
                    actualizarDatos(true);  
                }
            });
        }
    }
    
    @FXML
    private void agregarPuntoDeVenta(ActionEvent event){
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
        
        boolean idValida = TFId.validate();
        boolean nombreValido = TFNombre.validate();
        boolean deptoValido = BoxDepartamento.validate();
        boolean calleValida = TFCalle.validate();
        boolean numeroValido = TFNumero.validate();
        boolean ciudadValida = TFCiudad.validate();
        if(!(idValida && nombreValido && deptoValido && calleValida && numeroValido && ciudadValida)){
            return;
        }
        
        String departamento = ((Label)BoxDepartamento.getValue()).getText();
        
        agregarMarcador(TFCalle.getText(), TFNumero.getText(), 
                TFCiudad.getText(), departamento, Integer.parseInt(TFId.getText()), false);
    }
    
    @FXML
    private void clickBotonBuscar(ActionEvent event){
        String columna = ((String)BoxBuscarPor.getValue()).toLowerCase();
        
        JSONArray resultado = JSONAuxiliar.conseguirConColumna(TFBuscar.getText(), columna, NOMBRE_JSON, columna.equals("id"));
        
        ArrayList<PuntoDeVenta> puntos = new ArrayList<>();
        
        for(int i = 0; i < resultado.size(); ++i){
            puntos.add(PuntoDeVenta.parsearEntrySet(((JSONObject)resultado.get(i)).entrySet()));
        }
        
        tableViewBorrar.getItems().setAll(puntos);
    }
    
    private void agregarMarcador(String calle, String numero, 
            String ciudad, String departamento, int id, boolean actualizando){
        String direccion = calle + " " + numero + ", " + ciudad 
                + ", " + departamento;
        
        String s = "" +
            "window.direccion = '" + direccion + "';" +
            "window.id = " + id + "; " +
            "window.actualizando = " + (actualizando ? "1" : "0") + "; " +
            "document.goToLocation(window.direccion, window.id, window.actualizando);";
        
        webView.getEngine().executeScript(s);
    }
    
    @FXML
    private void eliminarPunto(ActionEvent event){
        PuntoDeVenta punto = tableViewBorrar.getSelectionModel().getSelectedItem();
        JSONObject o = (JSONObject) JSONAuxiliar.conseguirConColumna(punto.getId() + "", "id", NOMBRE_JSON, true).get(0);
        
        webView.getEngine().executeScript("" +
            "window.id = " + o.get("id") + "; " +
            "document.borrar(window.id);"
        );
        
        JSONAuxiliar.borrar(NOMBRE_JSON, o);

        Toast.show("Punto de venta eliminado.", "OK", 5, TFId);
        actualizarDatos(true);
    }

    private void limpiarCampos(){
        TFId.clear();
        TFNombre.clear();
        TFCalle.clear();
        TFCiudad.clear();
        TFNumero.clear();
        BoxDepartamento.getSelectionModel().clearSelection();
    }
}
