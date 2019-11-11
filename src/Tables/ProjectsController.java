package Tables;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ProjectsController implements Initializable {


    @FXML
    private JFXTreeTableView table;

    @FXML
    private JFXTreeTableColumn<Example, String> id;
    @FXML
    private JFXTreeTableColumn<Example, String> projectName;
    @FXML
    private JFXTreeTableColumn<Example, String> tasks;
    @FXML
    private JFXTreeTableColumn<Example, String> category;
    @FXML
    private JFXTreeTableColumn<Example, String> startDate;
    @FXML
    private JFXTreeTableColumn<Example, String> dueDate;
    @FXML
    private JFXTreeTableColumn<Example, String> status;
    @FXML
    private JFXTreeTableColumn<Example, String> priority;

    private JFXButton addProject;
    @FXML

    private Pane replaceable;

    @FXML
    JFXButton addProjectsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(param -> param.getValue().getValue().id);
        projectName.setCellValueFactory(param -> param.getValue().getValue().projectName);
        tasks.setCellValueFactory(param -> param.getValue().getValue().tasks);
        category.setCellValueFactory(param -> param.getValue().getValue().category);
        startDate.setCellValueFactory(param -> param.getValue().getValue().startDate);
        dueDate.setCellValueFactory(param -> param.getValue().getValue().dueDate);
        status.setCellValueFactory(param -> param.getValue().getValue().status);
        priority.setCellValueFactory(param -> param.getValue().getValue().priority);

        table.getColumns().setAll(id, projectName, tasks, category, startDate, dueDate, status, priority);


        ObservableList<Example> example = FXCollections.observableArrayList();
        example.add(new Example("1", "Project1", "Task1", "Design", "2019", "Acive", "fast", "2020"));
        example.add(new Example("1", "Project1f", "Task4", "Web", "2016", "Acive", "fast", "2020"));
        example.add(new Example("1", "Project1d", "Task3", "Mobile App", "2017", "Acive", "fast", "2020"));
        example.add(new Example("1", "Projectf1", "Task6", "Logo", "2019", "Acive", "fast", "2020"));

        TreeItem<Example> data = new RecursiveTreeItem<Example>(example, RecursiveTreeObject::getChildren);
        table.setRoot(data);
        table.setShowRoot(false);
    }

    public void addProject(ActionEvent actionEvent) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../Forms/ProjectsFormView.fxml"));
            replaceable.getChildren().retainAll();
            replaceable.getChildren().add(pane);
            addProjectsBtn.setStyle("visibility: hidden;");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class Example extends RecursiveTreeObject<Example> {
        StringProperty id;
        StringProperty projectName;
        StringProperty tasks;
        StringProperty category;
        StringProperty startDate;
        StringProperty status;
        StringProperty priority;
        StringProperty dueDate;

        public Example(String id, String projectName, String tasks, String category, String startDate, String status, String priority, String dueDate) {
            this.id = new SimpleStringProperty(id);
            this.projectName = new SimpleStringProperty(projectName);
            this.tasks = new SimpleStringProperty(tasks);
            this.category = new SimpleStringProperty(category);
            this.startDate = new SimpleStringProperty(startDate);
            this.status = new SimpleStringProperty(status);
            this.priority = new SimpleStringProperty(priority);
            this.dueDate = new SimpleStringProperty(dueDate);
        }
    }
}
