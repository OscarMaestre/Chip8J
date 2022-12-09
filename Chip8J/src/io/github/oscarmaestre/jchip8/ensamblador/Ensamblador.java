/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.ensamblador;

import io.github.oscarmaestre.jchip8.instrucciones.Instruccion;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionCALL;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionCLS;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionJPAddr;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionRET;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionSEVXByte;
import io.github.oscarmaestre.jchip8.instrucciones.InstruccionSYS;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
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
    String terminadorSentencia=this.espaciosQuiza+";"+this.espaciosQuiza;
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
        patronGlobal=patronGlobal+this.terminadorSentencia;
        //System.out.println("El patron global es:"+patronGlobal);
        Pattern devolver=Pattern.compile(patronGlobal);
        return devolver;
    }
    
    public boolean esEtiqueta(String linea, int posActual){
        Pattern etiqueta=Pattern.compile(this.etiqueta);
        Matcher matcher=etiqueta.matcher(linea);
        if (matcher.matches()){
            this.addSimbolo(linea, posActual);
            return true;
        }
        return false;
    }
    public Instruccion esCALL(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(CALL)");
        patrones.add(espaciosSiempre);
        patrones.add(expresionRegularNumero);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            System.out.println("Es un CALL");
            String direccion = matcher.group(3);
            System.out.println("El grupo 3 es:"+matcher.group(3));
            Integer valor = Integer.valueOf(direccion);
            System.out.println("Es un CALL a "+valor);
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
            //System.out.println("Es un CALL");
            String direccion = matcher.group(3);
            
            Integer valor = this.tablaSimbolos.get(direccion);
            //System.out.println("Es un CALL a "+valor);
            int codop=(4096*2)+valor;
            InstruccionCALL i=new InstruccionCALL(codop);
            return i;
        } //Fin del if
        return null;
    }
    
    public Instruccion esSYS(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(SYS)");
        patrones.add(espaciosSiempre);
        patrones.add(expresionRegularNumero);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            
            String direccion = matcher.group(3);
            System.out.println("El grupo 3 es:"+matcher.group(3));
            Integer valor = Integer.valueOf(direccion);
            System.out.println("Es un SYS a "+valor);
            int codop=valor;
            InstruccionSYS i=new InstruccionSYS(codop);
            return i;
        } //Fin del if
        return null;
    }
    
    public Instruccion esSYSConEtiqueta(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(SYS)");
        patrones.add(espaciosSiempre);
        patrones.add(this.etiqueta);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            //System.out.println("Es un CALL");
            String direccion = matcher.group(3);
            
            Integer valor = this.tablaSimbolos.get(direccion);
            //System.out.println("Es un CALL a "+valor);
            int codop=valor;
            InstruccionSYS i=new InstruccionSYS(codop);
            return i;
        } //Fin del if
        return null;
    }
    
    
    public Instruccion esJPConEtiqueta(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(JP)");
        patrones.add(espaciosSiempre);
        patrones.add(this.etiqueta);
        
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            //System.out.println("Es un CALL");
            String direccion = matcher.group(3);
            
            Integer valor = this.tablaSimbolos.get(direccion);
            System.out.println("Es un JP a "+valor);
            int codop=(4096*1)+valor;
            InstruccionJPAddr i=new InstruccionJPAddr(codop);
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
    
    public Instruccion esRET(String linea){
        ArrayList<String> patrones=new ArrayList<>();
        patrones.add("(RET)");
        patrones.add(this.espaciosQuiza);
        Pattern patron = this.getPatron(patrones);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            return new InstruccionRET(0x00ee);
        }
        return null;
    }
    
    public ArrayList<String> readFileAsList(String fileName) {
        
        ArrayList<String> lines=new ArrayList<String>();
        try {
            
            lines = (ArrayList<String>) Files.readAllLines(Paths.get(fileName),
                    Charset.defaultCharset());
            return lines;
            
        } catch (Exception e) {
            System.out.println("No encontre el fichero");
        }
        return lines;
    }
    public Matcher coincidePatronInstruccionRegistroConByte(String nombreInstruccion, String linea){
        Matcher devolver;
        String regExpPatron=this.espaciosQuiza+"("+nombreInstruccion+")"+this.espaciosSiempre+"("+
                this.registro+")"+this.espaciosQuiza+this.coma+this.espaciosQuiza+
                "("+this.expresionRegularNumero+")"+this.terminadorSentencia;
        System.out.println("El patron es:"+regExpPatron);
        Pattern patron=Pattern.compile(regExpPatron);
        Matcher matcher = patron.matcher(linea);
        if (matcher.matches()){
            devolver=matcher;
        } else{
            devolver=null;
        }
        return devolver;
    }
    public Instruccion getInstruccionRegistroConByte(String linea){
        ArrayList<DescripcionInstruccionRegistroConByte> listaInstrucciones;
        listaInstrucciones=new ArrayList<>();
        
        DescripcionInstruccionRegistroConByte descSEVXByte;
        descSEVXByte = new DescripcionInstruccionRegistroConByte(
                3, InstruccionSEVXByte::new, "SE");
        listaInstrucciones.add(descSEVXByte);
        for (DescripcionInstruccionRegistroConByte d: listaInstrucciones){
            String codop=d.nombreInstruccion;
            Matcher matcher=this.coincidePatronInstruccionRegistroConByte(codop, linea);
            if (matcher!=null){
                Instruccion i=d.getInstruccion(matcher);
                return i;
            }
        }
        return null;
    }
    public Instruccion getInstruccionDeLinea(String linea){
        ArrayList<Function<String, Instruccion>> procesadoInstrucciones;
        procesadoInstrucciones=new ArrayList<>();
        
        procesadoInstrucciones.add(this::esCLS);
        procesadoInstrucciones.add(this::esRET);
        procesadoInstrucciones.add(this::esCALL);
        procesadoInstrucciones.add(this::esCALLConEtiqueta);
        procesadoInstrucciones.add(this::esJPConEtiqueta);
        procesadoInstrucciones.add(this::esSYS);
        procesadoInstrucciones.add(this::esSYSConEtiqueta);
        
        
        for (Function<String, Instruccion> iterador : procesadoInstrucciones){
            Instruccion instruccion=iterador.apply(linea);
            if (instruccion!=null){
                System.out.println("--------------------------------------------");
                System.out.println("Linea de ensamblado:"+linea);
                System.out.println("Linea de ensamblado:"+instruccion.getHexString());
                System.out.println("Descripcion        :"+instruccion.getDescripcion());
                System.out.println("--------------------------------------------");
                return instruccion;
            }
        }
        /* Si llegamos aquí, probamos con el generico Registro, Byte*/
        Instruccion iRegByte=this.getInstruccionRegistroConByte(linea);
        if (iRegByte!=null){
            System.out.println("Encontrada regbyte:"+iRegByte.getDescripcion());
            return  iRegByte;
        }
        System.out.println("Esta línea produjo  null:"+linea);
        return null;
    }
    
    public ArrayList<Instruccion> ensamblar(String nombreFichero){
        ArrayList<String> lineas = this.readFileAsList(nombreFichero);
        ArrayList<Instruccion> instrucciones=new ArrayList<>();
        int posEnsamblado=512;
        Instruccion instruccion;
        Function<String, Instruccion> funcion;
        
        for (String linea : lineas){
            linea=linea.strip();
            if (linea.equals("")){
                continue;
            }
            
            if (this.esEtiqueta(linea, posEnsamblado)){
                continue;
            }
            

            instruccion=this.getInstruccionDeLinea(linea);
            if (instruccion!=null){
                posEnsamblado=posEnsamblado+2;
                instrucciones.add(instruccion);
            }
        } //Fin del for
        return instrucciones;
    }
    public static void main(String[] args) {
        Ensamblador ensamblador=new Ensamblador();
        ArrayList<Instruccion> ensamblar = ensamblador.ensamblar(args[0]);
        System.out.println("---Ensamblado---");
        for (Instruccion i:ensamblar){
            System.out.println(i.getHexString());
        }
        System.out.println("---Fin de Ensamblado---");
    }//Fin del main
    
}
