/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import ecoshop.backend.Envase;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Random;
import static javafx.animation.Interpolator.EASE_BOTH;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class EstadisticasController implements Initializable {
    private static final String ENVASES_JSON = "envases";
    private static final String PRODUCTOS_JSON = "productos";
    
    @FXML private PieChart pieChart;
    @FXML private BarChart barChartEnvases;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearPieChartAleatorio();
        
        ArrayList<Envase> e = 
                JSONAuxiliar.procesarArchivo(ENVASES_JSON, Envase::parsearEntrySet);
        
        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Envases reutilizados");

        for(int i = 0 ;i < e.size(); ++i){
            dataSeries1.getData().add(new XYChart.Data(e.get(i).getNombre(), e.get(i).getContadorReuso()));
        }

        barChartEnvases.getData().add(dataSeries1);
    }
    
    private void crearPieChartAleatorio(){
        ArrayList<Producto> productos = 
                JSONAuxiliar.procesarArchivo(PRODUCTOS_JSON, Producto::parsearEntrySet);
        
        ArrayList<PieChart.Data> m = new ArrayList<>();
        ArrayList<Double> valores = new ArrayList<>();
        int cantidad = Math.min(productos.size(),5);
        Random r = new Random();
        double suma = 0;
        for(int i = 0; i < cantidad; ++i){
            valores.add(Double.parseDouble(r.nextInt(100) + ""));
            suma += valores.get(i);
        }
        
        for(int i = 0; i < cantidad; ++i){
            valores.set(i, (valores.get(i)/suma)*100);
        }
        
        for(int i = 0; i < cantidad; ++i){
            m.add(new PieChart.Data(productos.get(i).getNombre(), valores.get(i)));
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(m);
        
        pieChart.setData(pieChartData);
    }
}
