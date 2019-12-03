package controllers;

import Database.CSP.Priority.Priority;
import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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

        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Status","Status"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        Random r=new Random();
        if(r.nextInt()%2==0){
            color.setStyle("-fx-background-color:orange;");
        }

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Status"));

        StatusDAO statusDAO=StatusDAO.getInstance();

        statusTable.setItems(FXCollections.observableArrayList((ArrayList<Status>)statusDAO.getAll()));

    }
}
