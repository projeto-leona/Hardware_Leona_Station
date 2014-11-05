/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHardware;

import br.leona.hardware.model.Arduino;
import br.leona.hardware.service.Pantilt;
import br.leona.hardware.model.Service;
import br.leona.hardware.service.Port;
import br.leona.hardware.service.RetrieveService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
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
public class JUnitTestHardware {   
    private static Port port;
    private static Pantilt pantilt;
    private static Arduino arduino;
        
    public JUnitTestHardware() {  
    }
    
    @BeforeClass
    public static void setUpClass() {         
       port = new Port();
       pantilt = new Pantilt();
       arduino = pantilt.getArduino();
    }
    
    @AfterClass
    public static void tearDownClass() {   
       pantilt.close();
    }
    
    @Before
    public void setUp() {
        assertEquals(1, pantilt.turnOn());
    }
    
    @After
    public void tearDown() {
        assertEquals(1, pantilt.turnOff()); 
    }
   
    @Test
    public void testMoveLeft() {
        assertEquals(1, pantilt.moveDirection("50", "LEFT"));
    }    

    @Test
    public void testMoveRight() {
        assertEquals(1, pantilt.moveDirection("50", "RIGHT"));
    }
    
    @Test
    public void testMoveUp() {
        assertEquals(1, pantilt.moveDirection("50", "UP"));
    }    

    @Test
    public void testMoveDown() {
        assertEquals(1, pantilt.moveDirection("50", "DOWN"));
    }
         
    @Test
    public void testReset() throws IOException {
        assertEquals(1, pantilt.reset());
    }
        
    @Test
    public void testIsOn() throws IOException {
        assertEquals(1, pantilt.isOn());
    }   
    
    @Test
    public void testGetService() throws IOException {
        System.out.println("("+port.getService().getName()+", "+port.getService().getStatus()+")");
        assertEquals("Ativo", port.getService().getStatus());
        System.out.println("("+pantilt.getService().getName()+", "+pantilt.getService().getStatus()+")");
        assertEquals("Ativo", pantilt.getService().getStatus());        
        System.out.println("("+arduino.getService().getName()+", "+arduino.getService().getStatus()+")");
        assertEquals("Ativo", arduino.getService().getStatus());
    }  
  
}
