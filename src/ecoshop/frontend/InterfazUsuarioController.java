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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class InterfazUsuarioController implements Initializable {
    @FXML 
    private Button botonCarrito;
    
    @FXML 
    private Button botonPreCompras;
    
    @FXML
    private Button botonPuntosDeVenta;
    
    @FXML
    private Button botonTienda;
    
   
    
    @FXML 
     private Shape triangulo;
    
    @FXML
    private Pane BarraBoton;
  
    @FXML
    private Pane paneTienda;
    
    @FXML
    private Pane panePreCompras; 
    
    @FXML
    private Pane panePuntosDeVenta;
    
    @FXML
    private Pane paneCarrito;
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                cargarPane(paneTienda, "Tienda.fxml");
               // cargarPane(paneCarrito, "PreVentas.fxml");
                //cargarPane(panePreCompras, "RegistrarVenta.fxml");
                //cargarPane(panePuntosDeVenta, "PuntosDeVenta.fxml");
              

    }    
  
    @FXML
    private void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private double x, y;
     
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
    
    private void limpiarBoton(Button boton){
        cambiarClaseControl(boton,"botonNoSeleccionado", "botonSeleccionado");
        //cambiarImagenBoton(imageView, ruta);
    }
    
    private void limpiarBotones(){
        limpiarBoton(botonCarrito);
        limpiarBoton(botonPreCompras);
        limpiarBoton(botonPuntosDeVenta);
        limpiarBoton(botonTienda);
       
    }
    
    private void esconderPanes(){
        paneCarrito.setVisible(false);
       panePreCompras.setVisible(false);
       paneTienda.setVisible(false);
        panePuntosDeVenta.setVisible(false);
      
    }
    
   @FXML
    private void clickBotonTienda(MouseEvent event) {
        controlarClick(botonTienda, paneTienda);
    }
    
    @FXML
    private void clickBotonCarrito(MouseEvent event) {
        controlarClick(botonCarrito, paneCarrito);
    }
    
    @FXML
    private void clickBotonPuntosDeVenta(MouseEvent event) {
        controlarClick(botonPuntosDeVenta, panePuntosDeVenta);
    }
    
   
    
    @FXML
    private void clickBotonPreCompras(MouseEvent event) {
        controlarClick(botonPreCompras, panePreCompras);
    }
   
    
  
    
     
 
    
    /**
     * @param boton boton recien clickeado
     * @param pane panel a ser priorizado
     * @param imageView imageView que contiene la imagen para ser cambiada
     * @param imagen cadena de texto que contiene la ruta de la imagen a poner
     *               en el imageView
     */
    private void controlarClick(Button boton, Pane pane){
        limpiarBotones();
        esconderPanes();
        
        cambiarClaseControl(boton,"botonSeleccionado", "botonNoSeleccionado");
        pane.setVisible(true);
        
        moverTriangulo(boton.getLayoutY() );
        moverBarraBoton(boton.getLayoutY());
    }
    

    
    private void cargarPane(Pane pane, String ruta){
         
        try {
            Pane panelNuevo;
            panelNuevo = FXMLLoader.load(getClass().getResource(ruta));
            pane.getChildren().add(panelNuevo);
        } catch (IOException ex) {
            Logger.getLogger(InterfazUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void moverBarraBoton(double y){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), (Node) BarraBoton);
        tt.setToY(y);
            tt.play();
    }
}
