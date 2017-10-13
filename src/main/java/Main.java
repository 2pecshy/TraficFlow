import engine.Simulateur;

public class Main {

    /**
     * @param args
     * */
    public static void main(String [] args) {

        Simulateur simulateur0 = null;

        Simulateur.INIT_Simulateur();

        try {
            simulateur0 = Simulateur.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Simulateur.INIT_Simulateur();
        Simulateur.KILL_Simulateur();
        Simulateur.KILL_Simulateur();

        try {
            simulateur0 = Simulateur.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("simulateur need to be init before!");
        }
        return;
    }


}
