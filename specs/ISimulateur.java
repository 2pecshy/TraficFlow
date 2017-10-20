package specs;

public interface ISimulateur{

    /*
    Initialise le simulateur avec les acteurs qui vont bien
     */
    boolean initSimulateur();

        /*
        Lance la simulation
         */
    boolean launchSimulation();

    /*
    Permet de recuperer les données brutes produite par la simulation
     */
    RawData getData();

    /*
    Permet de sauvegarder les données brutes dans la DB en leur associant un id
     */
    boolean saveRawData(RawData rawData, int id);


}