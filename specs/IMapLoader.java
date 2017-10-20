package specs;

public interface IMapLoader{

    /*
    Permet de charger une Carte depuis le disque
     */
    boolean loadCarteFromDisk(String path);

    /*
    Permet de charger une Carte depuis la DB en utilisant un id
     */
    boolean loadCarteFromDB(Int id);

}