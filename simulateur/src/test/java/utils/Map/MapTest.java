package utils.Map;

public class MapTest {

   /* private Map mapUnderTest;


    @Test
    void constructorTest(){

        mapUnderTest = new Map();
        assertEquals(0,mapUnderTest.getCarrefours().size());
        mapUnderTest = new Map(1000);
        assertEquals(1000,mapUnderTest.getCarrefours().size());
    }

    @Test
    void copyConstructor(){
        Map copy;
        Integer nb_carrefours = 1000;
        mapUnderTest = new Map(nb_carrefours);
        for (Integer i = 1; i < nb_carrefours;i++){
            mapUnderTest.addRoute(0,i,i%3);
        }
        copy = new Map(mapUnderTest);

        for (Integer i = 1; i < nb_carrefours;i++){
            assertEquals(copy.getRoute(0,i).getV1(),mapUnderTest.getRoute(0,i).getV1());
            assertEquals(copy.getRoute(0,i).getV2(),mapUnderTest.getRoute(0,i).getV2());
            assertEquals(copy.getRoute(0,i).getCout(EnumCriter.VOIES),mapUnderTest.getRoute(0,i).getCout(EnumCriter.VOIES));
        }
        assertTrue(copy.getRoute(0,20).getCout(EnumCriter.VOIES) == mapUnderTest.getRoute(0,20).getCout(EnumCriter.VOIES));
        copy.getRoute(0,20).setNombre_de_voie(10);
        assertTrue(copy.getRoute(0,20).getCout(EnumCriter.VOIES) != mapUnderTest.getRoute(0,20).getCout(EnumCriter.VOIES));
    }

    @Test
    void addRouteTest(){
        mapUnderTest = Map.getDefaultMap();
        assertFalse(mapUnderTest.addRoute(0,1,2));
        assertTrue(mapUnderTest.addRoute(3,2,2));
        assertFalse(mapUnderTest.addRoute(3,2,3));
        assertFalse(mapUnderTest.addRoute(3,2,2));
        assertFalse(mapUnderTest.addRoute(50,30,2));
        assertFalse(mapUnderTest.addRoute(-10,-30,2));

        assertFalse(mapUnderTest.addRoute(0,2,-3));
    }

    @Test
    void addCarrefoursTest(){
        mapUnderTest = Map.getDefaultMap();
        int nb_carrefours = mapUnderTest.getCarrefours().size();
        mapUnderTest.addCarrefours();
        assertEquals(nb_carrefours+1,mapUnderTest.getCarrefours().size());
    }

    @Test
    void getCoutRoute(){
        mapUnderTest = Map.getDefaultMap();
        TraficFlowModel.INIT_Simulateur();
        mapUnderTest.addRoute(0,3,3);
        assertEquals(3,mapUnderTest.getCoutRoute(mapUnderTest.getRoute(0,3)));
    }*/
}
