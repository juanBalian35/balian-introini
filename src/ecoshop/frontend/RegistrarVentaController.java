/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXLabelFloatControl;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import ecoshop.backend.JSONAuxiliar;
import ecoshop.backend.Producto;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class RegistrarVentaController implements Initializable {
    private static final String NOMBRE_JSON = "productos";
    
    @FXML JFXButton botonConfirmarCompra;
    @FXML JFXComboBox BoxBuscarPor;
    @FXML JFXTextField TFBuscar;
    
    @FXML TableView<Producto> tableViewBuscar;
    @FXML private TableColumn<Producto, String> columnId;
    @FXML private TableColumn<Producto, String> columnNombre;
    @FXML private TableColumn<Producto, String> columnPrecio;
    @FXML private TableColumn<Producto, String> columnEnvases;
    
    @FXML private JFXButton botonAgregarProducto1;
    @FXML private Pane paneDetallesVenta;
    @FXML private JFXButton botonBuscar;
    
    @FXML TableView<C> tableViewProductos;
    @FXML private TableColumn<C, String> columnId1;
    @FXML private TableColumn<C, String> columnNombre1;
    @FXML private TableColumn<C, String> columnPrecioUnit;
    @FXML private TableColumn<C, String> columnEnvases1;
    @FXML private TableColumn<C, String> columnCantidad;
    @FXML private TableColumn<C, String> columnPrecioTotal;
    
    @FXML private JFXTextField TFTotal;
    @FXML private JFXTextField TFEmail;
    @FXML private JFXTextField TFNombre;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewBuscar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            botonAgregarProducto1.setDisable(newSelection == null);
        });
        
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
        
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnEnvases.setCellValueFactory(new PropertyValueFactory<>("envases"));
        
        columnId1.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPrecioUnit.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnEnvases1.setCellValueFactory(new PropertyValueFactory<>("envases"));
        columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        columnPrecioTotal.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));
        columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        
        validarCampo(TFEmail, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        validarCampo(TFNombre, 
               new String[]{"Campo obligatorio"}, 
               new ValidatorBase[]{new RequiredFieldValidator()});
        actualizarDatos();
    }    
    
    private void validarCampo(IFXLabelFloatControl campo, 
            String[] mensajes,
            ValidatorBase[] validators){
        for(int i = 0; i < validators.length; ++i){
            validators[i].setMessage(mensajes[i]);
            campo.getValidators().add(validators[i]);
            
            Image image = new Image(getClass().getResourceAsStream("recursos/attention.png"));
            ImageView icono = new ImageView(image);
            icono.setFitHeight(13);
            icono.setFitWidth(13);
            validators[i].setIcon(icono);
        }
      
        if(campo instanceof JFXTextField){
            ((JFXTextField)campo).focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal && campo.validate())
                    ((JFXTextField)campo).getStyleClass().add("error");
            });
        }
        else if(campo instanceof JFXComboBox){
            ((JFXComboBox)campo).focusedProperty().addListener((o, oldVal, newVal) -> {
                if (!newVal && campo.validate())
                    ((JFXComboBox)campo).getStyleClass().add("error");
            });
        }
    }
    
    private void actualizarDatos(){
        ArrayList<Producto> productos = 
                JSONAuxiliar.procesarArchivo(NOMBRE_JSON, Producto::parsearEntrySet);
        tableViewBuscar.getItems().setAll(productos);
    }
    
    @FXML
    private  void accionBoxBuscarPor(ActionEvent event) {
        String seleccion = ((String)BoxBuscarPor.getValue()).toLowerCase();
        if("todos".equals(seleccion)){
            TFBuscar.disableProperty().setValue(true);
            TFBuscar.promptTextProperty().setValue(null);
            TFBuscar.clear();
            actualizarDatos();
        }
        else{
            TFBuscar.disableProperty().setValue(false);
            TFBuscar.promptTextProperty().setValue((String) seleccion);
        }
    }
    
    @FXML
    private void clickBotonBuscar(ActionEvent event){
        String columna = ((String)BoxBuscarPor.getValue()).toLowerCase();
        
        JSONArray resultado = JSONAuxiliar.conseguirConColumna(TFBuscar.getText(), columna, NOMBRE_JSON, columna.equals("id"));
        
        ArrayList<Producto> productos = new ArrayList<>();
        
        for(int i = 0; i < resultado.size(); ++i){
            productos.add(Producto.parsearEntrySet(((JSONObject)resultado.get(i)).entrySet()));
        }
        
        tableViewBuscar.getItems().setAll(productos);
    }
    
    ArrayList<C> ces = new ArrayList<>();
    
    @FXML
    private void clickBotonAgregarProducto(ActionEvent event){
        paneDetallesVenta.setDisable(false);
        
        Producto producto = tableViewBuscar.getSelectionModel().getSelectedItem();

        boolean yaExistia = false;
        for(int i = 0 ; i < ces.size(); ++i){
            if(ces.get(i).id == producto.getId()){
                ces.get(i).setCantidad(ces.get(i).cantidad + 1);
                yaExistia = true;
            }
        }
        
        if(!yaExistia){
            C c = new C(producto.getId(),producto.getPrecio(),producto.getMaterial(),
                producto.getDescripcion(),producto.getImagen(),producto.getNombre(),
                producto.getEnvases());
            ces.add(c);
        }
        
        double total = this.ces.stream().mapToDouble(x -> x.precioTotal).sum();
        
        TFTotal.setText(total + "");
        tableViewProductos.getItems().setAll(ces);
    }
    
    
    
    @FXML
    void clickBotonConfirmarCompra(MouseEvent event)  {
        boolean emailValido = TFEmail.validate();
        boolean nombreValido = TFNombre.validate();
        if(!(emailValido && nombreValido)){
            return;
        }
        
        Document document = new Document();
        
        try{
           PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("carlitostevez.pdf"));
           document.open();
           document.add(new Paragraph("A Hello World PDF document."));
           document.close();
           writer.close();
        } 
        catch (DocumentException e){
           e.printStackTrace();
        } catch (FileNotFoundException e){
           e.printStackTrace();
        }
    }
    
    public class C{
        private int id;
        private double precio;
        private String material;
        private String descripcion;
        private String imagen;
        private String nombre;
        private ArrayList<Integer> envases;
        private int cantidad;
        private double precioTotal;
        
        public C(int id, double precio, String material, String descripcion,
                String imagen,String nombre, ArrayList<Integer> envases){
            this.id = id;
            this.precio = precio;
            this.material = material;
            this.descripcion = descripcion;
            this.imagen = imagen;
            this.nombre = nombre;
            this.envases = envases;
            
            this.cantidad = 1;
            this.precioTotal = precio;
        }
        
        public void setCantidad(int c){
            this.cantidad = c;
            this.precioTotal = precio * c;
        }
        
        public int getCantidad(){
            return this.cantidad;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public ArrayList<Integer> getEnvases() {
            return envases;
        }

        public void setEnvases(ArrayList<Integer> envases) {
            this.envases = envases;
        }

        public double getPrecioTotal() {
            return precioTotal;
        }

        public void setPrecioTotal(double precioTotal) {
            this.precioTotal = precioTotal;
        }
    }
}
