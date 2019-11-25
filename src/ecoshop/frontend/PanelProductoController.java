/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXButton;
import ecoshop.backend.CarritoSingleton;
import ecoshop.backend.ImagenesAuxiliar;
import ecoshop.backend.Producto;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */


public class PanelProductoController implements Initializable {
  @FXML
  private Pane paneImagen;
  @FXML
  private Label lblDescripcion;
  
     @FXML
    private Pane paneFondo;

     @FXML
    private Pane paneFondot;
    @FXML
    private Pane paneSecundario;
    @FXML
    private BorderPane barraAbajo;
  
      @FXML
    private JFXButton botonVerMas;
      @FXML
      private VBox precios;
      
   @FXML private Label lblPrecio;
   @FXML private Label lblNombre;
   @FXML private Label lblMaterial;
   
   @FXML private Pane paneDescuento;
   @FXML private Label lblDescuento;
  
      
   private StringProperty lblDescripcionProperty = new SimpleStringProperty();
   private StringProperty lblPrecioProperty = new SimpleStringProperty();
   private StringProperty lblNombreProperty = new SimpleStringProperty();
   private StringProperty lblMaterialProperty = new SimpleStringProperty();
   private StringProperty lblDescuentoProperty = new SimpleStringProperty();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblDescripcion.textProperty().bind(lblDescripcionProperty);
        lblPrecio.textProperty().bind(lblPrecioProperty);
        lblNombre.textProperty().bind(lblNombreProperty);
        lblMaterial.textProperty().bind(lblMaterialProperty);
        lblDescuento.textProperty().bind(lblDescuentoProperty);
        
        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResourceAsStream("recursos/imagenes/chia.jpg")),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            new BackgroundSize(100, 100,true, true, true,true));
        //then you set to your node
        paneImagen.setBackground(new Background(myBI));
        
        paneFondo.heightProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number o, Number n){
                System.out.println("aaaa " + n);
            }
        });
    }    
    
    @FXML
    private void BotonApretado(MouseEvent event) {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), paneSecundario);

        //paneFondo.setPrefHeight(430);
        if(paneSecundario.isVisible()){
             
           tt.setToY(120);
           tt.play();
          // paneFondo.setPrefHeight(220);
            tt.setOnFinished((ActionEvent evento) -> {
            paneSecundario.setVisible(false);
           paneFondot.setPrefHeight(320);
           botonVerMas.getStyleClass().remove("boton-abajo-cerrada");
        barraAbajo.getStyleClass().remove("barra-abajo-cerrada");
           botonVerMas.getStyleClass().add("boton-abajo-abierto");
        barraAbajo.getStyleClass().add("barra-abajo-abierta");
       
        });
           
        }else{
        paneSecundario.setVisible(true);
        tt.setToY(120);
        tt.play();
        botonVerMas.getStyleClass().remove("boton-abajo-abierto");
        barraAbajo.getStyleClass().remove("barra-abajo-abierta");
        botonVerMas.getStyleClass().add("boton-abajo-cerrada");
        barraAbajo.getStyleClass().add("barra-abajo-cerrada");
        paneFondot.setPrefHeight(320 + paneSecundario.getHeight());
        // Hay que agrandar la ventana a tamaño pane fondo + tamaño sombra de ventana
      //  paneFondo.getScene().getWindow().setHeight(465);
        }
    }
    
    
    private Producto producto;
    
    public void setProducto(Producto producto){
        this.producto = producto;
        
        lblDescripcionProperty.setValue(producto.getDescripcion());
        lblPrecioProperty.setValue("$ " + producto.getPrecio());
        lblNombreProperty.setValue(producto.getNombre());
        if(producto.getDescuento() != 0){
            Label lblPrecioAntes= new Label("$" + producto.getPrecio());
            lblPrecioAntes.getStyleClass().add(getClass().getResource("estilos/estilos.css").toExternalForm());
            lblPrecioAntes.getStyleClass().add("label1");
            precios.getChildren().add(lblPrecioAntes);
            lblPrecioAntes.toBack();
            lblPrecioProperty.setValue("$ " + (producto.getPrecio()-(producto.getPrecio()*producto.getDescuento()/100)));
        }
        
        Image image;
        if("".equals(producto.getImagen())){
            image = new Image(getClass().getResourceAsStream("recursos/empty-image.png"));
        }
        else{
            File f = new File(producto.getImagen());
            image = ImagenesAuxiliar.archivoAImagen(f);
        }
        BackgroundImage myBI = new BackgroundImage(image,
            BackgroundRepeat.SPACE, BackgroundRepeat.SPACE, BackgroundPosition.CENTER,
            new BackgroundSize(100, 100,true, true, true,true));

        lblMaterialProperty.setValue("•" + producto.getMaterial());
        paneImagen.setBackground(new Background(myBI));
        
        
        paneDescuento.setVisible(producto.getDescuento() != 0);
        paneDescuento.setVisible(producto.getDescuento() != 0);
        lblDescuentoProperty.set(producto.getDescuento() + "%");
    }
    
    @FXML
    public void botonCarrito(ActionEvent event){
        CarritoSingleton.getInstancia().agregarAlCarrito(producto);
        Toast.show(producto.getNombre() + " agregado al carrito exitosamente.", "OK", 5, lblPrecio);
    }
    
}
