package controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
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


        ObservableList<ProjectItem> Catlist = FXCollections.observableArrayList();
        Catlist.add(new ProjectItem("1", "Ghaith", "RED"));
        Catlist.add(new ProjectItem("1", "Ghaith", "RED"));
        Catlist.add(new ProjectItem("1", "Ghaith", "RED"));
        Catlist.add(new ProjectItem("1", "Ghaith", "RED"));

        categoriesTable.setItems(Catlist);


    }
}
