/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.services;


import br.leona.hardware.model.Service;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Admin_2
 */
public class Arduino {
    private OutputStream serialOut;
    private int rate;
    private String comPort;
    private CommPortIdentifier portId;
    private Service service;
    private int isOn = 0;
    private String ServiceType;
    SerialPort serialPort;
    
    /**
     * Construtor da classe ControlePorta
     *
     * @param comPort - Porta COM que será utilizada para enviar os dados para
     * o arduino
     * @param rate - Taxa de transferência da porta serial geralmente é 9600
     */  
    public Arduino(String comPort, int rate) {
        System.out.println("Arduino.Arduino(String comPort, int rate)");
        service = new Service();
        service.setName("Arduino");
        service.setStatus("Inativo");
        this.comPort = comPort;
        this.rate = rate;        
        initialize();        
    }

    public Service getService() {
        return service;
    }

    public String getServiceType() {
        return ServiceType;
    }
      
    /**
     * Método que verifica se a comunicação com a porta serial está ok
     */
    private void initialize() {
        System.out.println("Arduino.initialize()");
        service.setStatus("Ativo");
        try {
            /**
             * Define uma variável portId do tipo CommPortIdentifier para
             * realizar e receber a comunicação serial
             */    
            //service.setStatus("Ativo");
            try {
                //Tenta verificar se a porta COM informada existe
                portId = CommPortIdentifier.getPortIdentifier(comPort);
                System.out.println(" Porta = " + portId.getName());
                ServiceType = portId.getName();
                //service.setStatus("Ativo");
            } catch (NoSuchPortException exception) {
                //Caso a porta COM não exista será exibido um erro
                System.out.println("Porta COM não encontrada: " + exception);
            }
            //Abre a porta COM 
            
            //SerialPort serialPort = null;
            try {
                serialPort = (SerialPort) portId.open("Comunicação serial", rate);
            } catch (PortInUseException ex) {                
                Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                serialOut = serialPort.getOutputStream();
            } catch (IOException ex) {
                Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                serialPort.setSerialPortParams(rate, //taxa de transferência da porta serial
                        SerialPort.DATABITS_8, //taxa de 10 bits 8 (envio)
                        SerialPort.STOPBITS_1, //taxa de 10 bits 1 (recebimento)
                        SerialPort.PARITY_NONE); //receber e enviar dados
            } catch (UnsupportedCommOperationException ex) {
                Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Sucesso Inicilaização da porta COM");
        } catch (HeadlessException exception) {
            System.out.println("Não foi possível inicilaizar porta COM: "+exception);             
            service.setStatus("Inativo");
        }
        
    }

    /**
     * Método que fecha a comunicação com a porta serial
     * @return 
     */
    public int close() {        
        try {            
            serialOut.close();
            service.setStatus("Inativo");
            isOn = 0; 
            return 1;
        } catch (IOException exception) {
            System.out.println("Não foi possível fechar porta COM: "+exception);
            return 0;
        }
    }

    public int sendData(String command) {        
        System.out.println("Arduino.sendData(String opcao)");
        try {            
            if(command.equals("!111O*")) isOn = 1; 
            if(command.equals("!111S*")) isOn = 1; 
            if(command.equals("!111F*")) isOn = 0; 
            byte[] bytes = command.getBytes();
            serialOut.write(bytes);  
            return 1;
        } catch (IOException exception) {
            System.out.println("Não foi possível enviar o comando: "+ exception);
            return 0;
        }
       
    }
    
    public int isOn(){
        System.out.println("Arduino.isOn()");
        return isOn;
    }
    
}