/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public class InstruccionCALL extends Instruccion {

    public InstruccionCALL(int instruccion) {
        super(instruccion);
    }

    @Override
    public void build(int instruccion) {
        return;
    }

    @Override
    public String getString() {
        String texto="CALL "+this.getNNN();
        return texto;
    }

    @Override
    public String getDescripcion() {
        String texto="Llamar a subrutina en "+this.getNNN();
        return texto;
    }
    
}
