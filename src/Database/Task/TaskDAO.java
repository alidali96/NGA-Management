package Database.Task;

import Const.Const;
import Database.DAO;
import Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDAO implements DAO<Task> {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private static List<Task> tasks;

    private static TaskDAO taskDAO;

    public static TaskDAO getInstance() {
        if(taskDAO==null){
            taskDAO = new TaskDAO();
        }
        return taskDAO;
    }

    private TaskDAO() {
        connection = DatabaseConnection.getConnection();
        updateList();
    }

    @Override
    public Optional<? extends Task> get(int taskID) {
        Task task = null;
        try {
            String queryString = "SELECT * FROM `" + Const.TABLE_TASKS + "` WHERE " + Const.TASK_COLUMN_ID + " = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setInt(1, taskID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(Const.TASK_COLUMN_ID);
                String name = resultSet.getString(Const.TASK_COLUMN_NAME);
                int project = resultSet.getInt(Const.TASK_COLUMN_PROJECT);
                byte open = resultSet.getByte(Const.TASK_COLUMN_OPEN);

                task = new Task(id, name, project, open);
                System.out.println(task.getName() + " Retrieved");
            } else {
                System.out.println(taskID + " id was not found");
            }
        } catch (
                SQLException e) {
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
        return Optional.ofNullable(task);
    }

    @Override
    public Optional<? extends Task> get(String taskName) {
        Task task = null;
        try {
            String queryString = "SELECT * FROM `" + Const.TABLE_TASKS + "` WHERE " + Const.TASK_COLUMN_NAME + " = ? LIMIT 1";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, taskName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Const.TASK_COLUMN_ID);
                String name = resultSet.getString(Const.TASK_COLUMN_NAME);
                int project = resultSet.getInt(Const.TASK_COLUMN_PROJECT);
                byte open = resultSet.getByte(Const.TASK_COLUMN_OPEN);

                task = new Task(id, name, project, open);
                System.out.println(task.getName() + " Retrieved");
            } else {
                System.out.println(taskName + " was not found");
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
        return Optional.ofNullable(task);
    }

    @Override
    public List<? extends Task> getAll() {
        return tasks;
    }

    @Override
    public int create(Task task) {
        int result;
        try {
            String queryString = "INSERT INTO `" + Const.TABLE_TASKS + "` VALUES(0,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getProject());
            preparedStatement.setByte(3, task.getOpen());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            // Get last inserted ID and set it to the project object to add to the projects list
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                task.setId(id);
                tasks.add(task);
            }
            System.out.println(task.getName() + " Inserted");
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
    public int update(Task task) {
        int result;
        try {
            String queryString = String.format("UPDATE `%s` SET %s=?, %s=?, %s=? WHERE %s=?", Const.TABLE_TASKS, Const.TASK_COLUMN_NAME, Const.TASK_COLUMN_PROJECT, Const.TASK_COLUMN_OPEN, Const.TASK_COLUMN_ID);
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getProject());
            preparedStatement.setByte(3, task.getOpen());
            preparedStatement.setInt(4, task.getId());
            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();

            // Update project in projects list :)
            for (Task t : tasks) {
                if (t.getId() == task.getId()) {
                    t.setName(task.getName());
                    t.setProject(task.getProject());
                    t.setOpen(task.getOpen());
                    break;
                }
            }
            System.out.println(task.getName() + " Updated");
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
    public int delete(Task task) {
        int result;
        if (get(task.getId()).isPresent()) {
            try {
                String queryString = "DELETE FROM `" + Const.TABLE_STATUS + "` WHERE " + Const.TASK_COLUMN_ID + " = ?";
                preparedStatement = connection.prepareStatement(queryString);
                preparedStatement.setInt(1, task.getId());
                preparedStatement.executeUpdate();

                // Delete project if it exist in projects list :)
                for (Task t : tasks) {
                    if (t.getId() == task.getId()) {
                        tasks.remove(t);
                        break;
                    }
                }
                System.out.println(task.getName() + " Deleted");
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
            System.out.println(task.getId() + " id is not found (Can't delete)");
            result = Const.NOT_FOUND;
        }
        return result;
    }

    @Override
    public void updateList() {
        tasks = new ArrayList<>();
        Task task = null;
        try {
            String queryString = "SELECT * FROM `" + Const.TABLE_TASKS + "`";
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(Const.TASK_COLUMN_ID);
                String name = resultSet.getString(Const.TASK_COLUMN_NAME);
                int project = resultSet.getInt(Const.TASK_COLUMN_PROJECT);
                byte open = resultSet.getByte(Const.TASK_COLUMN_OPEN);

                task = new Task(id, name, project, open);
                tasks.add(task);
            }

            System.out.println("List Updated Task");
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
        return !tasks.isEmpty() ? tasks.get(tasks.size() - 1).getId() : 0;
    }


    public ArrayList<Task> getTasksByPojectID(int projectID) {
        ArrayList<Task> list = new ArrayList<>();
        for(Task task: tasks) {
            if (task.getProject() == projectID)
                list.add(task);
        }
        return list;
    }
    public void testPrintAll() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
