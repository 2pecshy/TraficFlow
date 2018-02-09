package engine.Contexts;

import engine.Agent.Cars;
import engine.Event.EndOfSimulation;
import engine.Event.Events;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.Map.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TraficFlowContextTest {

    TraficFlowContext traficFlowContext;
    Map map;
    Cars car;

    @BeforeEach
    void setUp() {
        map = new Mockito().mock(Map.class);
    }

    @AfterEach
    void cleanUp(){

    }

    @Test
    void onTickTest(){

        int nb_agent = 1000;
        traficFlowContext = new TraficFlowContext(map);

        for(int i = 0; i < nb_agent; i++){
            car = new Cars(){

                @Override
                public void onTick(){

                }
            };
            traficFlowContext.addAgent(car);
        }
        assertEquals(traficFlowContext.getAgents().size(),nb_agent);
        for(int i = 0; i < 1000; i++){
            traficFlowContext.onTick();
        }
    }

    @Test
    void eventTest(){
        EndOfSimulation event;
        traficFlowContext = new TraficFlowContext(map);
        event = new EndOfSimulation(traficFlowContext);
        traficFlowContext.addEvent(event);
        for(int i = 0; i <= event.getNb_ticks_to_end() +1; i++){
            traficFlowContext.onTick();
            assertFalse(traficFlowContext.isFinish());
        }
        assertFalse(traficFlowContext.isFinish());

        event.onStart();
        traficFlowContext.onTick();
        assertTrue(traficFlowContext.isFinish());
    }
}
