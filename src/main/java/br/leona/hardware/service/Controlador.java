/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.service;

import br.leona.hardware.model.ControlePorta;
import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

/**
 * @author klauder
 */
public final class Controlador {
    private CommPortIdentifier cpi = null;
    private String portaCOM;
    private ControlePorta arduino = new ControlePorta(portaCOM, 9600);

    /**
     * Construtor da classe Arduino
     */
    public Controlador() {
        porta();
        
    }
    
    
    /*
     * Descobre quais portas de comunicação estão disponíveis
     */

    public void porta() {
        try {
            Enumeration pList = CommPortIdentifier.getPortIdentifiers();
            System.out.println("Porta =: " + pList.hasMoreElements());

            while (pList.hasMoreElements()) {
                cpi = (CommPortIdentifier) pList.nextElement();

                System.out.println("Portas " + cpi.getName() + " ");
                portaCOM = cpi.getName();


            }
            System.out.println("PortA ESCOLHIDA =" + portaCOM);
        } catch (Exception e) {
            System.out.println();
        }
    }

    public void reset(String button) {
        if ("Reset".equals(button)) {
            arduino.enviaDados("!111S*");
            System.out.println(button);
        }
    }

    public void close(String button) {
        if ("Close".equals(button)) {
            arduino.close();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!Fechou a comunicação serial!!!!!!!!!!!!!!!!!!!");
            System.out.println(button);//Imprime o nome do botão pressionado
        }
    }

    public void setLigado() {
        String button = arduino.getStatusCamera();
        if ("Camera ON".equals(button)) {
            arduino.enviaDados("!111O*");
            System.out.println(button);
        }
        if ("Camera OFF".equals(button)) {
            arduino.enviaDados("!111F*");
            System.out.println(button);
        }
    }

    public int setDirecao(String direcao, String graus) {
        int grausDigitado = Integer.parseInt(graus);
        if ("LEFT".equals(direcao)) {
            
            if (grausDigitado < 10) {
                String right = "L*";
                String x0 = "!00" + graus + right;
                System.out.println("LEFT < 10 = " + x0);
                arduino.enviaDados(x0);
           
            }else  if (grausDigitado >= 10 &&grausDigitado < 100 ) {
                String left = "L*";
                String w0 = "!0" + graus + left;
                System.out.println("LEFT >= 10 and < 100 = " + w0);
                arduino.enviaDados(w0);
                
            } else {
                String left = "L*";
                String y0 = "!" + graus + left;
                System.out.println("LEFT > 100 = " + y0);
                arduino.enviaDados(y0);

            }

        } if ("RIGHT".equals(direcao)) {
            
            if (grausDigitado < 10) {
                String right = "R*";
                String x1 = "!00" + graus + right;
                System.out.println("RIGHT < 10 = " + x1);
                arduino.enviaDados(x1);
                
                
            }else  if (grausDigitado >= 10 &&grausDigitado < 100 ) {
                String right = "R*";
                String w1 = "!0" + graus + right;
                System.out.println("RIGHT >= 10 and < 100 = " + w1);
                arduino.enviaDados(w1);

            } else {
                String right = "R*";
                String y1 = "!" + graus + right;
                System.out.println("RIGHT  > 100= " + y1);
                arduino.enviaDados(y1);

            }
        } 
        if ("UP".equals(direcao) && grausDigitado < 85) {
            if (grausDigitado < 10) {
                String up = "U*";
                String x2 = "!00" + graus + up;
                System.out.println("up = " + x2);
                arduino.enviaDados(x2);
            } else {
                String up = "U*";
                String y2 = "!0" + graus + up;
                System.out.println(" do else up = " + y2);
                arduino.enviaDados(y2);
            }

        }  if ("DOWN".equals(direcao) && grausDigitado < 85) {
            if (grausDigitado < 10) {
                String down = "D*";
                String x3 = "!00" + graus + down;
                System.out.println("down = " + x3);
                arduino.enviaDados(x3);
                
                
            } else {
                String down = "D*";
                String x3 = "!0" + graus + down;
                System.out.println(" do else down = " + x3);
                arduino.enviaDados(x3);
            }
        }
        return grausDigitado;
    }

    public int getStatusCamera() {
        return 0;//arduino.getStatus(portaCOM);
    }
}
