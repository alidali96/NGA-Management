package Forms;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.LinkedList;


public class ProjectsFormController {
    @FXML
    VBox TasksHBox;
    @FXML
    Label taskOneLabel;
    HBox vBox;
    private Object button;
    @FXML
    private JFXTextField projectName;
    @FXML
    private JFXComboBox category;
    @FXML
    private JFXComboBox<String> priority;
    @FXML
    private JFXComboBox<String> status;
    @FXML
    private JFXDatePicker startDatePicker;
    @FXML
    private JFXDatePicker dueDatePicker;

    String projectNameStr="";
    String categoryStr="";
    String priorityStr="";
    String statusStr="";
    String startDateStr="";
    String endDateStr="";

    public void addNewTask(ActionEvent actionEvent) {
        vBox=new HBox();
        JFXTextField newTask=new JFXTextField();
        JFXButton remove=new JFXButton();
        remove.setText("-");
        remove.setStyle( "-fx-min-width: 10px; " +
                "-fx-background-radius: 5em; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
//                "-fx-background-color: #903; " +
                "-fx-border-color: #d9534f; " +
                "-fx-text-fill: #903; " +
                "-fx-max-height: 30px;");
        /**
         * Remove the task row,when - clicked
         */
        remove.setOnAction(event -> {
            HBox parent= (HBox) remove.getParent();
            TasksHBox.getChildren().remove(parent);
        });
        taskOneLabel.setText("Task #1");
        Label label=new Label("Task #"+(TasksHBox.getChildren().size()+1));
        label.setMinHeight(40);
        label.setMinWidth(100);
        newTask.setMinWidth(300);
        newTask.setMinHeight(40);
        vBox.getChildren().addAll(label,newTask,remove);
        TasksHBox.getChildren().add(vBox);


    }


    public void processProjectsForm(ActionEvent actionEvent) {
        LinkedList<String>tasksList=new LinkedList<>();
        for(Node child:TasksHBox.getChildren()){
            HBox box= (HBox) child;
            for(Node achild:box.getChildren()) {
                if (achild instanceof TextField) {
                    tasksList.add(((TextField) achild).getText());
                }
            }
        }


         projectNameStr=projectName.getText();
        System.out.println("Tasks----");
        for(int i=0;i<tasksList.size();i++){
            System.out.println(tasksList.get(i));
        }
         categoryStr=category.getValue().toString();
         priorityStr=priority.getValue();
         statusStr=status.getValue();
         startDateStr=startDatePicker.getValue().toString();
         endDateStr=dueDatePicker.getValue().toString();

        category.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("hiii");
                    System.out.println(oldValue+" "+newValue);
                }
        );


        System.out.println(projectName.getText());
        System.out.println("category:"+categoryStr);
        System.out.println("priority:"+priorityStr);
        System.out.println("status:"+statusStr);
        System.out.println("startDate:"+startDateStr);
        System.out.println("dueDate:"+endDateStr);
    }

}
