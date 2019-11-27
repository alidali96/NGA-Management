package Database;

import Const.Const;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection = null;
    private static Connection connection = null;

    private String error = "Unknown";

    public static DatabaseConnection getInstance() {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection();
            System.out.println("Connection Instance Created");
        }
        System.out.println("Connection returned");
        return databaseConnection;
    }

    private DatabaseConnection() {

    }

    public boolean createConnection(String host, String name, String user, String password) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name, user, password);
//                connection=DriverManager.getConnection("jdbc:mysql://php.scweb.ca/"+database+"?user="+user+"&password="+password);
//                  connection=DriverManager.getConnection("jdbc:mysql://localhost/NGA_Management?user=root&password=webmaster&useSSL=false");

            System.out.println("Database Connected");
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            error = e.getMessage();
            System.out.println("Failed");
        }
        return isConnected();
    }

    public void createTable() {
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

    public boolean isConnected() {
        return connection != null;
    }

    public String getError() {
        return error;
    }
}
