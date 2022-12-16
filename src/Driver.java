import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Main class to run the program
 * Finds the distance and speed santa needs, close the map to get the results in the terminal
 */
public class Driver {

    /**
     * Main method to run program
     * @param args Arguments none
     * @throws PythonExecutionException e
     * @throws IOException e
     */
    public static void main(String[] args) throws PythonExecutionException, IOException {

        // Creates a string array from a csv file from the internet with the locations of each capital

        Scanner sc = null;
        ArrayList<String> locations = new ArrayList<>();
        try {
            sc = new Scanner(new File("src/Locations.csv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc.useDelimiter("\n");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            locations.add(sc.next());
        }
        sc.close();  //closes the scanner

        // Removes the header

        locations.remove(0);

        // Creates an array list of cities

        ArrayList<City> locs = new ArrayList<>();

        // Splits each string from each ,
        // Puts the correct info into city

        for (String str:locations){
            String[] parts = str.split(",");
            City ci = new City(parts[1],Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[0]);
            locs.add(ci);
        }

        // Sorts the locations based on the longitude
        // Reverses the list

        Collections.sort(locs);
        Collections.reverse(locs);

        // Creates different array lists to store needed info for displaying and calculating

        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<Double> latitude = new ArrayList<>();
        ArrayList<Double> longitude = new ArrayList<>();
        ArrayList<String> city = new ArrayList<>();

        Maths myMaths = new Maths();

        // Loops through each city, adds the lat and long and city name
        // Works out the distances between the node and next node in array

        for (int i = 0; i < locs.size(); i++) {
            City c = locs.get(i);
            latitude.add(c.getLatitude());
            longitude.add(c.getLongitude());
            city.add(c.getName());
            if(i < locs.size()-1){
                City nc = locs.get(i+1);
                distances.add(myMaths.getDistanceFromLatLonInKm(c.getLatitude(), c.getLongitude(), nc.getLatitude(), nc.getLongitude()));
            }
        }

        // Works out the time zone of each city based on longitude
        // Not needed now as we took an assumption later
        int numOfTimeZones = 15;

        for (int i = 0; i < longitude.size(); i++) {
            double normLong = Math.round(longitude.get(i) / numOfTimeZones);
            locs.get(i).setOffset((int) normLong);
        }

        /*
        for (City c: locs) {
            System.out.println(c);
        }
         */

        // Creates a plot using matplotlib
        // Adds the longs and lats

        Plot plt = Plot.create();
        plt.plot().add(longitude, latitude, "bo-").label("Locations");

        // Option to add the names of cities (becomes unreadable on small screens)
        /*
        for (int i = 0; i < locs.size(); i++) {
            plt.text(longitude.get(i), latitude.get(i), city.get(i));
        }
        */

        // Sets title and shows map

        plt.legend().loc("upper right");
        plt.title("Santa's Route");
        plt.show();

        // Works out the total distance travelled

        double total = 0;

        for (Double d: distances) {
            total = total + d;
        }

        System.out.println("Distance : " +total+ " Kilometers");

        // He has 24 hours based on having the hours of 12-2am for 24 time zones but would only be able to spend 1 hour in each zone

        total = total / 24;

        System.out.println("Speed : " +total+ " Kilometers per hour");

    }

}
