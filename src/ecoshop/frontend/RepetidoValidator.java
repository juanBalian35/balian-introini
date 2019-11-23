/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.frontend;

import com.jfoenix.validation.base.ValidatorBase;
import java.util.ArrayList;
import javafx.scene.control.TextInputControl;

/**
 *
 * @author Juanchi
 */
public class RepetidoValidator extends ValidatorBase {
    private ArrayList<String> existentes;
    
    public RepetidoValidator(){setMessage("Valor no debe de estar repetido");};
    
    public RepetidoValidator(ArrayList<String> existentes){
        setMessage("Valor no debe de estar repetido");
        this.existentes = existentes;
    };
    
    public RepetidoValidator(String mensaje, ArrayList<String> existentes){
        super(mensaje);
        this.existentes = existentes;
    };
    
    @Override
    protected void eval(){
        if(srcControl.get() instanceof TextInputControl){
            evaluar();
        }
    }
    
    public void setExistentes(ArrayList<String> existentes){
        this.existentes = existentes;
    }
    
    private void evaluar(){
        String texto = ((TextInputControl)srcControl.get()).getText();
        hasErrors.set(existentes.contains(texto));
    }
}
