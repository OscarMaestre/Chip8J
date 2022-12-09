/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public class InstruccionSYS extends Instruccion {

    public InstruccionSYS(int instruccion) {
        super(instruccion);
    }


    @Override
    public String getString() {
        return "SYS "+ Integer.toHexString(NNN);
    }

    @Override
    public String getDescripcion() {
        return "Llamada al sistema en "+ Integer.toHexString(NNN);
    }
    
}
