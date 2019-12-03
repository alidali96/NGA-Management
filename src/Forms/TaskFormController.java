package Forms;

import Database.Project.Project;
import Database.Project.ProjectDAO;
import Database.Task.Task;
import Database.Task.TaskDAO;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaskFormController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Button submitButton;
    @FXML
    private JFXToggleButton closeTask;

    @FXML
    JFXTextField name;
    @FXML
    JFXTextField project;
    @FXML
    VBox errorDisplay;



    public static Task editingTask;

    public static boolean updateForm = false;

    TaskDAO taskDAO=TaskDAO.getInstance();
    ProjectDAO projectDAO=ProjectDAO.getInstance();
    Project thisTasksProject=projectDAO.get(editingTask.getProject()).get();

    public void processForm(ActionEvent actionEvent) {
        LinkedList<String> errors = new LinkedList<>();
        if (name.getText().isEmpty() || name.getText().length() < 10) {
            errors.add("Task name should contain at least 10 letters");
            name.setStyle("-fx-border-color: red;");
        } else {
            name.setStyle("-fx-border-color: none;");
        }

        errorDisplay.getChildren().clear();
        if (errors.size() > 0) {
            for (int i = 0; i < errors.size(); i++) {
                Label errorLabel = new Label(errors.get(i));
                errorDisplay.getChildren().add(errorLabel);
            }
        } else {
            errorDisplay.getChildren().add(new Label("ALL SET"));
            editingTask.setName(name.getText());
            byte taskOpen=1;
            if(closeTask.isSelected()){
                taskOpen=0;
            }
            editingTask.setOpen(taskOpen);
            taskDAO.update(editingTask);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeTask.setStyle("visibility: hidden;");
        project.setText(thisTasksProject.getTitle());
        name.setText(editingTask.getName());
        if (updateForm) {
            title.setText("Edit Task");
            submitButton.setText("Submit");
            closeTask.setStyle("visibility: visible;");
        }
    }

    public void on(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning!");
        alert.setContentText("Are you sure you want to close this task?");
        if (closeTask.isSelected()) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                title.setText("Task: "+name.getText()+" is Closed!");
                // database query should go here!
            } else {
                closeTask.setSelected(false);
            }
        }else{
            title.setText("Task: "+name.getText());
        }
    }
}
