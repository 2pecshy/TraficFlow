package engine;

import engine.Simulateur;
import org.junit.jupiter.api.*;
import utils.Map.Cost.EnumCriter;
import utils.Map.Cost.Route;
import utils.Map.Map;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SimulateurTest {

    private Simulateur simuUnderTest;

    @BeforeEach
     void setUp(){
        Simulateur.INIT_Simulateur();
    }
    @AfterEach
    void cleanUp(){
        Simulateur.KILL_Simulateur();
    }

    @Test
    void instanceTest() {
        simuUnderTest = Simulateur.getInstance();
        assertEquals(Simulateur.getInstance(), simuUnderTest);
        assertTrue(Simulateur.KILL_Simulateur());
        assertFalse(Simulateur.KILL_Simulateur());
        assertTrue(Simulateur.INIT_Simulateur());
        assertFalse(Simulateur.INIT_Simulateur());
    }

    @Test
    void getMaxFlowTest(){
        simuUnderTest = Simulateur.getInstance();
        Simulateur s2 = Simulateur.getInstance();
        Map map0 = new Map();

        assertEquals(null,simuUnderTest.getMaxFlow(3,1));

        simuUnderTest.setMap(map0);
        assertEquals((double)simuUnderTest.getMaxFlow(3,1).getValue(),(double)4);

        for(Integer i = 0; i < map0.getCarrefours().size();i++){
            for(Integer j = 0; j < map0.getCarrefours().size();j++){
                if(i !=j)
                    assertEquals(simuUnderTest.getMaxFlow(i,j).getValue(),s2.getMaxFlow(i,j).getValue());
            }
        }
    }

    @Test
    void flowWhithCriter(){
        simuUnderTest = Simulateur.getInstance();
        Simulateur s2 = Simulateur.getInstance();
        Map map0 = new Map();
        simuUnderTest.setMap(map0);

        simuUnderTest.setCriter(EnumCriter.VOIES);
        assertEquals(simuUnderTest.getCriter(),EnumCriter.VOIES);
        assertEquals(s2.getCriter(),EnumCriter.VOIES);
        s2.setCriter(EnumCriter.DISTANCE);
        assertEquals(simuUnderTest.getCriter(),EnumCriter.DISTANCE);

        simuUnderTest.setCriter(EnumCriter.VOIES);
        assertEquals(simuUnderTest.getMaxFlow(3,1).getValue(),s2.getMaxFlow(3,1).getValue());

        simuUnderTest.setCriter(EnumCriter.DISTANCE);
        assertEquals(simuUnderTest.getMaxFlow(3,1).getValue(),s2.getMaxFlow(3,1).getValue());

        simuUnderTest.setCriter(EnumCriter.VITESSE);
        assertEquals(simuUnderTest.getMaxFlow(3,1).getValue(),s2.getMaxFlow(3,1).getValue());

    }

}