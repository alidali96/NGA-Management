package controllers;

import Forms.ProjectsFormController;
import Forms.StatusFormController;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AddProjectButton extends JFXButton{

    Pane replaceable;
    String form;

    public AddProjectButton(Pane replaceable, String form) {
        this.replaceable = replaceable;
        this.form = form;
        this.setText("[+] Add");
        this.setStyle("-fx-background-color: #0275d8; -fx-text-fill: white;");
        this.setButtonType(ButtonType.RAISED);
        setOnAction(e->{
            try {
                ProjectsFormController.updateForm = false;
                StatusFormController.updateForm = false;
                Pane pane = FXMLLoader.load(getClass().getResource("../Forms/" + form + "FormView.fxml"));
                replaceable.getChildren().retainAll();
                replaceable.getChildren().add(pane);
                this.setStyle("visibility: hidden;");
            } catch (IOException er) {
                er.printStackTrace();
            }
        });
    }
}
