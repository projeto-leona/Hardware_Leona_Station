/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.leona.hardware.services;

import br.leona.hardware.model.Service;
import gnu.io.CommPortIdentifier;
import java.util.Enumeration;
import java.util.NoSuchElementException;
/**
 *
 * @author Admin2
 */
public class Port {        
    private CommPortIdentifier commPortIdentifier;
    private Service service;
    int turnOn = 0;
    private String ServiceType;
   
    public Port(){
        service = new Service();        
        service.setName("Port");
        service.setStatus("Inativo");
        initialize();
    }
   
    public Service getService() {  
        return service;
    }
    
    public String getServiceType() {
        return ServiceType;
    }
    
    public void initialize(){
        Enumeration pList = CommPortIdentifier.getPortIdentifiers();               
        
        if(pList.hasMoreElements())  {            
            while (pList.hasMoreElements()) {
                try {      
                    commPortIdentifier = (CommPortIdentifier) pList.nextElement();                     
                    System.out.println("Inicializando Port: " + commPortIdentifier.getName());   
                    if (commPortIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                        System.out.println("is a Serial Port: " + commPortIdentifier);
                    } else if (commPortIdentifier.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                        System.out.println("is a Parallel Port: " + commPortIdentifier);
                    } else {
                        System.out.println("is an Unknown Port: " + commPortIdentifier);
                    }     
                    ServiceType = commPortIdentifier.getName();
                    service.setStatus("Ativo"); 
                    turnOn = 1;
                } catch (NoSuchElementException n) {
                    System.out.println("CommPortIdentfier: ERROR "+n);
                }              
            }     
        } else {            
              System.out.println("ERROR CommPortIdentfier: ");
        }  
        
    }   

    public int turnOn() {
        return turnOn;
    }
}
