/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHardware;

import br.leona.hardware.service.Arduino;
import br.leona.hardware.service.Pantilt;
import br.leona.hardware.service.Port;
import java.io.IOException;
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
        for(int i=0; i< 360; i+=10){          
            String degrees = new Integer(i).toString();
            assertEquals(1, pantilt.moveDirection(degrees, "LEFT"));
        }    
    }    

    @Test
    public void testMoveRight() {
         for(int i=0; i< 360; i+=10){          
            String degrees = new Integer(i).toString();
            assertEquals(1, pantilt.moveDirection(degrees, "RIGHT"));
         }
    }
    
    @Test
    public void testMoveUp() {
         for(int i=0; i< 86; i+=10){          
            String degrees = new Integer(i).toString();
            assertEquals(1, pantilt.moveDirection(degrees, "UP"));
         }
    }    

    @Test
    public void testMoveDown() {
         for(int i=0; i< 86; i+=10){          
            String degrees = new Integer(i).toString();
            assertEquals(1, pantilt.moveDirection(degrees, "DOWN"));
         }
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
