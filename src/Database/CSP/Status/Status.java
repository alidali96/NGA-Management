package Database.CSP.Status;

import Const.Const;
import Database.CSP.CSP;

public class Status extends CSP {

    private String table = Const.TABLE_STATUS;

    public Status(int id, String name) {
        super(id, name);
        super.table = this.table;
    }

    public Status(String name, String color) {
        super(name, color);
        super.table = this.table;
    }

    public Status(int id, String name, String color) {
        super(id, name, color);
        super.table = this.table;
    }

    public String toString(){
        return  this.getName();
    }
}
