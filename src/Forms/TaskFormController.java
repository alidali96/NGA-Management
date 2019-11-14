package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskFormController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Button submitButton;

    public static boolean updateForm = false;


    public void processForm(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (updateForm) {
            title.setText("Edit Task");
            submitButton.setText("Submit");
        }
    }
}
