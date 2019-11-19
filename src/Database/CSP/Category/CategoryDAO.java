package Database.CSP.Category;

import Const.Const;
import Database.CSP.CSPDAO;

public class CategoryDAO extends CSPDAO {

    private static CategoryDAO categoryDAO = new CategoryDAO();

    public static CategoryDAO getInstance() {
        return categoryDAO;
    }

    private CategoryDAO() {
        super(Const.TABLE_CATEGORY);
    }
}
