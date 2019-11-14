package controllers;

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

import Database.Project.Project;


import java.net.URL;
import java.util.ResourceBundle;

public class PrioritiesController implements Initializable {

    @FXML
    private TableView prioritiesTable;
    @FXML
    private TableColumn<Project, String> id;
    @FXML
    private TableColumn<Project, String> name;
    @FXML
    private TableColumn<Project, String> color;
    @FXML
    private TableColumn<Database.Project.Project, Project> edit;
    @FXML
    private VBox replaceable;
    @FXML
    Button addProjectsBtn;

    @FXML
    private VBox rightBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rightBox.getChildren().set(0, new AddProjectButton(replaceable, "Status"));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Status"));

        ObservableList<Project> Priolist = FXCollections.observableArrayList();
//        Priolist.add(new Project("1", "priority", "RED"));
//        Priolist.add(new Project("1", "priority", "RED"));
//        Priolist.add(new Project("1", "priority", "RED"));
//        Priolist.add(new Project("1", "priority", "RED"));

        prioritiesTable.setItems(Priolist);
    }
}
