package controllers;


import Database.CSP.Category.CategoryDAO;
import Database.CSP.Priority.PriorityDAO;
import Database.CSP.Status.StatusDAO;
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
        StatusDAO statusDAO=StatusDAO.getInstance();
        PriorityDAO priorityDAO=PriorityDAO.getInstance();

        projectName.setCellValueFactory(new PropertyValueFactory<>("title"));
        category.setCellValueFactory(e-> new SimpleStringProperty(categoryDAO.getItemById(e.getValue().getCategory()).getName()));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        status.setCellValueFactory(e-> new SimpleStringProperty(statusDAO.getItemById(e.getValue().getStatus()).getName()));
        priority.setCellValueFactory(e-> new SimpleStringProperty(priorityDAO.getItemById(e.getValue().getPriority()).getName()));
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Projects"));
        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Projects"));

        ObservableList<Project> projectModel1 = FXCollections.observableArrayList(projectDAO.getAll());

        table.setItems(projectModel1);
    }
}

