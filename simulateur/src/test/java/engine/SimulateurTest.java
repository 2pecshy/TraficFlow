package engine;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class SimulateurTest {

    private SimulateurManager simuUnderTest;
    private TraficFlowModel model1,model2,model3;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        SimulateurManager.INIT_Simulateur();
        model1 = Mockito.mock(TraficFlowModel.class);
        model2 = Mockito.mock(TraficFlowModel.class);
        model3 = Mockito.mock(TraficFlowModel.class);
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
    public void addAndRunSimulation(){
        simuUnderTest = SimulateurManager.getInstance();
        assertEquals(simuUnderTest.addAndRunSimulation(model1),0);
        assertEquals(simuUnderTest.addAndRunSimulation(model2),1);
        assertEquals(simuUnderTest.addAndRunSimulation(model3),2);
    }

    @Test
    public void pauseSimulation(){

        simuUnderTest = SimulateurManager.getInstance();
        simuUnderTest.pauseSimulation(-100);
        simuUnderTest.pauseSimulation(0);
        assertEquals(simuUnderTest.addAndRunSimulation(model1),0);
        simuUnderTest.pauseSimulation(0);
        simuUnderTest.pauseSimulation(1);
        assertEquals(simuUnderTest.addAndRunSimulation(model2),1);
        simuUnderTest.pauseSimulation(1);
        simuUnderTest.pauseSimulation(10000);
        simuUnderTest.pauseSimulation(2);
        assertEquals(simuUnderTest.addAndRunSimulation(model3),2);
        simuUnderTest.pauseSimulation(2);
        simuUnderTest.pauseSimulation(0);

    }

    @Test
    public void resumeSimulation(){

        simuUnderTest = SimulateurManager.getInstance();
        simuUnderTest.pauseSimulation(-100);
        simuUnderTest.resumeSimulation(0);
        simuUnderTest.pauseSimulation(0);
        assertEquals(simuUnderTest.addAndRunSimulation(model1),0);
        simuUnderTest.pauseSimulation(0);
        simuUnderTest.resumeSimulation(0);
        simuUnderTest.pauseSimulation(1);
        assertEquals(simuUnderTest.addAndRunSimulation(model2),1);
        simuUnderTest.pauseSimulation(1);
        simuUnderTest.resumeSimulation(1);
        simuUnderTest.pauseSimulation(10000);
        simuUnderTest.pauseSimulation(2);
        assertEquals(simuUnderTest.addAndRunSimulation(model3),2);
        simuUnderTest.pauseSimulation(2);
        simuUnderTest.resumeSimulation(2);
        simuUnderTest.pauseSimulation(0);
    }

}