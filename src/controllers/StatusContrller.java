package controllers;

import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class StatusContrller implements Initializable {

    @FXML
    private TableView statusTable;
    @FXML
    private TableColumn<Status, Status> name;
    @FXML
    private TableColumn<Status, Status> edit;
    @FXML
    private VBox replaceable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        replaceable.getChildren().set(0, new AddButton(replaceable, "Status"));

        name.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        name.setCellFactory(param -> new TableCell<Status, Status>() {
            @Override
            public void updateItem(Status status, boolean empty) {
                super.updateItem(status, empty);
                if (!empty) {
                    setStyle("-fx-background-color: " + status.getColor());
                    setText(status.getName());
                }
            }
        });

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new EditButton(replaceable, "Status"));

        StatusDAO statusDAO = StatusDAO.getInstance();

        statusTable.setItems(FXCollections.observableArrayList((ArrayList<Status>) statusDAO.getAll()));

    }
}
