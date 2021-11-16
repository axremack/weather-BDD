package Database;

import java.sql.*;
import java.util.List;

public class DBManager {
    public static String url;

    public DBManager(String url_given) {
        DBManager.url = url_given;
    }

    public static void createWeatherTable() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS weather (\n")
                .append("fetchedAt INTEGER NOT NULL,\n")
                .append("city VARCHAR(100) NOT NULL,\n")
                .append("general_weather_type VARCHAR(100) NOT NULL,\n")
                .append("general_weather_description VARCHAR(100) NOT NULL,\n")
                .append("current_temperature DOUBLE NOT NULL,\n")
                .append("felt_temperature DOUBLE NOT NULL,\n")
                .append("min_temperature DOUBLE NOT NULL,\n")
                .append("max_temperature DOUBLE NOT NULL,\n")
                .append("pressure INTEGER,\n")
                .append("humidity INTEGER,\n")
                .append("wind_speed DOUBLE,\n")
                .append("wind_deg DOUBLE,\n")
                .append("wind_gust DOUBLE,\n")
                .append("PRIMARY KEY (fetchedAt, city)\n")
                .append(");\n");

        // Printing the query to be sure of the syntax
        System.out.println(query);

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement s = conn.createStatement();
                s.execute(query.toString());
                System.out.println("Table successfully created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertValues(List<Object> list_values) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO weather(\n")
                .append("fetchedAt,\n")
                .append("city,\n")
                .append("general_weather_type,\n")
                .append("general_weather_description,\n")
                .append("current_temperature,\n")
                .append("felt_temperature,\n")
                .append("min_temperature,\n")
                .append("max_temperature,\n")
                .append("pressure,\n")
                .append("humidity,\n")
                .append("wind_speed,\n")
                .append("wind_deg,\n")
                .append("wind_gust\n")
                .append(")\n")
                .append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                PreparedStatement s = conn.prepareStatement(query.toString());
                s.setInt(1, (int) list_values.get(0));
                s.setString(2, (String) list_values.get(1));
                s.setString(3, (String) list_values.get(2));
                s.setString(4, (String) list_values.get(3));
                s.setDouble(5, (double) list_values.get(4));
                s.setDouble(6, (double) list_values.get(5));
                s.setDouble(7, (double) list_values.get(6));
                s.setDouble(8, (double) list_values.get(7));
                s.setInt(9, (int) list_values.get(8));
                s.setInt(10, (int) list_values.get(9));
                s.setDouble(11, (double) list_values.get(10));
                s.setDouble(12, (double) list_values.get(11));
                s.setDouble(13, (double) list_values.get(12));
                s.executeUpdate();
                System.out.println("Values have been added to database");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
