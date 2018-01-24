package engine;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class SimulateurTest {

    private TraficFlowModel simuUnderTest;

    @BeforeEach
    void setUp(){
        SimulateurManager.INIT_Simulateur();
    }
    @AfterEach
    void cleanUp(){
        SimulateurManager.KILL_Simulateur();
    }

    @Test
    public void instanceTest() {
        /*simuUnderTest = TraficFlowModel.getInstance();
        assertEquals(TraficFlowModel.getInstance(), simuUnderTest);
        assertTrue(TraficFlowModel.KILL_Simulateur());
        assertFalse(TraficFlowModel.KILL_Simulateur());
        assertTrue(TraficFlowModel.INIT_Simulateur());
        assertFalse(TraficFlowModel.INIT_Simulateur());*/
    }

    @Test
    public void getMaxFlowTest(){
        /*simuUnderTest = TraficFlowModel.getInstance();
        TraficFlowModel s2 = TraficFlowModel.getInstance();
        Map map0 = Map.getDefaultMap();

        assertEquals(null,simuUnderTest.getMaxFlow(3,1));

        simuUnderTest.setMap(map0);
        assertEquals((double)simuUnderTest.getMaxFlow(3,1).getValue(),(double)4);

        for(Integer i = 0; i < map0.getCarrefours().size();i++){
            for(Integer j = 0; j < map0.getCarrefours().size();j++){
                if(i !=j)
                    assertEquals(simuUnderTest.getMaxFlow(i,j).getValue(),s2.getMaxFlow(i,j).getValue());
            }
        }*/
    }

    @Test
    public void flowWhithCriter(){
        /*simuUnderTest = TraficFlowModel.getInstance();
        TraficFlowModel s2 = TraficFlowModel.getInstance();
        Map map0 = Map.getDefaultMap();
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
        assertEquals(simuUnderTest.getMaxFlow(3,1).getValue(),s2.getMaxFlow(3,1).getValue());*/

    }

    @Test
    public void ameliorerFlowTest(){

      /*  simuUnderTest = TraficFlowModel.getInstance();
        Map map0 = Map.getDefaultMap();

        simuUnderTest.setMap(map0);
        MaximumFlowAlgorithm.MaximumFlow<Route> Gf = simuUnderTest.getMaxFlow(3,1);
        double Vf = Gf.getValue();
        assertEquals(4.0,Vf);

        double amelioration_Max = simuUnderTest.ameliorerFlow(0,1);
        assertEquals(2.0,amelioration_Max);
        assertEquals(4,map0.getCarrefours().size());

        map0.getRoute(0,1).setNombre_de_voie( map0.getRoute(0,1).getNombre_de_voie()+(int) amelioration_Max);
        simuUnderTest.setMap(map0);

        double newVf = simuUnderTest.getMaxFlow(3,1).getValue();

        assertTrue(newVf > Vf);
        assertEquals(newVf,Vf+amelioration_Max);*/
    }

}