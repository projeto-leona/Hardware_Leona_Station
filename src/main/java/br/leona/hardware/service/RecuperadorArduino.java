/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.leona.hardware.service;

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import br.leona.hardware.model.Servico;
/**
 *
 * @author Lívia Miura
 */
public final class RecuperadorArduino implements RecuperadorServico{        
    private CommPortIdentifier commPortIdentifier;
    
    public RecuperadorArduino(){}
    
    @Override
    public Servico getServico() {             
        Enumeration pList = CommPortIdentifier.getPortIdentifiers();               
        Servico servico = new Servico();        
        servico.setNome("Arduino");        
        if(pList.hasMoreElements())  {            
            while (pList.hasMoreElements()) {
                try {
                    servico.setStatus("Ativo");                                       
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
                    servico.setStatus("Inativo");
                }              
            }     
        } else {            
              System.out.println("ERROR CommPortIdentfier: ");
              servico.setStatus("Inativo");              
        }        
        return servico;               
    }    
}
