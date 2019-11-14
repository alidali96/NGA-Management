package Database.Task;

public class Task {

    private int id;
    private String name;
    private String description;
    private int project;
    private int closed;


    public Task(String name, String description, int project, int closed) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.closed = closed;
    }

    public Task(int id, String name, String description, int project, int closed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.project = project;
        this.closed = closed;
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

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return String.format("ID: %d Name: %s Description: %s Project %d Closed: %d", id, name, description, project, closed);
    }
}
