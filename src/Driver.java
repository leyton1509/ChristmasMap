import com.github.sh0nk.matplotlib4j.Plot;
import com.github.sh0nk.matplotlib4j.PythonExecutionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.util.*;

public class Driver {

    public static void main(String[] args) throws PythonExecutionException, IOException {

        /*

        Set<String> allZones = ZoneId.getAvailableZoneIds();
        LocalDateTime dt = LocalDateTime.now();

        List<String> zoneList = new ArrayList<>(allZones);
        Collections.sort(zoneList);

        for (String s : zoneList) {
            ZoneId zone = ZoneId.of(s);
            ZonedDateTime zdt = dt.atZone(zone);
            ZoneOffset offset = zdt.getOffset();
            String out = String.format("%35s %10s%n", zone, offset);
            System.out.println(out);

        }


         */

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
        System.out.println(locations.get(0));

        ArrayList<City> locs = new ArrayList<City>();

        for (String str:locations){
            String[] parts = str.split(",");

            //String city = parts[5] +"/"+parts[1];
            //ZoneOffset offset;
            /*
            if(zoneList.contains(city)){
                ZoneId zone = ZoneId.of(city);
                ZonedDateTime zdt = dt.atZone(zone);
                offset = zdt.getOffset();
            }
            else{

                ZoneId zone = ZoneId.of(parts[0]);
                ZonedDateTime zdt = dt.atZone(zone);
                offset = zdt.getOffset();

            }

             */
            // City ci = new City(parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[0], parts[5], null);
            // Country,Capital,Latitude,Longitude

            Maths mymaths = new Maths();

            City ci = new City(parts[1],Double.parseDouble(parts[2]), Double.parseDouble(parts[3]), parts[0]);
            locs.add(ci);
        }

        for (City c: locs) {
            System.out.println(c);
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

        ArrayList<Integer> offsets = new ArrayList<Integer>();


        for (int i = 0; i < latitude.size(); i++) {
            double newLat = myMaths.normalize(latitude.get(i), latitude.get(myMaths.getIndexOfMin(latitude)), latitude.get(myMaths.getIndexOfMax(latitude)));
            offsets.add((int) newLat);
        }

        Plot plt = Plot.create();
        plt.plot().add(longitude, latitude, "bo-").label("Locations");

        for (int i = 0; i < locs.size(); i++) {
            plt.text(longitude.get(i), latitude.get(i), city.get(i));
        }

        plt.legend().loc("upper right");
        plt.title("Santa's Route");
        plt.show();

        double total = 0;

        for (Double d: distances) {
            total = total + d;
        }

        System.out.println("Total : " + total);

        for (Integer i : offsets) {
            System.out.println(offsets);
        }



    }



}
