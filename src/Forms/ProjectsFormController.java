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
import models.ProjectFormModel;

import java.util.HashMap;
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

    @FXML
    private VBox errorDisplay;

    private String projectNameStr="";
    private String categoryStr="";
    private String priorityStr="";
    private String statusStr="";
    private String startDateStr="";
    private String endDateStr="";
    private HashMap<String,String> formFieldsArray =new HashMap<>();

    public void addNewTask(ActionEvent actionEvent) {
        vBox=new HBox();
        JFXTextField newTask=new JFXTextField();
        JFXButton remove=new JFXButton();
        remove.setText("-");
        remove.setStyle( "-fx-min-width: 10px; " +
                "-fx-background-radius: 5em; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-border-color: #d9534f; " +
                "-fx-text-fill: #903; " +
                "-fx-max-height: 30px;");
        /**
         * Remove the task row,when - clicked
         */
        remove.setOnAction(event -> {
            HBox thisParent= (HBox) remove.getParent();
            TasksHBox.getChildren().remove(thisParent);
        });
        taskOneLabel.setText("Task #1");
        Label label=new Label("Task #"+(TasksHBox.getChildren().size()+1));
        label.setMinHeight(40);
        label.setMinWidth(100);
        newTask.setMinWidth(300);
        newTask.setMinHeight(40);
        vBox.getChildren().addAll(label,newTask,remove);
        if(TasksHBox.getChildren().size()<6){
            TasksHBox.getChildren().add(vBox);
        }

    }


    public void processProjectsForm(ActionEvent actionEvent) {
        LinkedList<String>tasksList=new LinkedList<>();
        LinkedList<String>errors=new LinkedList<>();

        //check each field if the data is entered
        if(projectName.getText().isEmpty() || projectName.getText().length()<10){
            errors.add("Project name should contain at least 10 letters");
            projectName.setStyle("-fx-border-color: red;");
        }else{
            projectName.setStyle("-fx-border-color: none;");
            projectNameStr=projectName.getText();
        }

        for(Node child:TasksHBox.getChildren()){
            HBox box= (HBox) child;
            for(Node achild:box.getChildren()) {
                if (achild instanceof TextField) {
                    if(((TextField) achild).getText().isEmpty() || ((TextField) achild).getText().length()<10){
                        errors.add("Each Task should contain at least 10 letters");
                        achild.setStyle("-fx-border-color: red;");
                    }else{
                        achild.setStyle("-fx-border-color: none;");
                        tasksList.add(((TextField) achild).getText());
                    }
                }
            }
        }
        if(category.getValue()==null){
            errors.add("Select a Category");
            HBox catParent= (HBox) category.getParent();
            catParent.getChildren().add(new Label("*"));
            category.setStyle("-fx-border-color: red;");
        }else{
            category.setStyle("-fx-border-color: none;");
            categoryStr=category.getValue().toString();
        }
        if(priority.getValue()==null){
            errors.add("Select a Priority");
            priority.setStyle("-fx-border-color: red;");
        }else{
            priority.setStyle("-fx-border-color: none;");
            priorityStr=priority.getValue();
        }

        if(status.getValue()==null){
            errors.add("Select a Status");
            status.setStyle("-fx-border-color: red;");
        }else{
            status.setStyle("-fx-border-color: none;");
            statusStr=status.getValue();
        }
        if(startDatePicker.getValue()==null){
            errors.add("Select a Start Date for your project");
            startDatePicker.setStyle("-fx-border-color: red;");
        }else{
            startDatePicker.setStyle("-fx-border-color: none;");
            startDateStr=startDatePicker.getValue().toString();
        }
        if(dueDatePicker.getValue()==null){
            errors.add("Select a Start Date for your project");
            dueDatePicker.setStyle("-fx-border-color: red;");
        }else{
            dueDatePicker.setStyle("-fx-border-color: none;");
            endDateStr=dueDatePicker.getValue().toString();
        }

        //cleaning the Error container each click to remove old cache
        errorDisplay.getChildren().clear();
        if(errors.size()>0){
            for(int i=0;i<errors.size();i++){
                Label errorLabel=new Label(errors.get(i));
                errorDisplay.getChildren().add(errorLabel);
            }
        }else{
            errorDisplay.getChildren().add(new Label("ALL SET"));
            formFieldsArray.put("projectName",projectNameStr);
            formFieldsArray.put("projectName",projectNameStr);
                for(int i=0;i<tasksList.size();i++){
                    formFieldsArray.put("task"+i,tasksList.get(i));
            }
            formFieldsArray.put("category",categoryStr);
            formFieldsArray.put("priority",priorityStr);
            formFieldsArray.put("status",statusStr);
            formFieldsArray.put("startDate",startDateStr);
            formFieldsArray.put("endDate",endDateStr);
        }
        category.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println(oldValue+" "+newValue);
                }
        );

        ProjectFormModel projectFormModel=new ProjectFormModel();
    }

    public HashMap projectFormFields(){
        return formFieldsArray;
    }

}
