/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.RetrieveServices;

import br.leona.hardware.model.Service;
//import br.leona.hardware.services.Port;

/**
 *
 * @author Admin_2
 */
public class RetrievePort implements RetrieveService {
    //Port port = new Port();
    
    @Override
    public Service getService() {
        //return port.getService();
         Service service= new Service();
        service.setName("Port");
        service.setStatus("Ativo");
        return service;
    }

    @Override
    public String getServiceType() {
        //return port.getServiceType();
        return "COM4";
    }
    
}
