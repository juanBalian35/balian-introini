/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
    
    @FXML private JFXButton botonAgregarProducto1;
    @FXML private Pane paneDetallesVenta;
    @FXML private JFXButton botonBuscar;
    
    @FXML private JFXTextField TFTotal;
    @FXML private JFXTextField TFEmail;
    @FXML private JFXTextField TFNombre;
            
    @FXML private VBox vBoxRegistrar;
    
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
    
    @FXML
    private void clickBotonAgregarProducto(ActionEvent event){
        paneDetallesVenta.setDisable(false);
        
        Producto producto = tableViewBuscar.getSelectionModel().getSelectedItem();
        /*s
        boolean yaExistia = false;
        for(int i = 0 ; i < ces.size(); ++i){
            if(ces.get(i).id == producto.getId()){
                ces.get(i).setCantidad(ces.get(i).cantidad + 1);
                yaExistia = true;
            }
        }*/
        Pane panelNuevo;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PanelProductoCarrito.fxml")); 
            panelNuevo = fxmlLoader.load();
            PanelProductoCarritoController controller = fxmlLoader.<PanelProductoCarritoController>getController();
            controller.setProducto(producto);
            //controller.clickBotonSalir();
            vBoxRegistrar.getChildren().add(panelNuevo);
        } catch (IOException ex) {
            Logger.getLogger(EstadisticasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*
        if(!yaExistia){
            C c = new C(producto.getId(),producto.getPrecio(),producto.getMaterial(),
                producto.getDescripcion(),producto.getImagen(),producto.getNombre(),
                producto.getEnvases());
            ces.add(c);
        }
        
        double total = this.ces.stream().mapToDouble(x -> x.precioTotal).sum();
        
        TFTotal.setText(total + "");*/
        //tableViewProductos.getItems().setAll(ces);
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
           
           PdfPTable table = new PdfPTable(5); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {0.5f, 1.5f, 0.4f, 0.7f, 0.4f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell11 = new PdfPCell(new Paragraph("Nombre"));
            cell11.setBorderColor(BaseColor.BLACK);
            cell11.setPaddingLeft(10);
            cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Cantidad"));
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Precio Unitario"));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            PdfPCell cell4 = new PdfPCell(new Paragraph("Total"));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            //To avoid having the cell border and the content overlap, if you are having thick cell borders
            //cell1.setUserBorderPadding(true);
            //cell2.setUserBorderPadding(true);
            //cell3.setUserBorderPadding(true);

            table.addCell(cell1);
            table.addCell(cell11);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            
            table.addCell(cell1);
            table.addCell(cell11);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell1);
            table.addCell(cell11);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell1);
            table.addCell(cell11);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell1);
            table.addCell(cell11);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            
            document.add(new Paragraph("Fecha: "));
            
            document.add(table);
            
            document.add(new Paragraph("Total: 230"));
        
           document.close();
           writer.close();
        } 
        catch (DocumentException e){
           e.printStackTrace();
        } catch (FileNotFoundException e){
           e.printStackTrace();
        }
    }
}
