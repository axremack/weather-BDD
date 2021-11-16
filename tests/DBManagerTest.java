import Database.DBManager;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagerTest extends TestCase {

    private String url = "jdbc:sqlite:tests/test.db";
    private List<String> listColumns = new ArrayList<>(){{
        add("fetchedAt");
        add("city");
        add("general_weather_type");
        add("general_weather_description");
        add("current_temperature");
        add("felt_temperature");
        add("min_temperature");
        add("max_temperature");
        add("pressure");
        add("humidity");
        add("wind_speed");
        add("wind_deg");
        add("wind_gust");
    }};

    private List<String> listTypes = new ArrayList<>(){{
        add("INTEGER");
        add("VARCHAR(100)");
        add("VARCHAR(100)");
        add("VARCHAR(100)");
        add("DOUBLE");
        add("DOUBLE");
        add("DOUBLE");
        add("DOUBLE");
        add("INTEGER");
        add("INTEGER");
        add("DOUBLE");
        add("DOUBLE");
        add("DOUBLE");
    }};

    // Les deux méthodes suivantes servent à empêcher les effets de bord
    @Override
    protected void setUp () throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    // Testing table creation
    @Test
    public void testTableCreation() {
        DBManager d = new DBManager(url);
        d.createWeatherTable();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                // Verifying if the table is added to the database
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet rs_debug = metaData.getTables(null, null, "weather", null);
                String nomTable = rs_debug.getString (3);
                assertEquals("weather", nomTable);

                ResultSet columns = metaData.getColumns(null, null, "weather", "%");
                int i = 0;
                while (columns.next()){
                    String column_name = columns.getString("COLUMN_NAME");
                    String column_type = columns.getString("TYPE_NAME");
                    assertEquals(listColumns.get(i), column_name);
                    assertEquals(listTypes.get(i), column_type);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
