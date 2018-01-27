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

    public static double distFromGpsPos(GPS_node pos1, GPS_node pos2){
        double R = 6371e3; // metres
        double lat1 = pos1.lat;
        double lat2 = pos2.lat;
        double lon1 = pos1.lon;
        double lon2 = pos2.lon;
        double φ1 = Math.toRadians(lat1);
        double φ2 = Math.toRadians(lat2);
        double Δφ = Math.toRadians(lat2-lat1);
        double Δλ = Math.toRadians(lon2-lon1);
        double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) +
                Math.cos(φ1) * Math.cos(φ2) *
                        Math.sin(Δλ/2) * Math.sin(Δλ/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d; //in M
    }
}
