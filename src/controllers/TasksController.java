package controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class TasksController implements Initializable {

    @FXML
    private TableView taskTable;
    @FXML
    private TableColumn<ProjectItem, String> id;
    @FXML
    private TableColumn<ProjectItem, String> name;
    @FXML
    private TableColumn<ProjectItem, String> project;
    @FXML
    private TableColumn<ProjectItem, String> startDate;
    @FXML
    private TableColumn<ProjectItem, String> dueDate;
    @FXML
    private TableColumn<ProjectItem, String> priority;
    @FXML
    private TableColumn<ProjectItem, String> status;
    @FXML
    TableColumn<ProjectItem, ProjectItem> edit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        project.setCellValueFactory(new PropertyValueFactory<>("project"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell());

        ObservableList<ProjectItem> Tasklist = FXCollections.observableArrayList();
        Tasklist.add(new ProjectItem("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));
        Tasklist.add(new ProjectItem("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));
        Tasklist.add(new ProjectItem("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));

        taskTable.setItems(Tasklist);
    }
}