package Database;

import java.sql.Statement;

public class Status {

    private int id;
    private String name;
    private String color;

    public Status(String name, String color) {
        this.id = 0;
        this.name = name;
        this.color = color;
    }

    public Status(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public Status setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Status setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Status setColor(String color) {
        this.color = color;
        return this;
    }
}
