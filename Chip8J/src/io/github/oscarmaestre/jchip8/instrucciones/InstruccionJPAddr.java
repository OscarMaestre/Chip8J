/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public class InstruccionJPAddr extends Instruccion {
    public InstruccionJPAddr(int instruccion) {
        super(instruccion);
    }

    @Override
    public void build(int instruccion) {
        return ;
    }

    @Override
    public String getString() {
        return "JP " + Integer.toHexString(NNN);
    }

    @Override
    public String getDescripcion() {
        return "Salto incondicional a " + Integer.toHexString(NNN);
    }
    
}
