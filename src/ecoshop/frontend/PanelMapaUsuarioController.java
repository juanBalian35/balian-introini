/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package ecoshop.frontend;

import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.PuntoDeVenta;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author juanchi
 */
public class PanelMapaUsuarioController implements Initializable {
    private static final String NOMBRE_JSON = "puntosDeVenta";
    
    @FXML private WebView webView;
    
    @FXML private Pane paneWebView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyBrowser myBrowser = new MyBrowser();
        
        paneWebView.getChildren().add(myBrowser);
    }
    
    private void actualizarDatos(){
        ArrayList<PuntoDeVenta> puntos =
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, PuntoDeVenta::parsearEntrySet);
        
        for(int i = 0 ; i < puntos.size(); ++i){
            agregarMarcador(puntos.get(i).getCalle(), puntos.get(i).getNumero(),
                    puntos.get(i).getCiudad(), puntos.get(i).getDepartamento(),
                    puntos.get(i).getId(), true);
        }
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
    
    private class MyBrowser extends Pane {
        WebEngine webEngine = webView.getEngine();
        
        public MyBrowser() {
            final URL urlGoogleMaps = getClass().getResource("demo.html");
            webEngine.load(urlGoogleMaps.toExternalForm());
            webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
                @Override
                public void handle(WebEvent<String> e) {
                }
            });
            
            getChildren().add(webView);
            webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Worker.State oldState, Worker.State newState){
                    actualizarDatos();
                }
            });
        }
    }
}