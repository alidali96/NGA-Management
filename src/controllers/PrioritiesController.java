package controllers;

import Database.CSP.Category.Category;
import Database.CSP.Priority.Priority;
import Database.CSP.Priority.PriorityDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import Database.Project.Project;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrioritiesController implements Initializable {

    @FXML
    private TableView prioritiesTable;

    @FXML
    private TableColumn<Priority, Priority> name;
    @FXML
    private TableColumn<Priority, Priority> edit;
    @FXML
    private VBox replaceable;
    @FXML
    Button addProjectsBtn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Priorities"));

        name.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        name.setCellFactory(param -> new TableCell<Priority, Priority>() {
            @Override
            public void updateItem(Priority priority, boolean empty) {
                super.updateItem(priority, empty);
                if (!empty) {
                    setStyle("-fx-text-fill: white; -fx-background-color: " + priority.getColor());
                    setText(priority.getName());
                }
            }
        });

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Priorities"));

        PriorityDAO priorityDAO=PriorityDAO.getInstance();

        prioritiesTable.setItems(FXCollections.observableArrayList((ArrayList<Priority>)priorityDAO.getAll()));

    }
}
