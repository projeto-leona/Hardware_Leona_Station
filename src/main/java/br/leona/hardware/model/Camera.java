/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leona.hardware.model;

import br.leona.hardware.service.Controlador;

/**
 *
 * @author Admin_2
 */
public class Camera {
    private boolean ligado = false;   
    private Controlador controlador = new Controlador();

    public boolean isLigado() {
        //int status = controlador.getStatusCamera();
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
        controlador.setLigado();
    }
        
    public int setDirecao(String direcao, String graus){
         return controlador.setDirecao(direcao, graus);
    }
}
