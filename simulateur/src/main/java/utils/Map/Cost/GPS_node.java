package utils.Map.Cost;

public class GPS_node {

    private double lon,lat;
    private long id;

    public GPS_node(long id_, double lat_, double lon_){

        id = id_;
        lat = lat_;
        lon = lon_;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj.getClass().getPackage()
                .equals(this.getClass().getPackage())
                && obj.getClass().getName()
                .equals(this.getClass().getName())) {

            GPS_node edge = (GPS_node) obj;

            if (this.id == edge.id)
                return true;
        }
        else if((obj.getClass().getPackage()
                .equals(String.class.getClass().getPackage())
                && obj.getClass().getName()
                .equals(String.class.getClass().getName()))){

            Integer tmp = Integer.valueOf((String)obj);

            if(this.id == tmp)
                return true;
        }

        return false;
    }
}
