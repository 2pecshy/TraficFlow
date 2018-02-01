package engine.Agent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.Map.Cost.GPS_node;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarsTest {

    Cars cars;
    GPS_node current_pos, endOfTravel, aPos;

    @BeforeEach
    void setUp(){
        current_pos = new Mockito().mock(GPS_node.class);
        endOfTravel = new Mockito().mock(GPS_node.class);
        aPos = new Mockito().mock(GPS_node.class);
    }

    @AfterEach
    void cleanUp(){

    }

    @Test
    void carsTest(){
        cars = new Cars();
        assertFalse(cars.isDead());
    }
}
