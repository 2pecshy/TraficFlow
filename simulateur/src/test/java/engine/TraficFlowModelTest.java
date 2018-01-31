package engine;
import engine.Contexts.TraficFlowContext;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import utils.Map.Map;

public class TraficFlowModelTest {


    TraficFlowContext traficFlowContext;
    TraficFlowModel traficFlowModel;
    Map map;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        map = new Mockito().mock(Map.class);
        traficFlowContext = new Mockito().mock(TraficFlowContext.class);
        traficFlowModel = new TraficFlowModel(map){
            @Override
            protected void startSimulation(){
                if(map == null) {
                    isRunning = NOT_RUNNING;
                    System.out.println("no simulation to run");
                    return;
                }
                isRunning = RUNNING;
                setContext(traficFlowContext);
                super.start();
            }
        };
    }

    @AfterEach
    void cleanUp(){

    }

    @Test
    void test1(){

        traficFlowModel.startSimulation();
    }

}
