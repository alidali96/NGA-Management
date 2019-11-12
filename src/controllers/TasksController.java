package controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksController implements Initializable {

    @FXML
    private JFXTreeTableView taskTable;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> id;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> name;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> project;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> startDate;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> dueDate;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> priority;
    @FXML
    private JFXTreeTableColumn<CreateTask, String> status;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(param -> param.getValue().getValue().id);
        name.setCellValueFactory(param -> param.getValue().getValue().name);
        project.setCellValueFactory(param -> param.getValue().getValue().project);
        startDate.setCellValueFactory(param -> param.getValue().getValue().startDate);
        dueDate.setCellValueFactory(param -> param.getValue().getValue().dueDate);
        priority.setCellValueFactory(param -> param.getValue().getValue().priority);
        status.setCellValueFactory(param -> param.getValue().getValue().status);

        taskTable.getColumns().setAll(id, name, project, startDate, dueDate, priority);

        ObservableList<CreateTask> list = FXCollections.observableArrayList();
        list.add(new CreateTask("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));
        list.add(new CreateTask("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));
        list.add(new CreateTask("1", "Ghaith", "RED", "cc", "cc", "cc", "status"));



        TreeItem<CreateTask> data = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        taskTable.setRoot(data);
        taskTable.setShowRoot(false);
    }

    class CreateTask extends RecursiveTreeObject<CreateTask> {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty project;
        SimpleStringProperty startDate;
        SimpleStringProperty dueDate;
        SimpleStringProperty priority;
        SimpleStringProperty status;

        public CreateTask(String id, String name, String project, String startDate, String dueDate, String priority, String status) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.project = new SimpleStringProperty(project);
            this.startDate = new SimpleStringProperty(startDate);
            this.dueDate = new SimpleStringProperty(dueDate);
            this.priority = new SimpleStringProperty(priority);
            this.status = new SimpleStringProperty(status);
        }
    }
}

