package controllers;

import Database.CSP.Priority.Priority;
import Database.CSP.Priority.PriorityDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


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
    @FXML
    private HBox topBar;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Region region1 = new Region();
        HBox.setHgrow(region1, javafx.scene.layout.Priority.ALWAYS);

        topBar.getChildren().addAll(region1,new AddButton(replaceable, "Priority"));

        name.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        name.setCellFactory(param -> new TableCell<Priority, Priority>() {
            @Override
            public void updateItem(Priority priority, boolean empty) {
                super.updateItem(priority, empty);
                if (!empty) {
                    setStyle("-fx-background-color: " + priority.getColor());
                    setText(priority.getName());
                }
            }
        });

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new EditButton(replaceable, "Priority"));

        PriorityDAO priorityDAO=PriorityDAO.getInstance();

        prioritiesTable.setItems(FXCollections.observableArrayList((ArrayList<Priority>)priorityDAO.getAll()));

    }
}
