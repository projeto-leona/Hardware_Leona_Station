/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHardware;

import br.leona.hardware.services.Arduino;
import br.leona.hardware.services.Pantilt;
import br.leona.hardware.services.Port;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    }
    
    @Before
    public void setUp() {
        testTurnOn();
        testIsOn();
    }
    
    @After
    public void tearDown() {   
        testTurnOff();  
        testIsOff();
        try {
            testReset();
        } catch (IOException ex) {
            Logger.getLogger(JUnitTestHardware.class.getName()).log(Level.SEVERE, null, ex);
        }
        testIsOn();
        /*
        try {
            testClose();
            testIsOff();
        } catch (IOException ex) {
            Logger.getLogger(JUnitTestHardware.class.getName()).log(Level.SEVERE, null, ex);
        }
                */
    }
              
    //@Test
    public void testIsOn() {
        assertEquals(1, pantilt.isOn());
    }   
   
    public void testIsOff() {
        assertEquals(0, pantilt.isOn());
    }   
     
    public void testTurnOn() {
        assertEquals(1, pantilt.turnOn()); 
    }   
   
    public void testTurnOff() {
        assertEquals(1, pantilt.turnOff());
    }  
      
    //@Test
    public void testReset() throws IOException {
        assertEquals(1, pantilt.reset()); 
    }
    
    public void testClose() throws IOException {
        assertEquals(1, pantilt.close()); 
    }     
    
    @Test
    public void testMoveLeft() {
        for(int i=0; i< 360; i+=10){          
            String degrees = Integer.toString(i);
            assertEquals(1, pantilt.moveDirection(degrees, "LEFT"));
        }    
    }    
    
    @Test
    public void testMoveRight() {
         for(int i=0; i< 360; i+=10){          
            String degrees = Integer.toString(i);
            assertEquals(1, pantilt.moveDirection(degrees, "RIGHT"));
         }
    }
    
    @Test
    public void testMoveUp() {
         for(int i=0; i< 86; i+=10){          
            String degrees = Integer.toString(i);
            assertEquals(1, pantilt.moveDirection(degrees, "UP"));
         }
    }    

    @Test
    public void testMoveDown() {
         for(int i=0; i< 86; i+=10){          
            String degrees = Integer.toString(i);
            assertEquals(1, pantilt.moveDirection(degrees, "DOWN"));
         }
    } 
    
    @Test
    public void testGetService() {
        System.out.println("("+port.getServiceType()+")");
        System.out.println("("+port.getService().getName()+", "+port.getService().getStatus()+")");
        assertEquals("Ativo", port.getService().getStatus());
        System.out.println("("+pantilt.getServiceType()+")");
        System.out.println("("+pantilt.getService().getName()+", "+pantilt.getService().getStatus()+")");
        assertEquals("Ativo", pantilt.getService().getStatus());    
        System.out.println("("+arduino.getServiceType()+")");
        System.out.println("("+arduino.getService().getName()+", "+arduino.getService().getStatus()+")");
        assertEquals("Ativo", arduino.getService().getStatus());
    } 
    
}
