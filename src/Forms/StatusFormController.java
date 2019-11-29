package Forms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StatusFormController implements Initializable {

    @FXML
    Button submitButton;
    @FXML
    Label title;

    public static boolean updateForm = false;


    public void processForm(ActionEvent actionEvent) {


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (updateForm) {
            title.setText("Edit Status");
            submitButton.setText("Submit");
        }
    }
}
