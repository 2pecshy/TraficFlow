package service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Matthieu on 14/01/2018.
 */
public class SimulationWebConfiguration implements Serializable{
    @JsonProperty("simulationLength") private int simulationLenght;
    @JsonProperty("simulationStart") private int simulationStart;
    @JsonProperty("HOVLanes") private boolean HOVLanes;
    @JsonProperty("migrationPendulaire") private boolean migrationPendulaire;

    public SimulationWebConfiguration(JSONObject json){
        this.simulationLenght = json.getInt("simulationLength");
        this.simulationStart = json.getInt("simulationStart");
        this.HOVLanes = json.getBoolean("HOVLanes");
        this.migrationPendulaire = json.getBoolean("migrationPendulaire");
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

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("simulationLength", simulationLenght);
        json.put("simulationStart", simulationStart);
        json.put("HOVLanes", HOVLanes);
        json.put("migrationPendulaire", migrationPendulaire);
        return  json;
    }

    public String toString(){
        return "{ \"simulationLength\" : "+simulationLenght+", \"simulationStart\" : "+simulationStart+", \"HOVLanes\" : \""+HOVLanes + "\", \"migrationPendulaire\" : \""+migrationPendulaire+"\" }";
    }
}
