package engine;

import engine.Simulateur;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimulateurTest {

    private Simulateur simuUndeTest;

    @BeforeAll
    static void setUp(){
        Simulateur.INIT_Simulateur();
    }
    @AfterAll
    static void cleanUp(){
        Simulateur.KILL_Simulateur();
    }

    @Test
    void instanceTest() {
        simuUndeTest = Simulateur.getInstance();
        assertEquals(Simulateur.getInstance(), simuUndeTest);
        assertTrue(Simulateur.KILL_Simulateur());
        assertFalse(Simulateur.KILL_Simulateur());
        assertTrue(Simulateur.INIT_Simulateur());
        assertFalse(Simulateur.INIT_Simulateur());
    }

}