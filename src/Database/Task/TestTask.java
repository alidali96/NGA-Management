package Database.Task;

import Const.Const;

import java.util.Optional;

public class TestTask {

    /**
     * Include all tests in this class
     */
    public TestTask() {
        TaskDAO taskDAO = new TaskDAO();


        Task task = new Task("task1", "description1", 3, 1);
        Task task2 = new Task("task2", "description2", 3, 0);
        Task task3 = new Task("task3", "description3", 3, 1);
        Task task4 = new Task("task4", "description4", 4, 0);
        Task task5 = new Task("task5", "description5", 4, 1);

        taskDAO.create(task);
        taskDAO.create(task2);
        taskDAO.create(task3);
        taskDAO.create(task4);
        taskDAO.create(task5);


        Optional<Task> deleteTask = (Optional<Task>) taskDAO.get(3);
        if (deleteTask.isPresent())
            taskDAO.delete(deleteTask.get());

        taskDAO.update(new Task(1, "task111", "description111",2 ,1));
        taskDAO.update(new Task(2, "task222", "description222",2,0));

        taskDAO.testPrintAll();
    }
}
