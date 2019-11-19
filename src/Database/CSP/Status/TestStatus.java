package Database.CSP.Status;

import Const.Const;
import Database.CSP.CSP;

import java.util.Optional;

public class TestStatus {

    /**
     * Include all tests in this class
     */
    public TestStatus() {
        StatusDAO statusDAO = new StatusDAO();


        Status status = new Status("Late", "Red");
        Status status1 = new Status("Pending", "Red");
        Status status2 = new Status("Working", "Blue");
        Status status3 = new Status("Finished", "Green");

        statusDAO.create(status);
        statusDAO.create(status1);
        statusDAO.create(status2);
        statusDAO.create(status3);

        Optional<Status> deleteStatus = (Optional<Status>) statusDAO.get(55);
        if (deleteStatus.isPresent())
            statusDAO.delete(deleteStatus.get());

        statusDAO.update(new Status(62, "Completed", "Purple"));
        statusDAO.update(new Status(62, "Complete", "Yellow"));

        statusDAO.testPrintAll();
    }


}
