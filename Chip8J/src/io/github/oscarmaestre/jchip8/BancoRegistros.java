/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8;

/**
 *
 * @author usuario
 */
public class BancoRegistros {
    /*Registros generales*/
    Registro[] registrosGenerales = new Registro[16];
    Registro   pc                 = new Registro();
    Registro   dt                 = new Registro();
    Registro   st                 = new Registro();
    public BancoRegistros(){
        for (int i=0; i<registrosGenerales.length; i++){
            registrosGenerales[i]=new Registro();
            String numHex=Integer.toHexString(i);
            registrosGenerales[i].setNombre("V"+numHex.toUpperCase());
            registrosGenerales[i].setValor((byte)0);
        } //Fin del for que inicializa los registros generales
        
        pc=new Registro();
        pc.setNombre("I ");
        
        dt=new Registro();
        dt.setNombre("DT");
        
        st=new Registro();
        st.setNombre("ST");
    }
    
}
