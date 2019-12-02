package Database;

import Const.Const;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static DatabaseConnection databaseConnection = null;
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

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

    public boolean createConnection(String host, String database, String user, String password) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database, user, password);
//                connection=DriverManager.getConnection("jdbc:mysql://php.scweb.ca/"+Const.DB_NAME+"?user="+Const.DB_USER+"&password="+Const.DB_PASS);
                  connection=DriverManager.getConnection("jdbc:mysql://localhost:8888/NGA_PROJECT?user=root&password=root&useSSL=false");

            System.out.println("Database Connected");

            createTable(Const.CREATE_TABLE_PROJECT);
            createTable(Const.CREATE_TABLE_TASKS);
            createTable(Const.CREATE_TABLE_CATEGORY);
            createTable(Const.CREATE_TABLE_STATUS);
            createTable(Const.CREATE_TABLE_PRIORITY);

            alterTables(Const.ALTER_TABLES_FOREIGN_KEYS);

        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            error = e.getMessage();
            System.out.println("Failed");
        }
        return isConnected();
    }

    public void createTable(String sqlQuery) {
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.executeUpdate();
                System.out.println(sqlQuery);
            } catch (SQLException e) {
                e.printStackTrace();
                error = e.getMessage();
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void alterTables(String[] sqlQueries) {
        if (connection != null) {
            try {
                for(String sqlQuery: sqlQueries) {
                    preparedStatement = connection.prepareStatement(sqlQuery);
                    preparedStatement.executeUpdate();
                    System.out.println(sqlQuery);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                error = e.getMessage();
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
