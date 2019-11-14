package controllers;

import Database.CSP.Status.Status;
import Database.Project.Project;
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

public class StatusContrller implements Initializable {

    @FXML
    private TableView statusTable;
    @FXML
    private TableColumn<Status, String> id;
    @FXML
    private TableColumn<Status, String> name;
    @FXML
    private TableColumn<Status, String> color;
    @FXML
    private TableColumn<Status, Status> edit;
    @FXML
    private VBox replaceable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Status"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Status"));

        ObservableList<Status> statuslist = FXCollections.observableArrayList();
        statuslist.add(new Status("name", "color"));
        statuslist.add(new Status("name", "color"));
        statuslist.add(new Status("name", "color"));
        statuslist.add(new Status("name", "color"));
        statuslist.add(new Status("name", "color"));
        statuslist.add(new Status("name", "color"));


        statusTable.setItems(statuslist);
    }
}
