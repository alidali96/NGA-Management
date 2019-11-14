package Database.Task;

public class Task {

    private int id;
    private String name;
    private String description;
    private int project;
    private int open;


    public Task(String name, String description, int project, int open) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.open = open;
    }

    public Task(int id, String name, String description, int project, int open) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.project = project;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return String.format("ID: %d Name: %s Description: %s Project %d Open: %d", id, name, description, project, open);
    }
}
