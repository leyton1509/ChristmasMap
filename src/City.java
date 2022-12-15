class City implements Comparable<City>{
    private String name;

    private String country;

    private String continent;
    private double latitude;
    private double longitude;

    private int offset = 0;

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getContinent() {
        return continent;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public City(String name, double latitude, double longitude, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }

    public String toString(){
        return country + " , " + name+ " , " + latitude + " , " + longitude + " , " + offset;
    }

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