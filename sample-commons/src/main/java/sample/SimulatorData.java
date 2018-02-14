package sample;

import org.springframework.data.annotation.Id;

/**
 * Created by Michael on 09/02/2018.
 */
public class SimulatorData {

    @Id
    private String mongo_id;

    private String id;
    private int nbCars;
    private long tick;
//    private Date date;

    public SimulatorData() {
        this.id = "0";
        this.nbCars = 0;
        this.tick = 0;
//        this.date = new Date();
    }

    public SimulatorData(String id, int nbCars, long tick) {
        this.id = id;
        this.nbCars = nbCars;
        this.tick = tick;
//        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNbCars() {
        return nbCars;
    }

    public void setNbCars(int nbCars) {
        this.nbCars = nbCars;
    }

    public long getTick() {
        return tick;
    }

    public void setTick(long tick) {
        this.tick = tick;
    }

    //    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }


    @Override
    public String toString() {
        return "SimulatorData{" +
                "mongo_id='" + mongo_id + '\'' +
                ", id='" + id + '\'' +
                ", nbCars=" + nbCars +
                ", tick=" + tick +
                '}';
    }
}
