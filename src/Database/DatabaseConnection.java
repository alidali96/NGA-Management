package Database;

import Const.Const;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection = null;
    private static Connection connection = null;

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null)
            databaseConnection = new DatabaseConnection(Const.DB_NAME, Const.DB_USER, Const.DB_PASS);
        return databaseConnection;
    }

    public DatabaseConnection(String database, String user, String password) {

        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://php.scweb.ca/" + database, user, password);
                System.out.println("Database connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
