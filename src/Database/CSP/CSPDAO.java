package Database.CSP;

import Const.Const;
import Database.CSP.Category.Category;
import Database.CSP.Priority.Priority;
import Database.CSP.Status.Status;
import Database.DAO;
import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CSPDAO implements DAO<CSP> {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private List<CSP> CSPes = null;

    private String table;

    public CSPDAO(String table) {
        this.table = table;
        connection = DatabaseConnection.getConnection();
        updateList();
    }

    @Override
    public Optional<? extends CSP> get(int cspID) {
        CSP csp = null;
        try {
            String queryString = "SELECT * FROM `" + table + "` WHERE id=? LIMIT 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, cspID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");

                switch (table) {
                    case Const.TABLE_STATUS:
                        csp = new Status(id, name, color);
                        break;
                    case Const.TABLE_CATEGORY:
                        csp = new Category(id, name, color);
                        break;
                    case Const.TABLE_PRIORITY:
                        csp = new Priority(id, name, color);
                        break;
                }
                System.out.println(csp.getName() + " Retrieved");
            } else {
                System.out.println(cspID + " id was not found");
            }
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
        return Optional.ofNullable(csp);
    }

    @Override
    public Optional<? extends CSP> get(String CSPName) {
        CSP csp = null;
        try {
            String queryString = "SELECT * FROM `" + table + "` WHERE name=? LIMIT 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, CSPName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");

                switch (table) {
                    case Const.TABLE_STATUS:
                        csp = new Status(id, name, color);
                        break;
                    case Const.TABLE_CATEGORY:
                        csp = new Category(id, name, color);
                        break;
                    case Const.TABLE_PRIORITY:
                        csp = new Priority(id, name, color);
                        break;
                }
                System.out.println(csp.getName() + " Retrieved");
            } else {
                System.out.println(CSPName + " was not found");
            }
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
        return Optional.ofNullable(csp);
    }

    @Override
    public List<? extends CSP> getAll() {
        return CSPes;
    }

    @Override
    public void create(CSP csp) {
        if (!get(csp.getName()).isPresent()) {
            try {
                String queryString = "INSERT INTO `" + table + "`(id, name, color) VALUES(0,?,?)";
                preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, csp.getName());
                preparedStatement.setString(2, csp.getColor());
                preparedStatement.executeUpdate();

                // Get last inserted ID and set it to the CSP object to add to the CSPes list
                ResultSet result = preparedStatement.getGeneratedKeys();
                if (result.next()) {
                    int id = result.getInt(1);
                    csp.setId(id);
                    CSPes.add(csp);
                }
                result.close();

                System.out.println(csp.getName() + " Inserted");
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
        } else {
            System.out.println(csp.getName() + " already exists");
        }
    }

    @Override
    public void update(CSP csp) {
        try {
            // Check if name already exist do not update it (prevent duplicate names)
            if (!get(csp.getName()).isPresent()) {
                String queryString = "UPDATE `" + table + "` SET name=? , color=? WHERE id=?";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, csp.getName());
                preparedStatement.setString(2, csp.getColor());
                preparedStatement.setInt(3, csp.getId());
            } else {
                String queryString = "UPDATE `" + table + "` SET color=? WHERE id=?";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setString(1, csp.getColor());
                preparedStatement.setInt(2, csp.getId());
            }

            preparedStatement.executeUpdate();

            // Update CSP name and color in CSPes list :)
            for (CSP oldCSP : CSPes) {
                if (csp.getId() == oldCSP.getId()) {
                    oldCSP.setName(csp.getName());
                    oldCSP.setColor(csp.getColor());
                    break;
                }
            }
            System.out.println(csp.getName() + " Updated");
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
    public void delete(CSP csp) {
        if (get(csp.getId()).isPresent()) {
            try {
                String queryString = "DELETE FROM `" + table + "` WHERE id=?";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, csp.getId());
                preparedStatement.executeUpdate();

                // Delete CSP if it exist in CSPes list :)
                for (CSP oldCSP : CSPes) {
                    if (csp.getId() == oldCSP.getId()) {
                        CSPes.remove(csp);
                        break;
                    }
                }
                System.out.println(csp.getName() + " Deleted");
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
        } else {
            System.out.println(csp.getId() + " id is not found (Can't delete)");
        }
    }

    /**
     * Populating CSPes ArrayList from database
     */
    @Override
    public void updateList() {
        CSPes = new ArrayList<>();
        CSP csp = null;
        try {
            String queryString = "SELECT * FROM `" + table + "`";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Table columns (id, name, color)
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");

                switch (table) {
                    case Const.TABLE_STATUS:
                        csp = new Status(id, name,color);
                        break;
                    case Const.TABLE_CATEGORY:
                        csp = new Category(id, name,color);
                        break;
                    case Const.TABLE_PRIORITY:
                        csp = new Priority(id, name, color);
                        break;
                }
                CSPes.add(csp);
            }

            System.out.println("List Updated");
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
        for (CSP csp : CSPes) {
            System.out.println(csp);
        }
    }
}
