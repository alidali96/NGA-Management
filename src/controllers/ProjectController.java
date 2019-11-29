package controllers;


import Database.CSP.Category.CategoryDAO;
import Database.DatabaseConnection;
import Database.Project.ProjectDAO;
import Database.Task.TaskDAO;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import Database.Project.Project;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {

    @FXML
    TableView table;

    @FXML
    TableColumn<Project, String> id;
    @FXML
    TableColumn<Project, String> projectName;
    @FXML
    TableColumn<Project, String> category;
    @FXML
    TableColumn<Project, String> startDate;
    @FXML
    TableColumn<Project, String> dueDate;
    @FXML
    TableColumn<Project, String> status;
    @FXML
    TableColumn<Project, String> priority;
    @FXML
    TableColumn<Project, Project> edit;

    @FXML
    private VBox replaceable;

    @FXML
    JFXButton addProjectsBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ProjectDAO projectDAO = ProjectDAO.getInstance();
        CategoryDAO categoryDAO = CategoryDAO.getInstance();


//        DatabaseConnection.getInstance();
        projectName.setCellValueFactory(new PropertyValueFactory<>("title"));
        category.setCellValueFactory(e-> new SimpleStringProperty(categoryDAO.getItemById(e.getValue().getCategory()).getName()));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Projects"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Projects"));


        Date date = new Date(System.currentTimeMillis());
        Date due = new Date(System.currentTimeMillis());
        due.setTime(System.currentTimeMillis() + 999999999);

        System.out.println(categoryDAO.getItemById(1));

        ObservableList<Project> projectModel1 = FXCollections.observableArrayList(projectDAO.getAll());


//        for(int i=0;i<projectDAO.getAll().size();i++){
//            int catId=projectDAO.getAll().get(i).getCategory();
//            String catName=categoryDAO.getItemById(catId).getName();
//            projectModel1.add(
//                    new Project(
//                            projectDAO.getAll().get(i).getTitle(),
//                            projectDAO.getAll().get(i).getDescription(),
//                            projectDAO.getAll().get(i).getStatus(),
//                            projectDAO.getAll().get(i).getCategory(),
//                            projectDAO.getAll().get(i).getPriority(),
//                            projectDAO.getAll().get(i).getStartDate(),
//                            projectDAO.getAll().get(i).getDueDate()
//                    )
//            );
//        }

        table.setItems(projectModel1);
    }
}

