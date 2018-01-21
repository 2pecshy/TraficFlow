package utils.Map.Osm;

public class Tag {

    private String k,v;

    Tag(String k_, String v_){

        k = k_;
        v = v_;
    }

    public String getK() {
        return k;
    }

    public String getV() {
        return v;
    }
}
