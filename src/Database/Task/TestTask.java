package Database.Task;

import Const.Const;

import java.util.Optional;

public class TestTask {

    /**
     * Include all tests in this class
     */
    public TestTask() {
        TaskDAO taskDAO = new TaskDAO();


        Task task = new Task("task1", 6, 1);
        Task task2 = new Task("task2", 6, 0);
        Task task3 = new Task("task3", 6, 1);
        Task task4 = new Task("task4", 7, 0);
        Task task5 = new Task("task5", 7, 1);

        taskDAO.create(task);
        taskDAO.create(task2);
        taskDAO.create(task3);
        taskDAO.create(task4);
        taskDAO.create(task5);


        Optional<Task> deleteTask = (Optional<Task>) taskDAO.get(3);
        if (deleteTask.isPresent())
            taskDAO.delete(deleteTask.get());

        taskDAO.update(new Task(1, "task111",9 ,1));
        taskDAO.update(new Task(2, "task222",9,0));

        taskDAO.testPrintAll();
    }
}
