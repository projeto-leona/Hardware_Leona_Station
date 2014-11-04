/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.leona.hardware.service;

import br.leona.hardware.model.Service;
/**
 *
 * @author Admin2
 */
public final class RetrieveServices implements RetrieveService{        
    //private CommPortIdentifier commPortIdentifier;
    
    public RetrieveServices(){}
    
    @Override
    public Service getService() {         
        Service service = new Service();        
        service.setNome("Arduino");        
        /*Enumeration pList = CommPortIdentifier.getPortIdentifiers();               
        
        if(pList.hasMoreElements())  {            
            while (pList.hasMoreElements()) {
                try {
                    service.setStatus("Ativo");                                       
                    commPortIdentifier = (CommPortIdentifier) pList.nextElement();                     
                    System.out.println("Inicializando Port: " + commPortIdentifier.getName());   
                    if (commPortIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                        System.out.println("is a Serial Port: " + commPortIdentifier);
                    } else if (commPortIdentifier.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                        System.out.println("is a Parallel Port: " + commPortIdentifier);
                    } else {
                        System.out.println("is an Unknown Port: " + commPortIdentifier);
                    }                    
                } catch (NoSuchElementException n) {
                    System.out.println("CommPortIdentfier: ERROR "+n);
                    service.setStatus("Inativo");
                }              
            }     
        } else {            
              System.out.println("ERROR CommPortIdentfier: ");
              service.setStatus("Inativo");              
        }   */     
        service.setStatus("Inativo");
        return service;               
    }   
}
