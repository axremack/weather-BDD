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

        WeatherFetcher wf = new WeatherFetcher();
        CityWeather weather = wf.getWeatherIn("Clermont");

        List<Object> list = new ArrayList<>() {{
            add(weather.getDT());
            add(weather.getCity());
            add(weather.getTemp().getTemp());
            add(weather.getWind().getSpeed());
        }};

        d.insertValues(list);

    }
}
