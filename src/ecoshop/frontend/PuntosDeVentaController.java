/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class PuntosDeVentaController implements Initializable {
    @FXML
    private WebView webView;
    
    @FXML
    private Pane paneWebView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyBrowser myBrowser = new MyBrowser();
        
        paneWebView.getChildren().add(myBrowser);
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

            Button update = new Button("Update");
            update.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    //lat = Double.parseDouble(latitude.getText());
                    //lon = Double.parseDouble(longitude.getText());
                    /*
                    System.out.printf("%.2f %.2f%n", lat, lon);

                    webEngine.executeScript("" +
                        "window.lat = " + lat + ";" +
                        "window.lon = " + lon + ";" +
                        "document.goToLocation(window.lat, window.lon);"
                    );*/
                }
            });

            HBox toolbar  = new HBox();
            //toolbar.getChildren().addAll(latitude, longitude, update);

            getChildren().addAll(toolbar);
        }
    }

}
