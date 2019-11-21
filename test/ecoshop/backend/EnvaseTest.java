/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoshop.backend;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author agustinintroini
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
       instance.setId("234AB");
        String expResult = "234AB";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Envase.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Envase instance = new Envase();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImagen method, of class Envase.
     */
    @Test
    public void testGetImagen() {
        System.out.println("getImagen");
        Envase instance = new Envase();
        String expResult = "";
        String result = instance.getImagen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImagen method, of class Envase.
     */
    @Test
    public void testSetImagen() {
        System.out.println("setImagen");
        String imagen = "";
        Envase instance = new Envase();
        instance.setImagen(imagen);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContadorReuso method, of class Envase.
     */
    @Test
    public void testGetContadorReuso() {
        System.out.println("getContadorReuso");
        Envase instance = new Envase();
        int expResult = 0;
        int result = instance.getContadorReuso();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContadorReuso method, of class Envase.
     */
    @Test
    public void testSetContadorReuso() {
        System.out.println("setContadorReuso");
        int contadorReuso = 0;
        Envase instance = new Envase();
        instance.setContadorReuso(contadorReuso);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoria method, of class Envase.
     */
    @Test
    public void testGetCategoria() {
        System.out.println("getCategoria");
        Envase instance = new Envase();
      //  Envase.Categorias expResult = null;
       // Envase.Categorias result = instance.getCategoria();
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoria method, of class Envase.
     */
    @Test
    public void testSetCategoria() {
        System.out.println("setCategoria");
//        Envase.Categorias categoria = null;
        Envase instance = new Envase();
  //      instance.setCategoria(categoria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNombre method, of class Envase.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Envase instance = new Envase();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNombre method, of class Envase.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String nombre = "";
        Envase instance = new Envase();
        instance.setNombre(nombre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
