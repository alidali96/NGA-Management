package Database.Project;

import java.sql.Date;
import java.util.Optional;

public class TestProject {

    /**
     * Include all tests in this class
     */
    public TestProject() {
        ProjectDAO projectDAO = ProjectDAO.getInstance();

        Date date = new Date(System.currentTimeMillis());
        Date due = new Date(System.currentTimeMillis());
        due.setTime(System.currentTimeMillis() + 999999999);

//        Project project = new Project("Tower Defense", "DESCRIPTION ABOUT THE GAME", 66, 1, 1, date, due);

//        projectDAO.create(project);

        Optional<Project> deleteProject = (Optional<Project>) projectDAO.get(8);
        if (deleteProject.isPresent())
            projectDAO.delete(deleteProject.get());


//        projectDAO.update(new Project(6, "Tower Defense 2", "NEW DESCRIPTION", 63, 4, 1, date, due));

        projectDAO.testPrintAll();
    }
}
