package controllers;


import Database.CSP.Category.Category;
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
import javafx.scene.control.TableCell;
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
    TableColumn<Project, Project> category;
    @FXML
    TableColumn<Project, String> startDate;
    @FXML
    TableColumn<Project, String> dueDate;
    @FXML
    TableColumn<Project, Project> status;
    @FXML
    TableColumn<Project, Project> priority;
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
//        category.setCellValueFactory(e-> new SimpleStringProperty(categoryDAO.getItemById(e.getValue().getCategory()).getName()));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
//        status.setCellValueFactory(e-> new SimpleStringProperty(statusDAO.getItemById(e.getValue().getStatus()).getName()));
//        priority.setCellValueFactory(e-> new SimpleStringProperty(priorityDAO.getItemById(e.getValue().getPriority()).getName()));
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Projects"));
        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Projects"));

        category.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        category.setCellFactory(param -> new TableCell<Project, Project>() {
            @Override
            public void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                if (!empty) {
                    setStyle("-fx-text-fill: white; -fx-background-color: " + categoryDAO.getItemById(project.getCategory()).getColor());
                    setText(categoryDAO.getItemById(project.getCategory()).getName());
                }
            }
        });

        status.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        status.setCellFactory(param -> new TableCell<Project, Project>() {
            @Override
            public void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                if (!empty) {
                    setStyle("-fx-text-fill: white; -fx-background-color: " + statusDAO.getItemById(project.getStatus()).getColor());
                    setText(statusDAO.getItemById(project.getStatus()).getName());
                }
            }
        });

        priority.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        priority.setCellFactory(param -> new TableCell<Project, Project>() {
            @Override
            public void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);
                if (!empty) {
                    setStyle("-fx-text-fill: white; -fx-background-color: " + priorityDAO.getItemById(project.getPriority()).getColor());
                    setText(priorityDAO.getItemById(project.getPriority()).getName());
                }
            }
        });

        ObservableList<Project> projectModel1 = FXCollections.observableArrayList(projectDAO.getAll());

        table.setItems(projectModel1);
    }
}

