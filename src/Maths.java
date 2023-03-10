/**
 * All the maths involved
 */
public class Maths {

    /**
     * Formula found on the internet (converted from JS)
     * @param lat1 The lat of point 1
     * @param lon1 The long of point 1
     * @param lat2 The lat of point 2
     * @param lon2 The long of point 2
     * @return The distance between two points in kilometers
     */
    public double getDistanceFromLatLonInKm(double lat1,double lon1,double lat2,double lon2) {
        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }


    /**
     * @param deg The degree value
     * @return the radian value of the degree
     */
    public double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

}
