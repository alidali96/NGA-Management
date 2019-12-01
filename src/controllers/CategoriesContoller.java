package controllers;

import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
import Database.CSP.Status.Status;
import Database.Project.Project;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategoriesContoller implements Initializable {

    @FXML
    private TableView categoriesTable;
    @FXML
    private TableColumn<Category, Category> name;
    @FXML
    private TableColumn<Category, Category> edit;

    @FXML
    private VBox replaceable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Categories"));
        name.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        name.setCellFactory(param -> new TableCell<Category, Category>() {
            @Override
            public void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (!empty) {
                    setStyle("-fx-text-fill: white; -fx-background-color: " + category.getColor());
                    setText(category.getName());
                }
            }
        });

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Categories"));
        CategoryDAO categoryDAO=CategoryDAO.getInstance();

        categoriesTable.setItems(FXCollections.observableArrayList((ArrayList<Category>)categoryDAO.getAll()));

    }
}
