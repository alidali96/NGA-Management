package Tables;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;


abstract class CreateTable {

    private JFXTreeTableView table;
    private JFXTreeTableColumn<Create, String> column1;
    private JFXTreeTableColumn<Create, String> column2;
    private JFXTreeTableColumn<Create, String> column3;

    public CreateTable() {
        column1.setCellValueFactory(param -> param.getValue().getValue().column1);
        column2.setCellValueFactory(param -> param.getValue().getValue().column2);
        column3.setCellValueFactory(param -> param.getValue().getValue().column3);

        table.getColumns().setAll(column1, column2, column3);

        ObservableList<Create> list = FXCollections.observableArrayList();
        list.add(new Create("col1", "col2", "col3"));

        TreeItem<Create> data = new RecursiveTreeItem<>(list, RecursiveTreeObject::getChildren);
        table.setRoot(data);
        table.setShowRoot(false);
    }

    class Create extends RecursiveTreeObject<Create>{
        StringProperty column1;
        StringProperty column2;
        StringProperty column3;
        public Create (String column1, String column2, String column3){
            this.column1 = new SimpleStringProperty(column1);
            this.column2 = new SimpleStringProperty(column2);
            this.column3 = new SimpleStringProperty(column3);
        }
    }
}
