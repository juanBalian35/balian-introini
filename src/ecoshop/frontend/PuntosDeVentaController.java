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

    @FXML private TableView tableViewBorrar;
    @FXML private TableColumn<PuntoDeVenta, String> columnId;
    @FXML private TableColumn<PuntoDeVenta, String> columnNombre;
    @FXML private TableColumn<PuntoDeVenta, String> columnDireccion;
    
    private RepetidoValidator validadorRepeticionId;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    private void actualizarDatos(){
        ArrayList<PuntoDeVenta> puntos = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, PuntoDeVenta::parsearEntrySet);
        for(int i = 0 ; i < puntos.size(); ++i){
            agregarMarcador(puntos.get(i).getCalle(), puntos.get(i).getNumero(), 
                    puntos.get(i).getCiudad(), puntos.get(i).getDepartamento());
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
                }
            });

            getChildren().add(webView);
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, State oldState, State newState){
                    System.out.println(newState);
                    actualizarDatos();  
                }
            });
        }
    }
    
    @FXML
    private void agregarPuntoDeVenta(ActionEvent event){
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
        
        agregarMarcador(TFCalle.getText(), TFNumero.getText(), TFCiudad.getText(), departamento);
                
        JSONObject puntoDeVenta = new JSONObject();
        puntoDeVenta.put("id", TFId.getText());
        puntoDeVenta.put("nombre", TFNombre.getText());
        puntoDeVenta.put("departamento", departamento);
        puntoDeVenta.put("ciudad", TFCiudad.getText());
        puntoDeVenta.put("calle", TFCalle.getText());
        puntoDeVenta.put("numero", TFNumero.getText());
        
        JSONAuxiliar.agregar(puntoDeVenta, NOMBRE_JSON);
    }
    
    private void agregarMarcador(String calle, String numero, 
            String ciudad, String departamento){
        String direccion = calle + " " + numero + ", " + ciudad 
                + ", " + departamento;
        
        webView.getEngine().executeScript("" +
            "window.direccion = '" + direccion + "';" +
            "document.goToLocation(window.direccion);"
        );
    }

}
