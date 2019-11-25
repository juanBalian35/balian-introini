/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juanchi
 */
public class ProductoTest {
    Producto instance;
    
    public ProductoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Producto();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetDescuento() {
        instance.setDescuento(34);
        int expResult = 34;
        int result = instance.getDescuento();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescuento method, of class Producto.
     */
    @Test
    public void testSetDescuento() {
        int descuento = 0;
        instance.setDescuento(descuento);
    }

    /**
     * Test of getNombre method, of class Producto.
     */
    @Test
    public void testGetNombre() {
        instance.setNombre("Manzana");
        String expResult = "Manzana";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Producto.
     */
    @Test
    public void testSetNombre() {
        String nombre = "";
        instance.setNombre(nombre);
    }

    /**
     * Test of getId method, of class Producto.
     */
    @Test
    public void testGetId() {
        instance.setId(233);
        int expResult = 233;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Producto.
     */
    @Test
    public void testSetId() {
        int id = 0;
        instance.setId(id);
    }

    /**
     * Test of getPrecio method, of class Producto.
     */
    @Test
    public void testGetPrecio() {
        instance.setPrecio(78.33);
        double expResult = 78.33;
        double result = instance.getPrecio();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPrecio method, of class Producto.
     */
    @Test
    public void testSetPrecio() {
        double precio = 0.0;
        instance.setPrecio(precio);
    }

    /**
     * Test of getMaterial method, of class Producto.
     */
    @Test
    public void testGetMaterial() {
        instance.setMaterial("Organico");
        String expResult = "Organico";
        String result = instance.getMaterial();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaterial method, of class Producto.
     */
    @Test
    public void testSetMaterial() {
        String material = "Organico";
        instance.setMaterial(material);
    }

    /**
     * Test of getDescripcion method, of class Producto.
     */
    @Test
    public void testGetDescripcion() {
        instance.setDescripcion("Deliciosas manzanas de color rojo.");
        String expResult = "Deliciosas manzanas de color rojo.";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescripcion method, of class Producto.
     */
    @Test
    public void testSetDescripcion() {
        String descripcion = "";
        instance.setDescripcion(descripcion);
    }

    /**
     * Test of getImagen method, of class Producto.
     */
    @Test
    public void testGetImagen() {
        instance.setImagen("imagenes/manzanas_rojas.png");
        String expResult = "imagenes/manzanas_rojas.png";
        String result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImagen method, of class Producto.
     */
    @Test
    public void testSetImagen() {
        String imagen = "";
        instance.setImagen(imagen);
    }

    /**
     * Test of getEnvases method, of class Producto.
     */
    @Test
    public void testGetEnvases() {
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(1);
        ar.add(2);
        ar.add(3);
        ar.add(4);
        instance.setEnvases(ar);
        ArrayList<Integer> expResult = new ArrayList<>();
        expResult.add(1);
        expResult.add(2);
        expResult.add(3);
        expResult.add(4);
        ArrayList<Integer> result = instance.getEnvases();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEnvases method, of class Producto.
     */
    @Test
    public void testSetEnvases() {
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(1);
        ar.add(2);
        ar.add(3);
        ar.add(4);
        instance.setEnvases(ar);
    }

    /**
     * Test of parsearEntrySet method, of class Producto.
     */
    @Test
    public void testParsearEntrySet() {
        Set<Map.Entry<String, String>> entrySet = new HashSet<>();
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("id","1"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("precio",
                "345.23"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("material",
                "Organico"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("descripcion",
                "Manzanas deliciosas para comer toda la familia."));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("imagen",
                "imagenes/manzanas_verdes.png"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("nombre",
                "Manzanas verdes"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("descuento",
                "50"));
        Producto expResult =  new Producto();
        expResult.setId(1);
        expResult.setPrecio(345.23);
        expResult.setMaterial("Organico");
        expResult.setDescripcion("Manzanas deliciosas para comer toda la familia.");
        expResult.setImagen("imagenes/manzanas_verdes.png");
        expResult.setNombre("Manzanas verdes");
        expResult.setDescuento(50);
        Producto result = Producto.parsearEntrySet(entrySet);
        
        assertEquals(result.getId(), expResult.getId());
        assertEquals(result.getNombre(), expResult.getNombre());
        assertEquals(result.getMaterial(), expResult.getMaterial());
        assertEquals(result.getDescripcion(), expResult.getDescripcion());
        assertEquals(result.getImagen(), expResult.getImagen());
        assertEquals(result.getPrecio(), expResult.getPrecio(), 0.0);
        assertEquals(result.getDescuento(), expResult.getDescuento());
    }

    /**
     * Test of equals method, of class Producto.
     */
    @Test
    public void testEquals() {
        instance = new Producto();
        Object other = null;
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
    
}
