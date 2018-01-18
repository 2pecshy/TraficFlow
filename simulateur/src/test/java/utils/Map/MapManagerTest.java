package utils.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Matthieu on 01/11/2017.
 */
public class MapManagerTest {

    private Map mapExpected;
    private MapManagerI mapManagerTest;

    @BeforeEach
    public void init(){
        /*mapManagerTest = new MapManagerI();
        mapExpected = new Map();
        for(int i = 0; i < 4; i++) {
            mapExpected.addCarrefours();
        }
        mapExpected.addRoute(0,1,1);
        mapExpected.addRoute(1,2,1);
        mapExpected.addRoute(2,3,2);
        mapExpected.addRoute(3,1,3);
        mapExpected.addRoute(3,0,3);*/
    }



    @Test
    public void loadMapTest(){
       /* Map test = mapManagerTest.loadMap("file",".\\src\\main\\java\\carte1.txt");
        assertEquals(test.getCarrefours().size(), mapExpected.getCarrefours().size());
        assertNotNull(test.getRoute(0,1));
        assertNotNull(test.getRoute(1,2));
        assertNotNull(test.getRoute(2,3));
        assertNotNull(test.getRoute(3,1));
        assertNotNull(test.getRoute(3,0));*/
    }

}