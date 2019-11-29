package Database.Project;

import java.sql.Date;

public class Project {

    private int id;
    private String title;
    private String description;
    private int status;
    private int category;
    private int priority;
    private Date startDate;
    private Date dueDate;

    public Project(String title, String description, int status, int category, int priority, Date startDate, Date dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public Project(int id, String title, String description, int status, int category, int priority, Date startDate, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public Project setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Project setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Project setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Project setStatus(int status) {
        this.status = status;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public Project setCategory(int category) {
        this.category = category;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public Project setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Project setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Project setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + " Description: " + getDescription() + " Priority: " + getPriority() + " Start Date: " + getStartDate() + " Due Date: " + getDueDate();
    }
}
