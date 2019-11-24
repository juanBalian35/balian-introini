/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
    @FXML private JFXComboBox BoxDepartamentos;

    @FXML private TableView tableViewBorrar;
    @FXML private TableColumn<PuntoDeVenta, String> columnId;
    @FXML private TableColumn<PuntoDeVenta, String> columnNombre;
    @FXML private TableColumn<PuntoDeVenta, String> columnDireccion;
    
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
    }    
    
    private void actualizarDatos(){
        ArrayList<PuntoDeVenta> puntos = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, PuntoDeVenta::parsearEntrySet);
        for(int i = 0 ; i < puntos.size(); ++i){
            agregarMarcador(puntos.get(i).getCalle(), puntos.get(i).getNumero(), 
                    puntos.get(i).getCiudad(), puntos.get(i).getDepartamento());
        }
        //validadorRepeticionId.setExistentes((ArrayList<String>)(envases.stream().map(x -> x.getId() + "").collect(Collectors.toList())));
        tableViewBorrar.getItems().setAll(puntos);
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
        String departamento = ((Label)BoxDepartamentos.getValue()).getText();
        
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
