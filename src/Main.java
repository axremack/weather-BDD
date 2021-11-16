import CityWeather.CityWeather;
import Database.DBManager;
import WeatherParsing.WeatherFetcher;

import java.util.ArrayList;
import java.util.Date;
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
            WeatherFetcher weatherFetcher = new WeatherFetcher();
            weather = weatherFetcher.getWeatherIn(args[0]);
            System.out.println(weather);

            List<Object> list = new ArrayList<>();
            list.add(weather.getDT());
            list.add(weather.getCity());
            list.add(weather.getTemp().getTemp());
            list.add(weather.getWind().getSpeed());

            DBManager d = new DBManager(url);
            d.createWeatherTable();
            d.insertValues(list);
            d.displayDBOrderedBy("city");
            d.deleteOldData();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
