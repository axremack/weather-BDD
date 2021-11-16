import Database.DBManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/Database/weather.db";

        DBManager d = new DBManager(url);

        d.dropTable();
        d.createWeatherTable();

        List<Object> list = new ArrayList<>(){{
            add((int) (new java.util.Date().getTime() / 1000));
            add("city");
            add(20.0);
            add(3.0);
        }};

        d.insertValues(list);


    }
}
