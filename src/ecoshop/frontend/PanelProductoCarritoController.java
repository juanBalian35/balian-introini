/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.JFXComboBox;
import ecoshop.backend.ImagenesAuxiliar;
import ecoshop.backend.Producto;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class PanelProductoCarritoController implements Initializable {
    private Producto producto;
    @FXML private Label lblPrecioTotal;
    @FXML private Label lblNombre;
    @FXML private Label lblMaterial;
    @FXML private Label lblPrecioUnitario;
    @FXML private Label lblId;
    @FXML private JFXComboBox boxEnvases;
    @FXML private ImageView imageView;
    @FXML private TextField TFCant;
    private int cantidad;
    
   private StringProperty lblPrecioTotalProperty = new SimpleStringProperty();
   private StringProperty lblNombreProperty = new SimpleStringProperty();
   private StringProperty lblMaterialProperty = new SimpleStringProperty();
   private StringProperty lblPrecioUnitarioProperty = new SimpleStringProperty();
   private StringProperty lblIdProperty = new SimpleStringProperty();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblPrecioTotal.textProperty().bind(lblPrecioTotalProperty);
        lblNombre.textProperty().bind(lblNombreProperty);
        lblMaterial.textProperty().bind(lblMaterialProperty);
        lblPrecioUnitario.textProperty().bind(lblPrecioUnitarioProperty);
        lblId.textProperty().bind(lblIdProperty);
        cantidad = 1;
        
        TFCant.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                int val = Integer.parseInt(newValue);
                lblPrecioTotalProperty.set((producto.getPrecio() * val) + "");
                cantidad = val;
            }
            catch(Exception e){
                //e.printStackTrace();
            }
        });
    }    
    
    public void setProducto(Producto producto){
        this.producto = producto;

        lblMaterialProperty.set(producto.getMaterial());
        lblNombreProperty.set(producto.getNombre());
        lblPrecioTotalProperty.set(producto.getPrecio() + "");
        lblPrecioUnitarioProperty.set(producto.getPrecio() + "");
        lblIdProperty.set("#ID: " + producto.getId());
        
        Image image;
        if("".equals(producto.getImagen())){
            image = new Image(getClass().getResourceAsStream("recursos/empty-image.png"));
        }
        else{
            File f = new File(producto.getImagen());
            image = ImagenesAuxiliar.archivoAImagen(f);
        }
        imageView.setImage(image);
        
        boxEnvases.getItems().setAll(producto.getEnvases());
    }
    
    public int getCantidad(){
        return cantidad;
    }
    
    public Integer getEnvaseSeleccionado(){
        Object o = boxEnvases.getSelectionModel().getSelectedItem();
        if(o == null)
            return null;
        
        return (Integer)o;
    }
    
    public void clickBotonQuit(ActionEvent event){
        System.out.println("asdasd");
    }
}
