package Database;

import java.util.Optional;

public class TestStatus {

    /**
     * Include all tests in this class
     */
    public TestStatus() {
        StatusDAO statusDAO = new StatusDAO();

        statusDAO.testPrintAll();

//        Status status = new Status(1, "Late", "Red");
//        Status status2 = new Status(2, "Working", "Blue");
//        Status status3 = new Status(3, "Finished", "Green");

        Status status = new Status("Late", "Red");
        Status status2 = new Status("Working", "Blue");
        Status status3 = new Status("Finished", "Green");

        statusDAO.add(status);
        statusDAO.add(status2);
        statusDAO.add(status3);

        statusDAO.testPrintAll();

        Optional<Status> deleteStatus = statusDAO.get(5);
        Status delete = deleteStatus.get();
        
        statusDAO.delete(delete);

        statusDAO.testPrintAll();
    }


}
