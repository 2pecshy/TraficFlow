package services.configuration;

/**
 * Created by Matthieu on 14/01/2018.
 */
public class SimulationWebConfiguration {
    private int simulationLenght;
    private int simulationStart;
    private boolean HOVLanes;
    private boolean migrationPendulaire;

    public SimulationWebConfiguration(){

    }

    public void sendConfigToSimulator(Object simu){
        /*
        simu.setSimulationStart(simulationStart);
        simu.setSimulationLength(simulationLength);
        simu.setHOVLanes(HOVLanes)
        simu.setMigrationPendulaire(migrationPendulaire)
         */
    }

    public int getSimulationLenght() {
        return simulationLenght;
    }

    public void setSimulationLenght(int simulationLenght) {
        this.simulationLenght = simulationLenght;
    }

    public int getSimulationStart() {
        return simulationStart;
    }

    public void setSimulationStart(int simulationStart) {
        this.simulationStart = simulationStart;
    }

    public boolean getHOVLanes() {
        return HOVLanes;
    }

    public void setHOVLanes(boolean HOVLanes) {
        this.HOVLanes = HOVLanes;
    }

    public boolean getMigrationPendulaire() {
        return migrationPendulaire;
    }

    public void setMigrationPendulaire(boolean migrationPendulaire) {
        this.migrationPendulaire = migrationPendulaire;
    }

    public String toString(){
        return "Start at : " + simulationStart + ", during : " + simulationLenght + "\n HOV Lanes : " + HOVLanes + " Migration pendulaire : " + migrationPendulaire;
    }
}
