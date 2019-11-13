package controllers;
public class ProjectItem {

    private String id;
    private String projectName;
    private String category;
    private String startDate;
    private String status;
    private String priority;
    private String dueDate;

    public ProjectItem(String id, String projectName, String category, String startDate, String status, String priority, String dueDate) {
        this.id = id;
        this.projectName = projectName;
        this.category = category;
        this.startDate = startDate;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}