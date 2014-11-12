/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.RetrieveServices;

import br.leona.hardware.model.Service;
//import br.leona.hardware.services.Pantilt;

/**
 *
 * @author Admin_2
 */
public class RetrievePantilt implements RetrieveService {
   // Pantilt pantilt = new Pantilt();
       
    @Override
    public Service getService() {
        //return pantilt.getService();        
        Service service= new Service();
        service.setName("Pantilt");
        service.setStatus("Ativo");
        return service;
    }

    @Override
    public String getServiceType() {
        //return pantilt.getServiceType();
        return "COM4";
    }
    
}
