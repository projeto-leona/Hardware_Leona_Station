/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.model;

import br.leona.hardware.model.Service;
import br.leona.hardware.service.RetrieveService;
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
 * @author Admin2
 */
public class Arduino  implements RetrieveService {
    private OutputStream serialOut;
    private int rate;
    private String comPort;
    private CommPortIdentifier portId;
    private Service service;
    private int turnOn = 0;
    

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
        this.comPort = comPort;
        this.rate = rate;
        turnOn = initialize();
    }

    /**
     * Método que verifica se a comunicação com a porta serial está ok
     */
    private int initialize() {
        System.out.println("Arduino.initialize()");
        service.setName("Arduino");
        try {
            /**
             * Define uma variável portId do tipo CommPortIdentifier para
             * realizar e receber a comunicação serial
             */
            
            try {
                //Tenta verificar se a porta COM informada existe
                portId = CommPortIdentifier.getPortIdentifier(comPort);
                //  portId = CommPortIdentifier.getPortIdentifier(cpi.getName());
                // System.out.println(" Porta está true or false = " + portId.getName());
                System.out.println(" Porta = " + portId.getName());
            //    System.out.println("  Taxa = " + this.rate);
                //} catch (Exception e) {
                // System.out.println("Porta COM não encontrada!!! "+portId.getName());
                service.setStatus("Ativo");
            } catch (NoSuchPortException exception) {
                //Caso a porta COM não exista será exibido um erro
                System.out.println("Porta COM não encontrada: "+exception);
//                System.out.println("Porta COM não encontrada!!! " + portId.getName());
                service.setStatus("Inativo");
            }
            //Abre a porta COM 
            
            SerialPort serialPort = null;
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
                return 1;
            } catch (UnsupportedCommOperationException ex) {
                Logger.getLogger(Arduino.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Sucesso Inicilaização da porta COM");
           
        } catch (HeadlessException exception) {
             System.out.println("Não foi possível inicilaizar porta COM: "+exception);
             service.setStatus("Inativo");
        }
        return 0;
    }

    /**
     * Método que fecha a comunicação com a porta serial
     * @return 
     */
    public int close() {
        System.out.println("Arduino.close()");
        try {
            serialOut.close();
            return 1;
        } catch (IOException exception) {
            System.out.println("Não foi possível fechar porta COM: "+exception);
            return 0;
        }
    }

    /**
     * @param command - Valor a ser enviado pela porta serial
     * @return 
     */
    /*
     * public void sendData(int command) { try {
     * serialOut.write(command);//escreve o valor na porta serial para ser enviado
     * // serialOut.write(1);//escreve o valor na porta serial para ser enviado
     *
     * } catch (IOException ex) { JOptionPane.showMessageDialog(null, "Não foi
     * possível enviar o dado. ", "Enviar dados", JOptionPane.PLAIN_MESSAGE); }
     * }
     */
    /*
     * Converter para bytes para enviar dados
     */
    public int sendData(String command) {        
        System.out.println("Arduino.sendData(String opcao)");
        try {

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
        return 1;
    }

    @Override
    public Service getService() {
        return service;
    }
   
    public int turnOn() {
        return turnOn;
    }
}