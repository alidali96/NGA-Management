package controllers;

import Database.Task.Task;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TableColumn<Task, String> name;
    @FXML
    private TableColumn<Task, String> description;
    @FXML
    private TableColumn<Task, Integer> project;
    @FXML
    private TableColumn<Task, Integer> open;
    @FXML
    TableColumn<Task, Task> edit;

    @FXML
    private VBox replaceable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Task"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        project.setCellValueFactory(new PropertyValueFactory<>("project"));
        open.setCellValueFactory(new PropertyValueFactory<>("open"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Task"));

        ObservableList<Task> tasklist = FXCollections.observableArrayList();
        tasklist.add(new Task("name",  6, 0));
        tasklist.add(new Task("name",  6, 0));
        tasklist.add(new Task("name", 6, 0));
        tasklist.add(new Task("name",  6, 0));
        tasklist.add(new Task("name", 6, 0));
        tasklist.add(new Task("name",  6, 0));


        taskTable.setItems(tasklist);
    }
}