import Database.DBManager;
import junit.framework.TestCase;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManagerTest extends TestCase {

    private String url = "jdbc:sqlite:tests/test.db";
    private DBManager d;

    private List<String> listColumns = new ArrayList<>(){{
        add("fetchedAt");
        add("city");
        add("current_temperature");
        add("wind_speed");
    }};

    private List<String> listTypes = new ArrayList<>(){{
        add("INTEGER");
        add("VARCHAR(100)");
        add("DOUBLE");
        add("DOUBLE");
    }};

    private List<Object> listValues = new ArrayList<>(){{
        add(3);
        add("city");
        add(20.0);
        add(3.0);
    }};


    // Les deux méthodes suivantes servent à empêcher les effets de bord
    @Override
    protected void setUp () throws Exception {
        super.setUp();
        d = new DBManager(url);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        //d.dropTable();
    }

    // Testing table creation
    @Test
    public void testTableCreation() {
        d.createWeatherTable();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
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

    // Testing value insertion
    @Test
    public void testValueInsertion() {
        d.createWeatherTable();
        d.insertValues(listValues);

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery ("SELECT * FROM weather");

                while (rs.next()) {
                    assertEquals(listValues.get(0), rs.getInt(1));
                    assertEquals(listValues.get(1), rs.getString(2));
                    assertEquals(listValues.get(2), rs.getDouble(3));
                    assertEquals(listValues.get(3), rs.getDouble(4));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
