/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.RetrieveServices;

import br.leona.hardware.model.Service;
//import br.leona.hardware.services.Arduino;
//import br.leona.hardware.services.Pantilt;

/**
 *
 * @author Admin_2
 */
public class RetrieveArduino implements RetrieveService {
    //Pantilt pantilt= new Pantilt();
    //Arduino arduino = pantilt.getArduino();
    
    @Override
    public Service getService() {
        //return arduino.getService();
        Service service= new Service();
        service.setName("Arduino");
        service.setStatus("Ativo");
        return service;
    }

    @Override
    public String getServiceType() {
        //return arduino.getServiceType();
        return "COM4";
    }
    
}
