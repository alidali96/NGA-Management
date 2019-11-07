package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusDAO implements DAO<Status> {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private static List<Status> statuses = null;

//    private Connection getConnection() throws SQLException {
//        connection = DatabaseConnection.getInstance().getConnection();
//        return connection;
//    }


    public StatusDAO() {
//        connection = DatabaseConnection.getInstance().getConnection();
        connection = DatabaseConnection.getConnection();
        updateList();
    }

    @Override
    public Optional<Status> get(int statusId) {
        Status status = null;
        try {
            String queryString = "SELECT * FROM `status` WHERE id = ? LIMIT 1";
//            connection = getConnection();
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
//            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, status.getName());
            preparedStatement.setString(2, status.getColor());
            preparedStatement.executeUpdate();

            // Get last inserted ID and set it to the status object to add to the statuses list
            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                status.setId(id);
                statuses.add(status);
            }
            result.close();

            System.out.println("Status Inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void update(Status status) {
        try {
            String queryString = "UPDATE `status` SET name=? , color=? WHERE id=?";
//            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, status.getName());
            preparedStatement.setString(2, status.getColor());
            preparedStatement.setInt(3, status.getId());
            preparedStatement.executeUpdate();

            // Update status name and color in statuses list ;)
//            statuses.get(statuses.indexOf(status)).setColor(status.getColor()).setName(status.getName());

            for(Status st: statuses) {
                if (status.getId() == st.getId()) {
                    st.setName(status.getName());
                    st.setColor(status.getColor());
                    //break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
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
//            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, status.getId());
            preparedStatement.executeUpdate();


            if (statuses.contains(status))
                statuses.remove(status);
            System.out.println("Status Deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Populating statuses ArrayList from database
     */
    private void updateList() {
        statuses = new ArrayList<>();
        try {
            String queryString = "SELECT * FROM `status`";
//            connection = getConnection();
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
