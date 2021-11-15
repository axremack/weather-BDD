import Database.DBManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String filename = "weather.db";

        /*try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        DBManager d = new DBManager(filename);
        d.createWeatherTable();


    }
}
