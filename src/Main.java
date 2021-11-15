import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:src/Database/weather.db";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
