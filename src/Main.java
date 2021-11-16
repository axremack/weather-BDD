import CityWeather.CityWeather;
import Database.DBManager;
import WeatherParsing.WeatherFetcher;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/Database/weather.db";
        CityWeather weather = null;

        if (args.length != 1) {
            System.err.println("Usage : main.java city_name");
            throw new IllegalArgumentException();
        }

        try {
            DBManager d = new DBManager(url);
            d.deleteOldData(); // Deleting data if too old

            boolean found = d.findInDB(args[0]);

            if(!found) {
                WeatherFetcher weatherFetcher = new WeatherFetcher();
                weather = weatherFetcher.getWeatherIn(args[0]);
                System.out.println(weather); // Printing out weather fetched

                List<Object> list = new ArrayList<>();
                list.add(weather.getDT());
                list.add(weather.getCity());
                list.add(weather.getTemp().getTemp());
                list.add(weather.getWind().getSpeed());

                d.createWeatherTable(); // Creating table only if it does not already exist
                d.insertValues(list);   // Inserting new values in the database
            }

            d.displayDBOrderedBy("city");
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
