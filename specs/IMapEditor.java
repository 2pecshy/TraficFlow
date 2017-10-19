package specs;

public interface IMapEditor {

    /*
    Va permettre de modifier des objets de type Carte ou Graph
     */

    /*
    Recupère la Carte du MapEditor
     */
    Carte getCarte();

    /*
    Effectue des modifications sur la carte C.
     */
    void modificationCarte(Carte c);

    /*
    Permet de sauver la Carte dans la DB
     */
    boolean saveCartetoDB(Carte c);



}