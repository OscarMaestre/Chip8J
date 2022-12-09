/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.oscarmaestre.jchip8.Ensamblador;
import io.github.oscarmaestre.jchip8.instrucciones.Instruccion;
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
}
