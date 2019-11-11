package Database.Project;

import Const.Const;

import java.sql.Date;
import java.util.Optional;

public class TestProject {

    /**
     * Include all tests in this class
     */
    public TestProject() {
        ProjectDAO projectDAO = new ProjectDAO();

        Date date = new Date(System.currentTimeMillis());
        Date due = new Date(System.currentTimeMillis());
        due.setTime(System.currentTimeMillis() + 999999999);

        Project project = new Project("Tower Defense" , "DESCRIPTION ABOUT THE GAME", "tasks", 63, 4, 1, date, due);

        projectDAO.create(project);

//        Optional<Project> deleteStatus = projectDAO.get(1);
//        if (deleteStatus.isPresent())
//            statusDAO.delete(deleteStatus.get());

//        statusDAO.update(new Status(62, "Completed", "Purple"));
//        statusDAO.update(new Status(62, "Complete", "Yellow"));

        projectDAO.testPrintAll();
    }
}
