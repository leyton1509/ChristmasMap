/**
 * Class to represent each city
 * Implements comparable to be compared
 */
public class City implements Comparable<City>{
    /**
     * The name of the city
     */
    private String name;

    /**
     * The of the county
     */
    private String country;

    /**
     * The latitude of the city
     */
    private double latitude;

    /**
     * The longitude of the city
     */
    private double longitude;

    /**
     * The timezone offset
     */
    private int offset = 0;

    /**
     * @return The name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * @return The latitude of the city
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return The longitude of the city
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return The country name
     */
    public String getCountry() {
        return country;
    }


    /**
     * @return The time zone offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset Sets the time zone offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Constructor for city
     * @param name The name of the city
     * @param latitude The latitude of the city
     * @param longitude The longitude of the city
     * @param country The name of the country
     */
    public City(String name, double latitude, double longitude, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    /**
     * @return The String of country, overrides toString.
     */
    public String toString(){
        return country + " , " + name+ " , " + latitude + " , " + longitude + " , " + offset;
    }

    /**
     * @param o the city to be compared.
     * @return An integer depending on if its <,==,>
     */
    @Override
    public int compareTo(City o) {

        if(longitude < o.longitude){
            return - 1;
        }else if(longitude == o.longitude){
            return 0;
        }else if(longitude > o.longitude){
            return 1;
        }
        else{
            return 0;
        }

        /*

        if(latitude < o.latitude){
            return  - 1;
        }else if(latitude == o.latitude){
            return 0;
        }else if(latitude > o.latitude){
            return 1;
        }
        else{
            return 0;
        }
        */



    }
}