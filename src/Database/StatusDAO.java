package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDAO implements DAO<Status> {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    private static List<Status> statuses = null;

    private Connection getConnection() throws SQLException {
        connection = DatabaseConnection.getInstance().getConnection();
        return connection;
    }


    public StatusDAO() {
        updateList();
    }

    @Override
    public Optional<Status> get(int statusId) {
        Status status = null;
        try {
            String queryString = "SELECT * FROM `status` WHERE id = ? LIMIT 1";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, statusId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");

                status = new Status(id, name, color);
            }
            System.out.println("Status Retrieved");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(status);
    }

    @Override
    public List<Status> getAll() {
        return statuses;
    }

    @Override
    public void add(Status status) {
        try {
            String queryString = "INSERT INTO `status`(id, name, color) VALUES(0,?,?)";
            connection = getConnection();
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(queryString);
            preparedStatement.setString(1, status.getName());
            preparedStatement.setString(2, status.getColor());
            preparedStatement.executeUpdate();

            updateList();
//            statuses.add(status);
            System.out.println("Status Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void update(Status status, String[] params) {
        try {
            String queryString = "UPDATE `status` SET name=? WHERE id=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, status.getName());
            preparedStatement.setInt(2, status.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void delete(Status status) {
        try {
            String queryString = "DELETE FROM `status` WHERE id=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, status.getId());
            preparedStatement.executeUpdate();


            updateList();
//            if (statuses.contains(status))
//                statuses.remove(status);
            System.out.println("Status Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Resetting ArrayList of statuses and populating it from database
     */
    private void updateList() {
        statuses = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM `status`";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Table columns (id, name, color)
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");

                Status status = new Status(id, name, color);
                statuses.add(status);
            }

            System.out.println("Status List Updated");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void testPrintAll() {
        for (Status status : statuses) {
            System.out.println(String.format("ID: %d - Name: %s - Color: %s", status.getId(), status.getName(), status.getColor()));
        }
    }
}
