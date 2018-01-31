package engine;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class SimulateurTest {

    private SimulateurManager simuUnderTest;
    private TraficFlowModel model1;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        SimulateurManager.INIT_Simulateur();
        model1 = Mockito.mock(TraficFlowModel.class);
    }
    @AfterEach
    void cleanUp(){
        SimulateurManager.KILL_Simulateur();
    }

    @Test
    public void instanceTest() {
        simuUnderTest = SimulateurManager.getInstance();
        assertEquals(SimulateurManager.getInstance(), simuUnderTest);
        assertTrue(SimulateurManager.KILL_Simulateur());
        assertFalse(SimulateurManager.KILL_Simulateur());
        assertTrue(SimulateurManager.INIT_Simulateur());
        assertFalse(SimulateurManager.INIT_Simulateur());
    }

    @Test
    public void getMaxFlowTest(){
        simuUnderTest = SimulateurManager.getInstance();
        assertEquals(simuUnderTest.addAndRunSimulation(model1),0);
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