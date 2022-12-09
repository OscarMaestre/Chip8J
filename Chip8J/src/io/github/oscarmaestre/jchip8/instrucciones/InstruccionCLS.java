/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public class InstruccionCLS extends Instruccion{

    public InstruccionCLS(int instruccion) {
        super(instruccion);
    }


    @Override
    public String getString() {
        return "CLS";
    }

    @Override
    public String getDescripcion() {
        return "Borra la pantalla";
    }
    
}
