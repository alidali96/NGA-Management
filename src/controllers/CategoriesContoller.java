package controllers;

import Database.Project.Project;
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

public class CategoriesContoller implements Initializable {

    @FXML
    private TableView categoriesTable;
    @FXML
    private TableColumn<Project, String> id;
    @FXML
    private TableColumn<Project, String> name;
    @FXML
    private TableColumn<Project, String> color;
    @FXML
    private TableColumn<Project, Project> edit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
//        edit.setCellFactory(param -> new ButtonCell());


        ObservableList<Project> Catlist = FXCollections.observableArrayList();
//        Catlist.add(new Project("1", "Ghaith", "RED"));
//        Catlist.add(new Project("1", "Ghaith", "RED"));
//        Catlist.add(new Project("1", "Ghaith", "RED"));
//        Catlist.add(new Project("1", "Ghaith", "RED"));

        categoriesTable.setItems(Catlist);


    }
}
