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

public class PrioritiesController implements Initializable {

    @FXML
    private TableView prioritiesTable;
    @FXML
    private TableColumn<ProjectItem, String> id;
    @FXML
    private TableColumn<ProjectItem, String> name;
    @FXML
    private TableColumn<ProjectItem, String> color;
    @FXML
    private TableColumn<ProjectItem, ProjectItem> edit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell());

        ObservableList<ProjectItem> Priolist = FXCollections.observableArrayList();
        Priolist.add(new ProjectItem("1", "priority", "RED"));
        Priolist.add(new ProjectItem("1", "priority", "RED"));
        Priolist.add(new ProjectItem("1", "priority", "RED"));
        Priolist.add(new ProjectItem("1", "priority", "RED"));

        prioritiesTable.setItems(Priolist);
    }
}
