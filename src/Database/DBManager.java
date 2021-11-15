package Database;

public class DBManager {
    public static String url;

    public DBManager(String fileName) {
        DBManager.url = "jdbc:sqlite:src/Database/" + fileName;
    }


}
