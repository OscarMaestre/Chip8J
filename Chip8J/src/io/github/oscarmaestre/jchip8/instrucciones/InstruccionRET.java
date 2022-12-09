/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public class InstruccionRET extends Instruccion {

    public InstruccionRET(int instruccion) {
        super(instruccion);
    }


    @Override
    public String getString() {
        return "RET";
    }

    @Override
    public String getDescripcion() {
        return "Retorna. Pone PC=[SP], SP=SP-1";
    }
    
    
}
