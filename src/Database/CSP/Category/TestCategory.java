package Database.CSP.Category;

import Const.Const;

import java.util.Optional;

public class TestCategory {

    public TestCategory() {
        CategoryDAO categoryDAO = new CategoryDAO();


        Category category = new Category("Web", "Red");
        Category category1 = new Category("Mobile", "Yellow");
        Category category2 = new Category("Desktop", "Blue");
        Category category3 = new Category("Game", "Green");

        categoryDAO.create(category);
        categoryDAO.create(category1);
        categoryDAO.create(category2);
        categoryDAO.create(category3);

        Optional<Category> deleteCat = (Optional<Category>) categoryDAO.get(2);
        if (deleteCat.isPresent())
            categoryDAO.delete(deleteCat.get());

        categoryDAO.update(new Category(62, "Completed", "Purple"));
        categoryDAO.update(new Category(62, "Complete", "Yellow"));

        categoryDAO.testPrintAll();
    }

}
