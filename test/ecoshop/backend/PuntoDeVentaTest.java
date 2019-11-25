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
public class PuntoDeVentaTest {
    PuntoDeVenta instance;
    
    public PuntoDeVentaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new PuntoDeVenta();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class PuntoDeVenta.
     */
    @Test
    public void testGetId() {
        instance.setId(33);
        int expResult = 33;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class PuntoDeVenta.
     */
    @Test
    public void testSetId() {
        int id = 1;
        instance.setId(id);
    }

    /**
     * Test of getDepartamento method, of class PuntoDeVenta.
     */
    @Test
    public void testGetDepartamento() {
        instance.setDepartamento("Maldonado");
        String expResult = "Maldonado";
        String result = instance.getDepartamento();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDepartamento method, of class PuntoDeVenta.
     */
    @Test
    public void testSetDepartamento() {
        String departamento = "";
        instance.setDepartamento(departamento);
    }

    /**
     * Test of getCiudad method, of class PuntoDeVenta.
     */
    @Test
    public void testGetCiudad() {
        instance.setCiudad("Punta del Este");
        String expResult = "Punta del Este";
        String result = instance.getCiudad();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCiudad method, of class PuntoDeVenta.
     */
    @Test
    public void testSetCiudad() {
        String ciudad = "";
        instance.setCiudad(ciudad);
    }

    /**
     * Test of getCalle method, of class PuntoDeVenta.
     */
    @Test
    public void testGetCalle() {
        instance.setCalle("Bulevar España");
        String expResult = "Bulevar España";
        String result = instance.getCalle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCalle method, of class PuntoDeVenta.
     */
    @Test
    public void testSetCalle() {
        String calle = "";
        instance.setCalle(calle);
    }

    /**
     * Test of getNumero method, of class PuntoDeVenta.
     */
    @Test
    public void testGetNumero() {
        instance.setNumero("1383");
        String expResult = "1383";
        String result = instance.getNumero();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNumero method, of class PuntoDeVenta.
     */
    @Test
    public void testSetNumero() {
        String numero = "";
        instance.setNumero(numero);
    }

    /**
     * Test of getNombre method, of class PuntoDeVenta.
     */
    @Test
    public void testGetNombre() {
        instance.setNombre("Greengreen");
        String expResult = "Greengreen";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNombre method, of class PuntoDeVenta.
     */
    @Test
    public void testSetNombre() {
        String nombre = "";
        instance.setNombre(nombre);
    }

    /**
     * Test of getDireccion method, of class PuntoDeVenta.
     */
    @Test
    public void testGetDireccion() {
        instance.setCalle("Av. Millan");
        instance.setNumero("4734");
        instance.setCiudad("Montevideo");
        instance.setDepartamento("Montevideo");
        
        String expResult = "Av. Millan 4734, Montevideo, Montevideo";
        String result = instance.getDireccion();
        assertEquals(expResult, result);
    }

    /**
     * Test of parsearEntrySet method, of class PuntoDeVenta.
     */
    @Test
    public void testParsearEntrySet() {
        Set<Map.Entry<String, String>> entrySet = new HashSet<>();
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("id","1"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("nombre","Greengreen"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("departamento","Flores"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("ciudad","Trinidad"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("calle","Antonietta"));
        entrySet.add(new AbstractMap.SimpleEntry<String, String>("numero","3473"));
        PuntoDeVenta expResult =  new PuntoDeVenta();
        expResult.setId(1);
        expResult.setNombre("Greengreen");
        expResult.setDepartamento("Flores");
        expResult.setCiudad("Trinidad");
        expResult.setCalle("Antonietta");
        expResult.setNumero("3473");
        PuntoDeVenta result = PuntoDeVenta.parsearEntrySet(entrySet);
        
        assertEquals(result.getId(), expResult.getId());
        assertEquals(result.getNombre(), expResult.getNombre());
        assertEquals(result.getDepartamento(), expResult.getDepartamento());
        assertEquals(result.getCiudad(), expResult.getCiudad());
        assertEquals(result.getCalle(), expResult.getCalle());
        assertEquals(result.getNumero(), expResult.getNumero());
    }
    
}
