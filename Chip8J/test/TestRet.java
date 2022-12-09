/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.oscarmaestre.jchip8.instrucciones.InstruccionRET;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author usuario
 */
public class TestRet {
    
    public TestRet() {
    }
    @Test
    public void testRet(){
        InstruccionRET iret=new InstruccionRET(0x00ee);
        String hexString = iret.getHexString();
        Assert.assertEquals(hexString, "00EE");
    }
}
