package Database;

import java.sql.*;

public class DBManager {
    public static String url;

    public DBManager(String fileName) {
        DBManager.url = "jdbc:sqlite:src/Database/" + fileName;
    }

    public static void createWeatherTable() {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS weather (\n")
                .append("fetchedAt INTEGER PRIMARY KEY NOT NULL,\n")
                .append("city VARCHAR(100) PRIMARY KEY NOT NULL,\n")
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
                .append("wind_gust DOUBLE\n")
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

                // Verifying if the table is added to the database
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet rs_debug = metaData.getTables(null, null, "%", null);
                while (rs_debug.next()) {
                    String nomTable = rs_debug.getString (3);
                    System.out.println(nomTable);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
