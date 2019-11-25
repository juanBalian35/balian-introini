/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
public class EnvaseTest {
    private Envase instance;
    
    public EnvaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Envase();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Envase.
     */
    @Test
    public void testGetId() {
        instance.setId(234);
        int expResult = 234;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Envase.
     */
    @Test
    public void testSetId() {
        int id = 0;
        instance.setId(id);
    }

    /**
     * Test of getImagen method, of class Envase.
     */
    @Test
    public void testGetImagen() {
        instance.setImagen("imagenes/imagen.jpg");
        String expResult = "imagenes/imagen.jpg";
        String result = instance.getImagen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setImagen method, of class Envase.
     */
    @Test
    public void testSetImagen() {
        String imagen = "imagenes/imagen.jpg";
        instance.setImagen(imagen);
    }

    /**
     * Test of getContadorReuso method, of class Envase.
     */
    @Test
    public void testGetContadorReuso() {
        instance.setContadorReuso(4);
        int expResult = 4;
        int result = instance.getContadorReuso();
        assertEquals(expResult, result);
    }

    /**
     * Test of setContadorReuso method, of class Envase.
     */
    @Test
    public void testSetContadorReuso() {
        int contadorReuso = 0;
        instance.setContadorReuso(contadorReuso);
    }

    /**
     * Test of sumarUnoContadorReuso method, of class Envase.
     */
    @Test
    public void testSumarUnoContadorReuso() {
        instance.setContadorReuso(4);
        int expResult = 5;
        int result = instance.sumarUnoContadorReuso();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCategoria method, of class Envase.
     */
    @Test
    public void testGetCategoria() {
        instance.setCategoria("Metal");
        String expResult = "Metal";
        String result = instance.getCategoria();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCategoria method, of class Envase.
     */
    @Test
    public void testSetCategoria() {
        String categoria = "Metal";
        instance.setCategoria(categoria);
    }

    /**
     * Test of getNombre method, of class Envase.
     */
    @Test
    public void testGetNombre() {
        instance.setNombre("Lata");
        String expResult = "Lata";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class Envase.
     */
    @Test
    public void testSetNombre() {
        String nombre = "Lata";
        instance.setNombre(nombre);
    }

    /**
     * Test of parsearEntrySet method, of class Envase.
     */
    @Test
    public void testParsearEntrySet() {
        Set<Map.Entry<String, String>> entrySet = new HashSet<>();
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("id","1"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("nombre","Lata"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("categoria","Metal"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("imagen","imagen/lata.png"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("contadorReuso","0"));
        Envase expResult =  new Envase();
        expResult.setId(1);
        expResult.setNombre("Lata");
        expResult.setCategoria("Metal");
        expResult.setImagen("imagen/lata.png");
        expResult.setContadorReuso(0);
        Envase result = Envase.parsearEntrySet(entrySet);
        
        assertEquals(result.getId(), expResult.getId());
        assertEquals(result.getNombre(), expResult.getNombre());
        assertEquals(result.getCategoria(), expResult.getCategoria());
        assertEquals(result.getImagen(), expResult.getImagen());
        assertEquals(result.getContadorReuso(), expResult.getContadorReuso());
    }
    
}
