/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public class InstruccionSEVXByte extends Instruccion {

    int numRegistro;
    
    public InstruccionSEVXByte(int instruccion) {
        super(instruccion);
        this.numRegistro=this.nibbles[1];
    }
 

    @Override
    public String getString() {
        String informe="SE V"+this.registros[this.numRegistro]+", "+Integer.toString(this.KK);
        return informe;
    }

    @Override
    public String getDescripcion() {
        String informe="Saltar sig instr. si V"+this.registros[this.numRegistro]+"=="+Integer.toString(this.KK);
        return informe;
    }
    
}
