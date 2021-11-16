import Database.DBManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String filename = "weather.db";

        DBManager d = new DBManager(filename);
        d.createWeatherTable();


    }
}
