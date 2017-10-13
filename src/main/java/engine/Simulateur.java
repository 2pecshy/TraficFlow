package engine;

public class Simulateur {

    private static Simulateur instance = null;

    public static Simulateur getInstance() throws Exception {

        if(instance == null)
            throw new java.lang.RuntimeException("Null floating point on engine.Simulateur!");
        return instance;
    }

    private Simulateur() {

    }

    public static boolean INIT_Simulateur(){
        if(instance == null) {
            System.out.println("INIT: engine.Simulateur");
            instance = new Simulateur();
            return true;
        }
        System.out.println("engine.Simulateur Already Running");
        return false;
    }

    public static boolean KILL_Simulateur(){
        if(instance != null) {
            System.out.println("Killing: engine.Simulateur");
            instance = null;
            return true;
        }
        System.out.println("engine.Simulateur Already dead");
        return false;
    }
}
