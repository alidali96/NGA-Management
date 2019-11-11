package Database.CSP;

public abstract class CSP  {

    protected String table;

    private int id = 0;
    private String name;
    private String color;

    public CSP(String name, String color) {
        this.name = name.trim();
        this.color = color.trim();
    }

    public CSP(int id, String name, String color) {
        this.id = id;
        this.name = name.trim();
        this.color = color.trim();
    }

    public int getId() {
        return id;
    }

    public CSP setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CSP setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public CSP setColor(String color) {
        this.color = color;
        return this;
    }

    public String getTable() {
        return table;
    }

//    public void setTable(String table) {
//        this.table = table;
//    }


    @Override
    public String toString() {
        return String.format("ID: %d - Name: %s - Color: %s", getId(), getName(), getColor());
    }
}
