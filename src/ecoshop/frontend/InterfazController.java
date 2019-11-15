package ecoshop.frontend;

import java.io.IOException;
import java.lang.reflect.Array;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

/**
 *
 * @author Agustín Introini
 * @author Juan Balian
 */
public class InterfazController implements Initializable {
    @FXML 
    private Button botonAdministrarEnvases;
    
    @FXML 
    private Button botonAdministrarProductos;
    
    @FXML
    private Button botonPuntosDeVenta;
    
    @FXML
    private Button botonRegistrarVentas;
    
    @FXML
    private Button botonPreVentas;
    
    @FXML
    private Button botonEstadisticas;
    
    @FXML 
    private ImageView imagenAdministrarEnvases;
    
    @FXML 
    private ImageView imagenAdministrarProductos;
    
    @FXML
    private ImageView imagenPuntosDeVenta;
    
    @FXML
    private ImageView imagenRegistrarVentas;
    
    @FXML
    private ImageView imagenPreVentas;
    
    @FXML
    private ImageView imagenEstadisticas;
    
    @FXML 
     private Shape triangulo;
    
    @FXML 
    private Pane PaneRegistrarVenta ;    
    @FXML 
    private Pane PanePreVenta;    
    @FXML 
    private Pane PaneAdministrarProducto ;    
    @FXML 
    private Pane PanePuntosDeVenta;    
    @FXML 
    private Pane PaneAdministrarEnvase ;
    @FXML 
    private Pane PaneEstadistica ;
  
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                cargarPane(PaneAdministrarProducto, "AdminProductos.fxml");
                cargarPane(PanePreVenta, "PreVentas.fxml");
                cargarPane(PaneRegistrarVenta, "RegistrarVenta.fxml");
                cargarPane(PanePuntosDeVenta, "PuntosDeVenta.fxml");
                cargarPane(PaneAdministrarEnvase, "AdminEnvases.fxml");
                cargarPane(PaneEstadistica, "Estadisticas.fxml");                

    }    
  
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    double x, y;
     
    @FXML
    private void dragged(MouseEvent event) {
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setX(event.getScreenX() - x);
         stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }
    
    private void cambiarClaseControl(Node control, String claseNueva, String claseAntigua){
        control.getStyleClass().remove(claseAntigua);
        control.getStyleClass().remove(claseNueva);
        control.getStyleClass().add(claseNueva);
    }
    
    private void cambiarImagenBoton(ImageView imagen, String ruta){
        imagen.setImage(new Image(getClass().getResourceAsStream(ruta)));
    }
    
    private void limpiarBoton(Button boton, ImageView imageView, String ruta){
        cambiarClaseControl(boton,"botonNoSeleccionado", "botonSeleccionado");
        cambiarImagenBoton(imageView, ruta);
    }
    
    private void limpiarBotones(){
        limpiarBoton(botonAdministrarProductos, imagenAdministrarProductos, "recursos/003-basket.png");
        limpiarBoton(botonAdministrarEnvases, imagenAdministrarEnvases, "recursos/001-plastic.png");
        limpiarBoton(botonPuntosDeVenta, imagenPuntosDeVenta, "recursos/002-street.png");
        limpiarBoton(botonRegistrarVentas, imagenRegistrarVentas, "recursos/004-swipe.png");
        limpiarBoton(botonPreVentas, imagenPreVentas, "recursos/011-payment-day.png");
        limpiarBoton(botonEstadisticas, imagenEstadisticas, "recursos/007-graphic.png");
    }
    
    private void esconderPanes(){
        
        
       
        cambiarClaseControl(PaneRegistrarVenta,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(PanePreVenta,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(PaneAdministrarProducto,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(PanePuntosDeVenta,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(PaneAdministrarEnvase,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(PaneEstadistica,"paneNoSeleccionado", "paneSeleccionado");
    }
    
    @FXML
    private void clickBotonAdministrarProductos(MouseEvent event) {
        limpiarBotones();
        esconderPanes();
        
        Button b = (Button)event.getSource();
        cambiarClaseControl(b,"botonSeleccionado", "botonNoSeleccionado");
       cambiarClaseControl(PaneAdministrarProducto,"paneSeleccionado","paneNoSeleccionado");
        cambiarImagenBoton(imagenAdministrarProductos,"recursos/006-basket-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight());
                
    }
    
    @FXML
    private void clickBotonAdministrarEnvases(MouseEvent event) {
        limpiarBotones();
        esconderPanes();
        
        Button b = (Button)event.getSource();
        cambiarClaseControl(b,"botonSeleccionado", "botonNoSeleccionado");
       cambiarClaseControl(PaneAdministrarEnvase,"paneSeleccionado","paneNoSeleccionado");
        cambiarImagenBoton(imagenAdministrarEnvases,"recursos/001-plastic-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight());
    }
    
    @FXML
    private void clickBotonPuntosDeVenta(MouseEvent event) {
        limpiarBotones();
        esconderPanes();
        
        Button b = (Button)event.getSource();
        cambiarClaseControl(b,"botonSeleccionado", "botonNoSeleccionado");
        cambiarClaseControl(PanePuntosDeVenta,"paneSeleccionado","paneNoSeleccionado");
        cambiarImagenBoton(imagenPuntosDeVenta,"recursos/002-street-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight());
    }
    
    @FXML
    private void clickBotonRegistrarVentas(MouseEvent event) {
        limpiarBotones();
        esconderPanes();
        
        Button b = (Button)event.getSource();
        cambiarClaseControl(b,"botonSeleccionado", "botonNoSeleccionado");
        cambiarClaseControl(PaneRegistrarVenta,"paneSeleccionado","paneNoSeleccionado");
        cambiarImagenBoton(imagenRegistrarVentas,"recursos/003-swipe-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight());
    }
    
    @FXML
    private void clickBotonPreVentas(MouseEvent event) {
        limpiarBotones();
        esconderPanes();
        
        Button b = (Button)event.getSource();
        cambiarClaseControl(b,"botonSeleccionado", "botonNoSeleccionado");
        cambiarClaseControl(PanePreVenta,"paneSeleccionado","paneNoSeleccionado");
        
        cambiarImagenBoton(imagenPreVentas,"recursos/005-payment-day-L.png");
       moverTriangulo(b.getLayoutY() - b.getHeight());
    }
    
    @FXML
    private void clickBotonEstadisticas(MouseEvent event) {
        limpiarBotones();
        esconderPanes();
        
        Button b = (Button)event.getSource();
        
        cambiarClaseControl(b,"botonSeleccionado", "botonNoSeleccionado");
        cambiarClaseControl(PaneEstadistica,"paneSeleccionado","paneNoSeleccionado");
        cambiarImagenBoton(imagenEstadisticas,"recursos/007-bar-chart-L.png");
        
        moverTriangulo(b.getLayoutY() - b.getHeight());
        
       
    }
    
    private void cargarPane(Pane pane, String ruta){
         
        try {
            Pane panelNuevo;
            panelNuevo = FXMLLoader.load(getClass().getResource(ruta));
            pane.getChildren().add(panelNuevo);
        } catch (IOException ex) {
            Logger.getLogger(InterfazController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void moverTriangulo(double y){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.1), (Node) triangulo);
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.1), (Node) triangulo);
        TranslateTransition tt3 = new TranslateTransition(Duration.seconds(0.1), (Node) triangulo);
        tt.setToX(-30);
        tt.setAutoReverse(true);
        tt.play();
      
        tt.setOnFinished((ActionEvent event) -> {
            triangulo.setVisible(false);
            tt2.setToY(y);
            tt2.play();
        });
        
    tt2.setOnFinished((ActionEvent event) -> {
        triangulo.setVisible(true);
        tt3.setToX(0);
        tt3.play();
        });
       
    }
}
