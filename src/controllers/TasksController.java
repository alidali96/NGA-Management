package controllers;

import Database.Project.Project;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class TasksController implements Initializable {

    @FXML
    private TableView taskTable;
    @FXML
    private TableColumn<Project, String> id;
    @FXML
    private TableColumn<Project, String> name;
    @FXML
    private TableColumn<Project, String> project;
    @FXML
    private TableColumn<Project, String> startDate;
    @FXML
    private TableColumn<Project, String> dueDate;
    @FXML
    private TableColumn<Project, String> priority;
    @FXML
    private TableColumn<Project, String> status;
    @FXML
    TableColumn<Project, Project> edit;

    @FXML
    private VBox replaceable;
    @FXML
    private Button addProjectsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Projects"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        project.setCellValueFactory(new PropertyValueFactory<>("project"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Projects"));

        ObservableList<Project> Tasklist = FXCollections.observableArrayList();
//        Tasklist.add(new Project("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));
//        Tasklist.add(new Project("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));
//        Tasklist.add(new Project("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));

        taskTable.setItems(Tasklist);
    }
}