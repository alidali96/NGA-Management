package Database.Project;

import Const.Const;
import Database.DAO;
import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDAO implements DAO<Project> {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private static ArrayList<Project> projects;

    private static ProjectDAO projectDAO = new ProjectDAO();

    public static ProjectDAO getInstance() {
        return projectDAO;
    }

    private ProjectDAO() {
        connection = DatabaseConnection.getConnection();
        projects=new ArrayList<>();
        updateList();
    }

    @Override
    public Optional<? extends Project> get(int projectID) {
        Project project = null;
        try {
            String queryString = "SELECT * FROM `" + Const.TABLE_PROJECT + "` WHERE " + Const.PROJECT_COLUMN_ID + " = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, projectID);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Const.PROJECT_COLUMN_ID);
                String title = resultSet.getString(Const.PROJECT_COLUMN_TITLE);
                String description = resultSet.getString(Const.PROJECT_COLUMN_DESCRIPTION);
                int status = resultSet.getInt(Const.PROJECT_COLUMN_STATUS);
                int category = resultSet.getInt(Const.PROJECT_COLUMN_CATEGORY);
                int priority = resultSet.getInt(Const.PROJECT_COLUMN_PRIORITY);
                Date startDate = resultSet.getDate(Const.PROJECT_COLUMN_START_DATE);
                Date dueDate = resultSet.getDate(Const.PROJECT_COLUMN_DUE_DATE);

                project = new Project(id, title, description, status, category, priority, startDate, dueDate);
                System.out.println(project.getTitle() + " Retrieved");
            } else {
                System.out.println(projectID + " id was not found");
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
        return Optional.ofNullable(project);
    }

    @Override
    public Optional<? extends Project> get(String projectTitle) {
        Project project = null;
        try {
            String queryString = "SELECT * FROM `" + Const.TABLE_PROJECT + "` WHERE " + Const.PROJECT_COLUMN_TITLE + " = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, projectTitle);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Const.PROJECT_COLUMN_ID);
                String title = resultSet.getString(Const.PROJECT_COLUMN_TITLE);
                String description = resultSet.getString(Const.PROJECT_COLUMN_DESCRIPTION);
                int status = resultSet.getInt(Const.PROJECT_COLUMN_STATUS);
                int category = resultSet.getInt(Const.PROJECT_COLUMN_CATEGORY);
                int priority = resultSet.getInt(Const.PROJECT_COLUMN_PRIORITY);
                Date startDate = resultSet.getDate(Const.PROJECT_COLUMN_START_DATE);
                Date dueDate = resultSet.getDate(Const.PROJECT_COLUMN_DUE_DATE);

                project = new Project(id, title, description, status, category, priority, startDate, dueDate);
                System.out.println(project.getTitle() + " Retrieved");
            } else {
                System.out.println(projectTitle + " was not found");
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
        return Optional.ofNullable(project);
    }

    @Override
    public List<? extends Project> getAll() {
        return projects;
    }

    @Override
    public int create(Project project) {
        int result;
        try {
            String queryString = "INSERT INTO `" + Const.TABLE_PROJECT + "` VALUES(0,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getStatus());
            preparedStatement.setInt(4, project.getCategory());
            preparedStatement.setInt(5, project.getPriority());
            preparedStatement.setDate(6, project.getStartDate());
            preparedStatement.setDate(7, project.getDueDate());

//            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();


            // Get last inserted ID and set it to the project object to add to the projects list
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                project.setId(id);
                projects.add(project);
            }
            System.out.println(project.getTitle() + " Inserted");
            result = Const.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            result = Const.FAILED;
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
        return result;
    }

    @Override
    public int update(Project project) {
        int result;
        try {
            String queryString = String.format("UPDATE `%s` SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?", Const.TABLE_PROJECT, Const.PROJECT_COLUMN_TITLE, Const.PROJECT_COLUMN_DESCRIPTION, Const.PROJECT_COLUMN_STATUS, Const.PROJECT_COLUMN_CATEGORY, Const.PROJECT_COLUMN_PRIORITY, Const.PROJECT_COLUMN_START_DATE, Const.PROJECT_COLUMN_DUE_DATE, Const.PROJECT_COLUMN_ID);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, project.getTitle());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setInt(3, project.getStatus());
            preparedStatement.setInt(4, project.getCategory());
            preparedStatement.setInt(5, project.getPriority());
            preparedStatement.setDate(6, project.getStartDate());
            preparedStatement.setDate(7, project.getDueDate());
            preparedStatement.setInt(8, project.getId());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            // Update project in projects list :)
            for (Project p : projects) {
                if (p.getId() == project.getId()) {
                    p.setTitle(project.getTitle());
                    p.setDescription(project.getDescription());
                    p.setStatus(project.getStatus());
                    p.setCategory(project.getCategory());
                    p.setPriority(project.getPriority());
                    p.setStartDate(project.getStartDate());
                    p.setDueDate(project.getDueDate());
                    break;
                }
            }
            System.out.println(project.getTitle() + " Updated");
            result = Const.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            result = Const.FAILED;
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
        return result;
    }

    @Override
    public int delete(Project project) {
        int result;
        if (get(project.getId()).isPresent()) {
            try {
                String queryString = "DELETE FROM `" + Const.TABLE_PROJECT + "` WHERE " + Const.PROJECT_COLUMN_ID + " = ?";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, project.getId());
                preparedStatement.executeUpdate();

                // Delete project if it exist in projects list :)
                for (Project p : projects) {
                    if (p.getId() == project.getId()) {
                        projects.remove(p);
                        break;
                    }
                }
                System.out.println(project.getTitle() + " Deleted");
                result = Const.SUCCESS;
            } catch (SQLException e) {
                e.printStackTrace();
                result = Const.FAILED;
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
            System.out.println(project.getId() + " id is not found (Can't delete)");
            result = Const.NOT_FOUND;
        }
        return result;
    }

    @Override
    public void updateList() {
        projects = new ArrayList<>();
        Project project;
        try {
            String queryString = "SELECT * FROM `" + Const.TABLE_PROJECT + "`";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(Const.PROJECT_COLUMN_ID);
                String title = resultSet.getString(Const.PROJECT_COLUMN_TITLE);
                String description = resultSet.getString(Const.PROJECT_COLUMN_DESCRIPTION);
                int status = resultSet.getInt(Const.PROJECT_COLUMN_STATUS);
                int category = resultSet.getInt(Const.PROJECT_COLUMN_CATEGORY);
                int priority = resultSet.getInt(Const.PROJECT_COLUMN_PRIORITY);
                Date startDate = resultSet.getDate(Const.PROJECT_COLUMN_START_DATE);
                Date dueDate = resultSet.getDate(Const.PROJECT_COLUMN_DUE_DATE);


                project = new Project(id, title, description, status, category, priority, startDate, dueDate);

                projects.add(project);
            }

            System.out.println("List Updated Project");
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

    @Override
    public int getLastInsertedId() {
        return !projects.isEmpty() ? projects.get(projects.size() - 1).getId() : 0;
    }

    public void testPrintAll() {
        for (Project project : projects) {
            System.out.println(project);
        }
    }
}
