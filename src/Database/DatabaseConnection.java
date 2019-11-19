package Database;

import Const.Const;

import java.sql.*;
import java.util.ArrayList;

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
//                connection = DriverManager.getConnection("jdbc:mysql://php.scweb.ca/" + database, user, password);
//                connection=DriverManager.getConnection("jdbc:mysql://php.scweb.ca/"+database+"?user="+user+"&password="+password);
                  connection=DriverManager.getConnection("jdbc:mysql://localhost/NGA_Management?user=root&password=webmaster&useSSL=false");


                System.out.println("Database Connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  void createTable() {
        if (connection != null) {
            try {
//                Statement statement = connection.createStatement();
//                String query = "";
//
//                statement.executeUpdate(query);
//                System.out.println("Tables Created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public  void insertRecord(String query, String... values) {
//        if (connection != null) {
//            try {
//                PreparedStatement statement = connection.prepareStatement(query);
//                for (int i = 0; i < values.length; i++) {
//                    statement.setString(i + 1, values[i]);
//                }
//                statement.executeUpdate();
//                System.out.println("Record Inserted");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public  ResultSet fetchRecords(String query) {
//        if (connection != null) {
//            try {
//                Statement statement = connection.createStatement();
//
//                return statement.executeQuery(query);
//
////                while(result.next()) {
////                    int id = result.getInt("id");
////                    String title = result.getString("title");
////                    String description = result.getString("description");
////
////                    System.out.println("ID:" + id + " - Title: " + title + " - Description: " + description);
////
////                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }

    public static Connection getConnection() {
        return connection;
    }
}
