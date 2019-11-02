package Database;

import Const.Const;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

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
                System.out.println("Database Connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTables() {
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                String query = "CREATE TABLE IF NOT EXISTS `project`(\n" +
                        "    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                        "    title VARCHAR(255) NOT NULL,\n" +
                        "    description MEDIUMTEXT NOT NULL\n" +
                        "    );";

                statement.executeUpdate(query);
                System.out.println("Tables Created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertRecord(String query, String... values) {
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                for (int i = 0; i < values.length; i++) {
                    statement.setString(i + 1, values[i]);
                }
                statement.executeUpdate();
                System.out.println("Record Inserted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
