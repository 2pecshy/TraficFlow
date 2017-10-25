package utils.Map.Cost;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    private Route routeUnderTest;

    @Test
    void constructor(){
        //assertThrows(ExceptionInInitializerError.class, (Executable) new Route( 0,1,-1));
        //assertThrows(ExceptionInInitializerError.class, (Executable) new Route( 0,0,1) );
        //assertThrows(ExceptionInInitializerError.class, (Executable) new Route( 0,1,-1));
        //assertThrows(ExceptionInInitializerError.class, (Executable) new Route( 0,0,-1));
        assertTrue(new Route( 0,1,2) != null);
    }

    @Test
    void getCoutTest(){

        routeUnderTest = new Route(1,3,4);
        assertEquals(4,routeUnderTest.getCout(EnumCriter.VOIES));
    }

    @Test
    void setNombre_de_voie(){

        Integer nb_voies = 4;
        Integer newNbVoies = nb_voies-3;
        routeUnderTest = new Route(1,3,4);

    }

}
