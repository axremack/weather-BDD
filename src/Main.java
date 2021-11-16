import Database.DBManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/Database/weather.db";

        DBManager d = new DBManager(url);
        d.createWeatherTable();


    }
}
