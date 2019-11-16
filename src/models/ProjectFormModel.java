package models;

import Database.Project.Project;
import Database.Project.ProjectDAO;
import Forms.ProjectsFormController;

public class ProjectFormModel {

    ProjectDAO projectDAO;
    public ProjectFormModel(){
        this.projectDAO = new ProjectDAO();
        System.out.println("print from Model");
    }
    public void addProject(Project project){
        ProjectsFormController projectsFormController=new ProjectsFormController();
        System.out.println(projectsFormController.projectFormFields());
    }
}
