/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.oscarmaestre.jchip8.Ensamblador;
import io.github.oscarmaestre.jchip8.instrucciones.Instruccion;
import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author usuario
 */
public class TestEnsamblador {
    Ensamblador ensamblador;
    final String DIRECTORIO_ENSAMBLADO="ficheros_ensamblado";
    public TestEnsamblador() {
        this.ensamblador=new Ensamblador();
    }

    
    @Test
    public void ensamblarCALL() {
        Instruccion esCALL = this.ensamblador.esCALL("CALL    431;");
        String hexString = esCALL.getHexString();
        Assert.assertEquals("21AF", hexString, "El valor no coincide");
    }
    
    @Test
    public void ensamblarCALLConEtiqueta() {
        String simbolo=":SUBRUTINA";
        this.ensamblador.addSimbolo(":SUBRUTINA", 651);
        Instruccion esCALL = this.ensamblador.esCALLConEtiqueta("CALL    "+simbolo+ " ;");
        String hexString = esCALL.getHexString();
        Assert.assertEquals("228B", hexString, "El valor no coincide");
    }
    
    @Test
    public void ensamblarCLS(){
        Instruccion esCLS=this.ensamblador.esCLS("CLS    ;");
        String hexString = esCLS.getHexString();
        Assert.assertEquals("00E0", hexString);
    }
    
    @Test 
    public void ensamblarFichero1(){
        this.ensamblador.ensamblar(this.DIRECTORIO_ENSAMBLADO+"/ensamblador01.txt");
    }
    @Test 
    public void ensamblarFicheroConEtiquetas(){
        this.ensamblador.ensamblar(this.DIRECTORIO_ENSAMBLADO+"/ensamblado_con_etiqueta01.txt");
    }
    @Test 
    public void ensamblarFicheroConEtiquetasYBlancos(){
        ArrayList<Instruccion> ensamblar = this.ensamblador.ensamblar(this.DIRECTORIO_ENSAMBLADO+"/ensamblado_con_etiqueta02.txt");
        System.out.println("---Ensamblado---");
        String ensamblado="";
        for (Instruccion i:ensamblar){
            ensamblado+="\n"+i.getHexString();
        }
        System.out.println("Resultado:");
        System.out.println(ensamblado);
        System.out.println("---Fin de Ensamblado---");
    }
}
