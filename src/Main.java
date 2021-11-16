import CityWeather.CityWeather;
import Database.DBManager;
import WeatherParsing.WeatherFetcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:src/Database/weather.db";

        DBManager d = new DBManager(url);

        d.dropTable();
        d.createWeatherTable();

        WeatherFetcher wf1 = new WeatherFetcher();
        CityWeather weather1 = wf1.getWeatherIn("Clermont");

        List<Object> list1 = new ArrayList<>() {{
            add(weather1.getDT());
            add(weather1.getCity());
            add(weather1.getTemp().getTemp());
            add(weather1.getWind().getSpeed());
        }};

        WeatherFetcher wf2 = new WeatherFetcher();
        CityWeather weather2 = wf2.getWeatherIn("Vichy");

        List<Object> list2 = new ArrayList<>() {{
            add(weather2.getDT());
            add(weather2.getCity());
            add(weather2.getTemp().getTemp());
            add(weather2.getWind().getSpeed());
        }};

        WeatherFetcher wf3 = new WeatherFetcher();
        CityWeather weather3 = wf3.getWeatherIn("Reims");

        List<Object> list3 = new ArrayList<>() {{
            add(weather3.getDT());
            add(weather3.getCity());
            add(weather3.getTemp().getTemp());
            add(weather3.getWind().getSpeed());
        }};

        d.insertValues(list1);
        d.insertValues(list2);
        d.insertValues(list3);

        System.out.println();
        d.displayDB();


    }
}
