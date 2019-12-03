package controllers;

import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

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
    @FXML
    public static Text categoryTitle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        replaceable.getChildren().set(0, new AddButton(replaceable, "Category"));
        name.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        name.setCellFactory(param -> new TableCell<Category, Category>() {
            @Override
            public void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);
                if (!empty) {
                    setStyle("-fx-background-color: " + category.getColor());
                    setText(category.getName());
                }
            }
        });


        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new EditButton(replaceable, "Category"));
        CategoryDAO categoryDAO=CategoryDAO.getInstance();

        categoriesTable.setItems(FXCollections.observableArrayList((ArrayList<Category>)categoryDAO.getAll()));

    }
}
