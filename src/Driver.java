import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Driver {



    public static void main(String[] args) throws PythonExecutionException, IOException {

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

        locations.remove(0);

        ArrayList<City> locs = new ArrayList<City>();

        for (String str:locations){
            String[] parts = str.split(",");
            City ci = new City(parts[1],Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[0]);
            locs.add(ci);
        }



        Collections.sort(locs);
        Collections.reverse(locs);

        ArrayList<Double> distances = new ArrayList<Double>();
        ArrayList<Double> latitude = new ArrayList<Double>();
        ArrayList<Double> longitude = new ArrayList<Double>();
        ArrayList<String> city = new ArrayList<String>();
        Maths myMaths = new Maths();

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

        // ArrayList<Integer> offsets = new ArrayList<Integer>();

        double maxL = 180;
        double minL = -180;
        int numOfTimeZones = 15;

        for (int i = 0; i < longitude.size(); i++) {
            double normLong = Math.round(longitude.get(i) / numOfTimeZones);
            locs.get(i).setOffset((int) normLong);
        }

        for (City c: locs) {
            System.out.println(c);
        }

        Plot plt = Plot.create();
        plt.plot().add(longitude, latitude, "bo-").label("Locations");

        /*
        for (int i = 0; i < locs.size(); i++) {
            plt.text(longitude.get(i), latitude.get(i), city.get(i));
        }
        */

        plt.legend().loc("upper right");
        plt.title("Santa's Route");
        plt.show();

        double total = 0;

        for (Double d: distances) {
            total = total + d;
        }

        System.out.println("Distance : " +total+ " Kilometers");

        total = total / 24;

        System.out.println("Speed : " +total+ " Kilometers per hour");



    }



}
