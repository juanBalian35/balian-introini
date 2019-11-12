package ecoshop.frontend;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
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
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    private void cambiarClaseBoton(Button boton, String nuevaClase){
        if(nuevaClase.equals("botonSeleccionado"))
            boton.getStyleClass().remove("botonNoSeleccionado");
        else
            boton.getStyleClass().remove("botonSeleccionado");
        
        boton.getStyleClass().add(nuevaClase);
    }
    
    private void cambiarImagenBoton(ImageView imagen, String ruta){
        imagen.setImage(new Image(getClass().getResourceAsStream(ruta)));
    }
    
    private void limpiarBoton(Button boton, ImageView imageView, String ruta){
        cambiarClaseBoton(boton,"botonNoSeleccionado");
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
    
    @FXML
    private void clickBotonAdministrarProductos(MouseEvent event) {
        limpiarBotones();
        
        Button b = (Button)event.getSource();
        cambiarClaseBoton(b,"botonSeleccionado");
        
        cambiarImagenBoton(imagenAdministrarProductos,"recursos/006-basket-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight() + 5);
    }
    
    @FXML
    private void clickBotonAdministrarEnvases(MouseEvent event) {
        limpiarBotones();
        
        Button b = (Button)event.getSource();
        cambiarClaseBoton(b,"botonSeleccionado");
        
        cambiarImagenBoton(imagenAdministrarEnvases,"recursos/001-plastic-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight() + 5);
    }
    
    @FXML
    private void clickBotonPuntosDeVenta(MouseEvent event) {
        limpiarBotones();
        
        Button b = (Button)event.getSource();
        cambiarClaseBoton(b,"botonSeleccionado");
        
        cambiarImagenBoton(imagenPuntosDeVenta,"recursos/002-street-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight() + 5);
    }
    
    @FXML
    private void clickBotonRegistrarVentas(MouseEvent event) {
        limpiarBotones();
        
        Button b = (Button)event.getSource();
        cambiarClaseBoton(b,"botonSeleccionado");
        
        cambiarImagenBoton(imagenRegistrarVentas,"recursos/003-swipe-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight() + 5);
    }
    
    @FXML
    private void clickBotonPreVentas(MouseEvent event) {
        limpiarBotones();
        
        Button b = (Button)event.getSource();
        cambiarClaseBoton(b,"botonSeleccionado");
        
        cambiarImagenBoton(imagenPreVentas,"recursos/005-payment-day-L.png");
       moverTriangulo(b.getLayoutY() - b.getHeight() + 5);
    }
    
    @FXML
    private void clickBotonEstadisticas(MouseEvent event) {
        limpiarBotones();
        
        Button b = (Button)event.getSource();
        
        cambiarClaseBoton(b,"botonSeleccionado");
        cambiarImagenBoton(imagenEstadisticas,"recursos/007-bar-chart-L.png");
        moverTriangulo(b.getLayoutY() - b.getHeight() + 5);
    }
    
    private void moverTriangulo(double y){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.3), (Node) triangulo);
        tt.setToY(y);
        tt.setAutoReverse(true);
 
     tt.play();
    }
}
