/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.oscarmaestre.jchip8.instrucciones.InstruccionCALL;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author usuario
 */
public class TestCALL {
    
    @Test
    public void testCALL(){
        InstruccionCALL i=new InstruccionCALL(0x23ab);
        String descripcion = i.getString();
        Assert.assertEquals(descripcion, "CALL 939");
    }
}
