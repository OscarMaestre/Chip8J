/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package io.github.oscarmaestre.jchip8.instrucciones;

/**
 *
 * @author usuario
 */
public abstract class Instruccion {
    protected byte[] nibbles;
    /*En realidad no siempre se usa, pero ocurre a menudo
    así que lo precalculamos */
    protected int NNN;
    protected int KK;
    static String[] registros={"0", "1", "2", "3", "4", "5", "6", "7", "8",
        "9", "A", "B", "C", "D", "E", "F"};
    public Instruccion(int instruccion){
        this.nibbles=this.getNibble(instruccion);
    }
    public static int getPosRegistro(String numeroConV){
        for (int i=0; i<registros.length; i++){
            String reg="V"+registros[i];
            if (reg.equals(numeroConV)){
                return i;
            }
        }
        return -1;
    }
    public int getNNN(){
        return this.NNN;
    }
    public int getKK(){
        return this.KK;
    }
    public String getHexString(){
        String nibble1=Integer.toHexString(this.nibbles[0]).toUpperCase();
        String nibble2=Integer.toHexString(this.nibbles[1]).toUpperCase();
        String nibble3=Integer.toHexString(this.nibbles[2]).toUpperCase();
        String nibble4=Integer.toHexString(this.nibbles[3]).toUpperCase();
        
        String cadena=nibble1+nibble2+nibble3+nibble4;
        return cadena;
    }
    
    public byte[]  getNibble (int word){
        final int N1=0b1111000000000000;
        final int N2=0b0000111100000000;
        final int N3=0b0000000011110000;
        final int N4=0b0000000000001111;
        int resultado=0;
        byte[] nibbles=new byte[4];
        nibbles[0]= (byte) ((word & N1) >> 12);
        nibbles[1]= (byte) ((word & N2) >>  8);
        nibbles[2]= (byte) ((word & N3) >>  4);
        nibbles[3]= (byte)  (word & N4);
        
        /* Aqui precalculamos NNN y KK*/
        this.KK=(nibbles[2]*16) + (nibbles[3]);
        this.NNN=(nibbles[1]*256) + (nibbles[2]*16) + (nibbles[3]);
        return nibbles;
    }
    
    public byte[] getBytes(){
        byte[] bytes=new byte[2];
        bytes[0]=(byte) ((this.nibbles[0]*16+this.nibbles[1]) & 0xff);
        bytes[1]=(byte) ((this.nibbles[2]*16+this.nibbles[3]) & 0xff);
        return bytes;
    }
    public abstract String getString();
    
    public abstract String getDescripcion();
}
