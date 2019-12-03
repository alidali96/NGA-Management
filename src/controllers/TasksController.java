package controllers;

import Database.CSP.Status.Status;
import Database.Project.Project;
import Database.Project.ProjectDAO;
import Database.Task.Task;
import Database.Task.TaskDAO;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class TasksController implements Initializable {

    @FXML
    private TableView taskTable;

    @FXML
    private TableColumn<Task, String> name;
    @FXML
    private TableColumn<Task, String> project;
    @FXML
    private TableColumn<Task, Integer> open;
    @FXML
    TableColumn<Task, Task> edit;

    @FXML
    private VBox replaceable;


    ProjectDAO projectDAO=ProjectDAO.getInstance();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Task"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        project.setCellValueFactory(new PropertyValueFactory<>("project"));

        project.setCellValueFactory(e->new SimpleStringProperty(projectDAO.get(e.getValue().getProject()).get().getTitle()));
//        project.setCellValueFactory(e->new SimpleStringProperty(projectDAO.get(e.getValue().getTitle()).get().getTitle()));
//        category.setCellValueFactory(e-> new SimpleStringProperty(categoryDAO.getItemById(e.getValue().getCategory()).getName()));

        open.setCellValueFactory(new PropertyValueFactory<>("open"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Task"));


        TaskDAO taskDAO=TaskDAO.getInstance();
        taskTable.setItems(FXCollections.observableArrayList((ArrayList<Task>)taskDAO.getAll()));

    }
}