/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHardware;

import br.leona.hardware.model.Pantilt;
import br.leona.hardware.model.Service;
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
    private static Pantilt pantilt;
    private final List<Service> listServices = new ArrayList<Service>();
    
    
    public JUnitTestHardware() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       pantilt = new Pantilt();
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
    public void testListServicos() { 
       List<Service> listSet = new ArrayList<Service>();
       for(int i = 0; i <10; i++){
           Service service = new Service();
           service.setNome("service"+(i+1));
           service.setStatus("status"+(i+1));
           listSet.add(service);
       }
           
       setListServices(listSet);
       
       List<Service> listGet = getListaServicos();
       
       for(int i = 0; i == listSet.size(); i++)
       assertEquals(listSet.get(i).getNome(), listGet.get(i).getNome());
       
    } 
   
    public void setListServices(List<Service> listServices) {        
        this.listServices.addAll(listServices);        
    }

    public List<Service> getListaServicos() {        
        ServiceLoader<RetrieveService> servLoad = ServiceLoader.load(RetrieveService.class);       
        Iterator<RetrieveService> iterSL = servLoad.iterator();                   
        
        if(servLoad.iterator().hasNext()) {           
           while(iterSL.hasNext()) {
               RetrieveService rs = iterSL.next();
                listServices.add(rs.getService());
            }            
        }
        else System.out.println("ServiceLoader Empty -  ServerServicos.listaServicos()");
        
        return listServices;     
        
    }
}
