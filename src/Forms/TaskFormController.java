package Forms;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaskFormController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Button submitButton;
    @FXML
    private JFXToggleButton closeTask;

    public static boolean updateForm = false;


    public void processForm(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeTask.setStyle("visibility: hidden;");
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
                title.setText("Closed!"); // test
                // database query should go here!
            } else {
                closeTask.setSelected(false);
            }
        }
    }
}
