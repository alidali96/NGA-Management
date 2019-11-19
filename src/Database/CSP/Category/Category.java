package Database.CSP.Category;

import Database.CSP.CSP;

public class Category extends CSP {
    public Category(String name, String color) {
        super(name, color);
    }


    public Category(int id, String name, String color) {
        super(id, name, color);
    }

    public String toString(){
        return this.getName();
    }
}
