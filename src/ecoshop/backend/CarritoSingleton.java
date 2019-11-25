/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;

import java.util.ArrayList;

/**
 *
 * @author juanchi
 */
public class CarritoSingleton {
    private static CarritoSingleton instancia = null;
    private ArrayList<Producto> productos;
    
    private CarritoSingleton(){
        productos = new ArrayList<>();
    }
    
    public static CarritoSingleton getInstancia(){
        if(instancia == null)
            instancia = new CarritoSingleton();
        
        return instancia;
    }
    
    public void agregarAlCarrito(Producto producto){
        productos.add(producto);
    }
    
    public ArrayList<Producto> getProductos(){
        return productos;
    }
}
