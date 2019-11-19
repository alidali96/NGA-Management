package Database.CSP.Priority;

import Const.Const;
import Database.CSP.Category.TestCategory;

import java.util.Optional;

public class TestPriority {

    public TestPriority() {
        PriorityDAO priorityDAO = new PriorityDAO();


        Priority priority = new Priority("High", "Red");
        Priority priority1 = new Priority("Low", "Yellow");
        Priority priority2 = new Priority("Medium", "Blue");
        Priority priority3 = new Priority("Important", "Green");

        priorityDAO.create(priority);
        priorityDAO.create(priority1);
        priorityDAO.create(priority2);
        priorityDAO.create(priority3);

        Optional<Priority> deletePriority = (Optional<Priority>) priorityDAO.get(55);
        if (deletePriority.isPresent())
            priorityDAO.delete(deletePriority.get());

        priorityDAO.update(new Priority(62, "Ignored", "Grey"));
        priorityDAO.update(new Priority(62, "Forget", "Red"));

        priorityDAO.testPrintAll();
    }
}
