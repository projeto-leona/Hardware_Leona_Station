/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.services;

import br.leona.hardware.model.Service;
import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

/**
 * @author Admin_2
 */
public class Pantilt {
    private Service service;
    private Arduino arduino;
    CommPortIdentifier comPortIdentifier;
    private String comport;
    private int rate;
    private String ServiceType = "Inativo";

       
    /**
     * Construtor da classe Arduino
     */
    public Pantilt() {
        System.out.println("Pantilt.Pantilt()");
        service = new Service();
        service.setName("Pantilt");
        service.setStatus("Inativo");
        rate = 9600;
        if(port()!=1) System.out.println("!! port() Falhou !!");
        arduino = new Arduino(comport, rate);
    }
    
    public Service getService() {  
        return service;
    }

     public String getServiceType() {
        return ServiceType;
    }
    
    public Arduino getArduino(){
        return arduino;
    }
    
    /*
     * Descobre quais portas de comunicação estão disponíveis
     */
    public int port() {
        System.out.println("Pantilt.Port()");                    
        service.setName("Pantilt");    
        try {
            Enumeration pList = CommPortIdentifier.getPortIdentifiers();
            System.out.println("Port =: " + pList.hasMoreElements());

            while (pList.hasMoreElements()) {
                comPortIdentifier = (CommPortIdentifier) pList.nextElement();
                System.out.println("Ports " + comPortIdentifier.getName() + " ");
                comport = comPortIdentifier.getName();
                ServiceType = comPortIdentifier.getName();
                service.setStatus("Ativo"); 
            }            
            System.out.println("Port is " + comport);  
            return 1;
        } catch (Exception exception) {
           System.out.println(exception); 
        }
        return 0;
    }
   
    public int isOn() {      
        System.out.println("Pantilt.isOn()");
        return arduino.isOn();            
    }
    
    public int turnOn() {      
        System.out.println("Pantilt.turnOn()");
        return arduino.sendData("!111O*");            
    }
    public int turnOff() {
        System.out.println("Pantilt.turnOff()");  
        return arduino.sendData("!111F*");                  
    }

    public int close() {  
        System.out.println("Pantilt.close()");
        if(arduino.close() == 1) { 
            service.setStatus("Inativo");
            return 1;
        }
        return 0;
    }

    public int reset() {       
        System.out.println("Pantilt.reset()");
        if(arduino.sendData("!111S*") == 1) { 
            service.setStatus("Ativo");
            return 1;
        }
        return 0;        
    }

    public int moveDirection(String degrees, String direction) {
        System.out.println("Pantilt.moveDirection(String graus, String direcao)"); 
        int typedDegrees = Integer.parseInt(degrees);      
        System.out.println("Graus = "+ typedDegrees);
        
        if(direction.equals("LEFT")) return moveLeft(typedDegrees, degrees);
        else
        if(direction.equals("RIGHT")) return moveRight(typedDegrees, degrees);
        else
        if(direction.equals("UP")) return moveUp(typedDegrees, degrees); 
        else
        if(direction.equals("DOWN")) return moveDown(typedDegrees, degrees);
        else
        return 0;
    }

    public int moveDown(int typedDegrees, String degrees) {  
        System.out.println("Pantilt.moveDown(int grausDigitado, String graus)");  
        String down;
        if (typedDegrees < 85) {
            if (typedDegrees < 10) {
                down = "!00" + degrees + "D*";
                System.out.println("down = " + down);
                return arduino.sendData(down);
            } else {
                down = "!0" + degrees + "D*";
                System.out.println(" do else down = " + down);
                return arduino.sendData(down);
            }
        }  
        return 0;
    }

    public int moveUp(int typedDegrees, String degrees){
         System.out.println("Pantilt.moveUp(int grausDigitado, String graus)");  
         String up;
         if (typedDegrees < 85) {
            if (typedDegrees < 10) {
                up = "!00" + degrees + "U*";
                System.out.println("up = " + up);
                return arduino.sendData(up);
            } else {
                up = "!0" + degrees + "U*";
                System.out.println(" do else up = " + up);
                return arduino.sendData(up);
            }
        }        
        return 0;
    }

    public int moveRight(int typedDegrees, String degrees) {
        System.out.println("Pantilt.moveRight(int grausDigitado, String graus)");  
        String right;
        if (typedDegrees < 10) {
            right = "!00" + degrees + "R*";
            System.out.println("RIGHT < 10 = " +right);
           return arduino.sendData(right);
        }else  if (typedDegrees >= 10 &&typedDegrees < 100 ) {
            right = "!0" + degrees + "R*";
            System.out.println("RIGHT >= 10 and < 100 = " + right);
            return arduino.sendData(right);
        } else {
            right = "!" + degrees + "R*";
            System.out.println("RIGHT  > 100= " + right);
            return arduino.sendData(right);
        }
    }

    public int moveLeft(int typedDegrees, String degrees) { 
      System.out.println("Pantilt.moveLeft(String direcao, int grausDigitado, String graus)");          
      String left;
      if (typedDegrees < 10) {
            left = "!00" + degrees + "L*";
            System.out.println("LEFT < 10 = " + left);
            return arduino.sendData(left);
        }else  if (typedDegrees >= 10 &&typedDegrees < 100 ) {
            left = "!0" + degrees + "L*";
            System.out.println("LEFT >= 10 and < 100 = " + left);
            return arduino.sendData(left);
        } else {
            left = "!" + degrees + "L*";
            System.out.println("LEFT > 100 = " + left);
            return arduino.sendData(left);
        }      
    }
}