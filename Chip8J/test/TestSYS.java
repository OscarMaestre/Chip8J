/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */

import io.github.oscarmaestre.jchip8.instrucciones.InstruccionSYS;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author usuario
 */
public class TestSYS {
    
    @Test
    public void hello() {
        InstruccionSYS sys=new InstruccionSYS(0x0fff);
        int NNN=sys.getNNN();
        Assert.assertEquals(NNN, 0xfff);
    }
    @Test
    public void test2() {
        InstruccionSYS sys=new InstruccionSYS(0x0abd);
        int NNN=sys.getNNN();
        Assert.assertEquals(NNN, 0xabd);
    }
    

}
