package Forms;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ProjectsFormController {
    @FXML
    VBox TasksHBox;
    @FXML
    Label taskOneLabel;
    public void addNewTask(ActionEvent actionEvent) {

        HBox vBox=new HBox();
        JFXTextField newTask=new JFXTextField();
        taskOneLabel.setText("Task #1");
        Label label=new Label("Task #"+(TasksHBox.getChildren().size()+1));
        label.setMinHeight(40);
        label.setMinWidth(100);
        newTask.setMinWidth(300);
        newTask.setMinHeight(40);
        vBox.getChildren().addAll(label,newTask);
        TasksHBox.getChildren().add(vBox);
    }
}
