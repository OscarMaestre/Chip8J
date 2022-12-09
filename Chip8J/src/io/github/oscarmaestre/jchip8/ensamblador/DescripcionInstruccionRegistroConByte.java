package io.github.oscarmaestre.jchip8.ensamblador;

import io.github.oscarmaestre.jchip8.instrucciones.Instruccion;
import java.util.function.Function;
import java.util.regex.Matcher;

public class DescripcionInstruccionRegistroConByte {
    int codop;
    Function<Integer, Instruccion> constructor;
    String nombreInstruccion;

    public DescripcionInstruccionRegistroConByte(int codop, Function<Integer, Instruccion> constructor, String nombreInstruccion) {
        this.codop = codop;
        this.constructor = constructor;
        this.nombreInstruccion = nombreInstruccion;
    }
    
    public Instruccion getInstruccion(Matcher matcher){
        String strRegistro=matcher.group(4);
        String strByte=matcher.group(9);
        System.out.println("Grupo 4:"+strRegistro);
        System.out.println("Grupo 9:"+strByte);
        int valorRegistro=Instruccion.getPosRegistro(strRegistro);
        int valorByte=Integer.parseInt(strByte);
        
        int codigoOperacion=(4096*codop)+(16*valorRegistro)+valorByte;
        Instruccion i=this.constructor.apply(codigoOperacion);
        return i;
    }
    
}
