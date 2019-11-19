/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.controls.*;
import ecoshop.backend.JSONAuxiliar;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import org.json.simple.JSONObject;
import org.json.simple.JsonObject;

/*
 * FXML Controller class
 *
 * @author agustinintroini
 */
public class AdminProductosController implements Initializable {

    @FXML
    private JFXComboBox BoxBuscarPor;
    
    @FXML
    private JFXButton botonBuscar;
    
    @FXML
    private JFXTextField TFBuscar;
    
    @FXML
    private JFXTextField TBPrecio;
    
    @FXML
    private Label signoPeso;
    
    @FXML
    private Pane pane;
    
    @FXML
    private JFXButton botonAgregarProducto;
    
    boolean controlSignoPeso = false;
    UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {
        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {
            if (t.isReplaced()) 
                if(t.getText().matches("[^0-9]"))
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));

            if (t.isAdded()) {
                if ((t.getControlText().contains(".") && t.getText().matches("[^0-9]")) 
                        || t.getText().matches("[^0-9.]")) {
                    t.setText("");
                }
            }

            return t;
        }
    };
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TBPrecio.setTextFormatter(new TextFormatter<>(filter));    
      
        TFBuscar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
                botonBuscar.disableProperty().setValue(newValue.length() == 0);
            }
        });
        
        TBPrecio.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if(!controlSignoPeso && newValue.length()<=3){
                    TBPrecio.setText(oldValue);
                }
                controlSignoPeso = false;
            }
        });
          
        TBPrecio.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean wasFocused, Boolean isNowFocused) {
                if(isNowFocused){
                    signoPeso.setVisible(true);
                    if(!(TBPrecio.getText().length()>3)){
                        TBPrecio.setText("    ");
                    }
                }
                else if(TBPrecio.getText().length() == 4){
                    signoPeso.setVisible(false);
                    controlSignoPeso = true;
                    TBPrecio.setText("");
                }
            } 
        });
    }
 
    @FXML
    private  void accionBoxBuscarPor(ActionEvent event) {
        Object seleccion = BoxBuscarPor.getValue();
        TFBuscar.disableProperty().setValue(Boolean.FALSE);
        TFBuscar.promptTextProperty().setValue((String) seleccion);
    }
    
    @FXML
    private void clickBotonAgregarProducto(MouseEvent event){
        JSONObject nuevo  = new JSONObject();
        nuevo.put("id", "1");
        nuevo.put("nombre", "1");
        nuevo.put("material", "1");
        nuevo.put("precio", "1");
        nuevo.put("descripcion", "1");
        
        JSONAuxiliar.agregar(nuevo,"productos");
    }
  
}
