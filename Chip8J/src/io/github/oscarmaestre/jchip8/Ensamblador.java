/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8;

import io.github.oscarmaestre.jchip8.instrucciones.Instruccion;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionCALL;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionCLS;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author usuario
 */
public class Ensamblador {
    String expresionRegularNumero="([0-9]+)";
    String espaciosSiempre="(\\s+)";
    String espaciosQuiza="(\\s*)";
    String coma="(,)";
    String puntoyComa=";";
    String registro="(V[0-9A-F])";
    String etiqueta="(\\:[A-Z]+)";
    
    HashMap<String, Integer> tablaSimbolos;

    public Ensamblador() {
        this.tablaSimbolos=new HashMap<String, Integer>();
    }
    
    public void addSimbolo(String nombre, int valor){
        this.tablaSimbolos.put(nombre, valor);
    }
    
    public Pattern getPatron(ArrayList<String> patrones){
        String patronGlobal="";
        for (String patron : patrones){
            patronGlobal=patronGlobal+patron;
        }
        patronGlobal=patronGlobal+this.espaciosQuiza+puntoyComa+this.espaciosQuiza;
        System.out.println("El patron global es:"+patronGlobal);
        Pattern devolver=Pattern.compile(patronGlobal);
        return devolver;
    }
    
    public Instruccion esCALL(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(CALL)");
        patrones.add(espaciosSiempre);
        patrones.add(expresionRegularNumero);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            //System.out.println("Es un CALL");
            String direccion = matcher.group(3);
            //System.out.println("El grupo 3 es:"+matcher.group(3));
            Integer valor = Integer.valueOf(direccion);
            //System.out.println("Es un CALL a "+valor);
            int codop=(4096*2)+valor;
            InstruccionCALL i=new InstruccionCALL(codop);
            return i;
        } //Fin del if
        return null;
    }
    
    public Instruccion esCALLConEtiqueta(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(CALL)");
        patrones.add(espaciosSiempre);
        patrones.add(this.etiqueta);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            System.out.println("Es un CALL");
            String direccion = matcher.group(3);
            
            Integer valor = this.tablaSimbolos.get(direccion);
            System.out.println("Es un CALL a "+valor);
            int codop=(4096*2)+valor;
            InstruccionCALL i=new InstruccionCALL(codop);
            return i;
        } //Fin del if
        return null;
    }
    public Instruccion esCLS(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(CLS)");
        patrones.add(this.espaciosQuiza);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            return new InstruccionCLS(0x00e0);
        }
        return null;
    }
}
