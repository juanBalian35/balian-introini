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
    private Pane BarraBoton;
  
    @FXML
    private Pane paneRegistrarVenta;
    
    @FXML
    private Pane panePreVenta; 
    
    @FXML
    private Pane panePuntosDeVenta;
    
    @FXML
    private Pane paneAdministrarProducto;
    
    @FXML
    private Pane paneAdministrarEnvase;
    
    @FXML
    private Pane paneEstadistica;
    
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                cargarPane(paneAdministrarProducto, "AdminProductos.fxml");
                cargarPane(panePreVenta, "PreVentas.fxml");
                cargarPane(paneRegistrarVenta, "RegistrarVenta.fxml");
                cargarPane(panePuntosDeVenta, "PuntosDeVenta.fxml");
                cargarPane(paneAdministrarEnvase, "AdminEnvases.fxml");
                cargarPane(paneEstadistica, "Estadisticas.fxml");                

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
    
    private void limpiarBoton(Button boton, ImageView imageView, String ruta){
        cambiarClaseControl(boton,"botonNoSeleccionado", "botonSeleccionado");
        //cambiarImagenBoton(imageView, ruta);
    }
    
    private void limpiarBotones(){
        limpiarBoton(botonAdministrarProductos, imagenAdministrarProductos, "recursos/AdminProductosBLANCO.png");
        limpiarBoton(botonAdministrarEnvases, imagenAdministrarEnvases, "recursos/AdminEnvaseBLANCO.png");
        limpiarBoton(botonPuntosDeVenta, imagenPuntosDeVenta, "recursos/pdvBLANCO.png");
        limpiarBoton(botonRegistrarVentas, imagenRegistrarVentas, "recursos/RegistrarVentaBLANCO.png");
        limpiarBoton(botonPreVentas, imagenPreVentas, "recursos/PreventasBLANCO.png");
        limpiarBoton(botonEstadisticas, imagenEstadisticas, "recursos/EstadisticasBLANCO.png");
    }
    
    private void esconderPanes(){
        cambiarClaseControl(paneRegistrarVenta,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(panePreVenta,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(paneAdministrarProducto,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(panePuntosDeVenta,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(paneAdministrarEnvase,"paneNoSeleccionado", "paneSeleccionado");
        cambiarClaseControl(paneEstadistica,"paneNoSeleccionado", "paneSeleccionado");
    }
    
   @FXML
    private void clickBotonAdministrarProductos(MouseEvent event) {
        controlarClick(botonAdministrarProductos, paneAdministrarProducto, imagenAdministrarProductos,"recursos/AdminProductosAZUL.png");
    }
    
    @FXML
    private void clickBotonAdministrarEnvases(MouseEvent event) {
        controlarClick(botonAdministrarEnvases, paneAdministrarEnvase, imagenAdministrarEnvases,"recursos/AdminEnvaseAZUL.png");
    }
    
    @FXML
    private void clickBotonPuntosDeVenta(MouseEvent event) {
        controlarClick(botonPuntosDeVenta, panePuntosDeVenta, imagenPuntosDeVenta,"recursos/pdvAZUL.png");
    }
    
    @FXML
    private void clickBotonRegistrarVentas(MouseEvent event) {
        controlarClick(botonRegistrarVentas, paneRegistrarVenta, imagenRegistrarVentas,"recursos/RegistrarVentaAZUL.png");
    }
    
    @FXML
    private void clickBotonPreVentas(MouseEvent event) {
        controlarClick(botonPreVentas, panePreVenta, imagenPreVentas,"recursos/PreventasAZUL.png");
    }
    
    @FXML
    private void clickBotonEstadisticas(MouseEvent event) {
        controlarClick(botonEstadisticas, paneEstadistica, imagenEstadisticas,"recursos/EstadisticasAZUL.png");
    }
    
     @FXML
    private void hoverAdminEnvases(MouseEvent event) {
       // cambiarImagenBoton(imagenAdministrarEnvases,"recursos/AdminEnvaseAZUL.png");
    }
    
     
 
    
    /**
     * @param boton boton recien clickeado
     * @param pane panel a ser priorizado
     * @param imageView imageView que contiene la imagen para ser cambiada
     * @param imagen cadena de texto que contiene la ruta de la imagen a poner
     *               en el imageView
     */
    private void controlarClick(Button boton, Pane pane, ImageView imageView, String imagen){
        limpiarBotones();
        esconderPanes();
        
        cambiarClaseControl(boton,"botonSeleccionado", "botonNoSeleccionado");
        cambiarClaseControl(pane,"paneSeleccionado","paneNoSeleccionado");
       // cambiarImagenBoton(imageView,imagen);
        
        moverTriangulo(boton.getLayoutY() );
        moverBarraBoton(boton.getLayoutY());
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
    
    private void moverBarraBoton(double y){
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), (Node) BarraBoton);
        tt.setToY(y);
            tt.play();
    }
}
