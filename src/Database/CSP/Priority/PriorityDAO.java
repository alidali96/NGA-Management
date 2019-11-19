package Database.CSP.Priority;

import Const.Const;
import Database.CSP.CSPDAO;
import Database.Project.ProjectDAO;

public class PriorityDAO extends CSPDAO {

    private static PriorityDAO projectDAO = new PriorityDAO();

    public static PriorityDAO getInstance() {
        return projectDAO;
    }

    private PriorityDAO() {
        super(Const.TABLE_PRIORITY);
    }
}
