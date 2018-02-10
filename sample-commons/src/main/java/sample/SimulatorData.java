package sample;

/**
 * Created by Michael on 09/02/2018.
 */
public class SimulatorData {

    private int id;
    private int nbCars;

    public SimulatorData() {
        this.id = 0;
        this.nbCars = 0;
    }

    public SimulatorData(int id, int nbCars) {
        this.id = id;
        this.nbCars = nbCars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbCars() {
        return nbCars;
    }

    public void setNbCars(int nbCars) {
        this.nbCars = nbCars;
    }

    @Override
    public String toString() {
        return "SimulatorData{" +
                "id=" + id +
                ", nbCars=" + nbCars +
                '}';
    }
}
