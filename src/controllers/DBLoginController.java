package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DBLoginModel;

import java.io.IOException;

public class DBLoginController {

    DBLoginModel model = DBLoginModel.getInstance();

    @FXML
    JFXTextField dbHost;
    @FXML
    JFXTextField dbName;
    @FXML
    JFXTextField dbUsername;
    @FXML
    JFXPasswordField dbPassword;

    @FXML
    VBox root;


    public void connect(ActionEvent actionEvent) {
        String host = dbHost.getText();
        String name = dbName.getText();
        String username = dbUsername.getText();
        String password = dbPassword.getText();
        if (host.isEmpty() || name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            return;
        }

        boolean connected = model.establishConnection(host, name, username, password);
        if (connected) {
            try {
                Pane pane = FXMLLoader.load(getClass().getResource("/Main/main.fxml"));
                Scene scene = new Scene(pane);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to connect to Database");
            alert.setContentText(model.errorMessage());
            alert.show();
        }
    }
}
