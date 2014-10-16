/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TesteCamera;

import br.leona.hardware.model.Camera;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin_2
 */
public class JUnitTestCamera {
    private Camera camera;
    
    public JUnitTestCamera() {
        camera = new Camera();
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {   
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsLigado() {
        assertFalse(camera.isLigado());
    }
    
    @Test
    public void testSetLigado() {
        camera.setLigado(true);       
        assertTrue(camera.isLigado());
    }
    
    @Test
    public void testDirUp() {
        assertEquals(10, camera.setDirecao("UP", "10"));
    }
}
