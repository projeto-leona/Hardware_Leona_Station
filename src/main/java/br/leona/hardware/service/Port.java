/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.leona.hardware.service;

import br.leona.hardware.model.Service;
import gnu.io.CommPortIdentifier;
import java.util.Enumeration;
import java.util.NoSuchElementException;
/**
 *
 * @author Admin2
 */
public final class Port implements RetrieveService{        
    private CommPortIdentifier commPortIdentifier;
    
    public Port(){
    }
    
    @Override
    public Service getService() {           
        Service service = new Service(); 
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
                    service.setNome(commPortIdentifier.getName());
                    service.setStatus("Ativo");    
                } catch (NoSuchElementException n) {
                    System.out.println("CommPortIdentfier: ERROR "+n);
                    service.setStatus("Inativo");
                }              
            }     
        } else {            
              System.out.println("ERROR CommPortIdentfier: ");
              service.setStatus("Inativo");              
        }   
        return service;               
    }   
}
