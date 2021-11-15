package Database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

public class DBManager {
    public static String url;

    public DBManager(String fileName) {
        DBManager.url = "jdbc:sqlite:src/Database/" + fileName;
    }

    public static void createWeatherTable() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS weather (\n")
                .append("general_weather_type")
                .append("general_weather_description")
                .append("current_temperature")
                .append("felt_temperature")
                .append("min_temperature")
                .append("max_temperature")
                .append("pressure")
                .append("humidity")
                .append("wind_speed")
                .append("wind_deg")
                .append("wind_gust")
                .append(")\n");

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement s = conn.createStatement();
                s.execute(query.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
