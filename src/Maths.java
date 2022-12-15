import java.util.ArrayList;

public class Maths {

    private int normalizedLow = -12;
    private int normalizedHigh = 12;

    public double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
        var R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        var dLon = deg2rad(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    public double normalize(double x, double dataLow, double dataHigh) {
        return ((x - dataLow)
                / (dataHigh - dataLow))
                * (normalizedHigh - normalizedLow) + normalizedLow;
    }

    public int getIndexOfMin(ArrayList<Double> data) {
        double min = Double.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            double f = data.get(i);
            if (Double.compare(f, min) < 0) {
                min = f;
                index = i;
            }
        }
        return index;
    }

    public int getIndexOfMax(ArrayList<Double> data) {
        double max = -1;
        int index = -1;
        for (int i = 0; i < data.size(); i++) {
            double f = data.get(i);
            if (Double.compare(f, max) > 0) {
                max = f;
                index = i;
            }
        }
        return index;
    }




    public double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

}
