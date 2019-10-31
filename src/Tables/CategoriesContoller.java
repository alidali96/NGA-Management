package Tables;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriesContoller implements Initializable {

    @FXML
    private JFXTreeTableView categoriesTable;
    @FXML
    private JFXTreeTableColumn<CreateCat, String> id;
    @FXML
    private JFXTreeTableColumn<CreateCat, String> name;
    @FXML
    private JFXTreeTableColumn<CreateCat, String> color;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(param -> param.getValue().getValue().id);
        name.setCellValueFactory(param -> param.getValue().getValue().name);
        color.setCellValueFactory(param -> param.getValue().getValue().color);

        categoriesTable.getColumns().setAll(id, name, color);

        ObservableList<CreateCat> list = FXCollections.observableArrayList();
        list.add(new CreateCat("1", "Ghaith", "RED"));
        list.add(new CreateCat("1", "Ghaith", "RED"));
        list.add(new CreateCat("1", "Ghaith", "RED"));
        list.add(new CreateCat("1", "Ghaith", "RED"));


        TreeItem<CreateCat> data = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        categoriesTable.setRoot(data);
        categoriesTable.setShowRoot(false);
    }

    class CreateCat extends RecursiveTreeObject<CreateCat> {
        SimpleStringProperty id;
        SimpleStringProperty name;
        SimpleStringProperty color;

        public CreateCat(String id, String name, String color) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.color = new SimpleStringProperty(color);
        }
    }
}
