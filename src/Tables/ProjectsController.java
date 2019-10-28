package Tables;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class ProjectsController{

   // public class ProjectsController implements Initializable {


//    @FXML
//    private JFXTreeTableView table;
//
//    @FXML
//    private JFXTreeTableColumn<Example, String> id;
//
//    @FXML
//    private JFXTreeTableColumn<Example, String> id1;
//
//    @FXML
//    private JFXTreeTableColumn<Example, String> id2;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        id.setCellValueFactory(param -> param.getValue().getValue().fmane);
//        id1.setCellValueFactory(param -> param.getValue().getValue().lName);
//        id2.setCellValueFactory(param -> param.getValue().getValue().age);
//
//        table.getColumns().setAll(id, id1, id2);
//
//
//        ObservableList<Example> example = FXCollections.observableArrayList();
//        example.add(new Example("Ghaith", "Darwish", "25"));
//        example.add(new Example("Ghaith", "Darwish", "25"));
//        example.add(new Example("Ghaith", "Darwish", "25"));
//        example.add(new Example("Ghaith", "Darwish", "25"));
//
//
//        TreeItem<Example> data = new RecursiveTreeItem<Example>(example, RecursiveTreeObject::getChildren);
//        table.setRoot(data);
//        table.setShowRoot(false);
//    }
//
//
//    class Example extends RecursiveTreeObject<Example>{
//        StringProperty fmane;
//        StringProperty lName;
//        StringProperty age;
//        public Example (String fname, String lName, String age){
//            this.fmane = new SimpleStringProperty(fname);
//            this.lName = new SimpleStringProperty(lName);
//            this.age = new SimpleStringProperty(age);
//        }
//    }
}
