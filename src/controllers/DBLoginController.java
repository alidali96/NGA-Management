package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.DBLoginModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DBLoginController  {

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
        String database = dbName.getText();
        String username = dbUsername.getText();
        String password = dbPassword.getText();
        if (host.isEmpty() || database.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("You must fill all fields!");

            String message = "";
            message += host.isEmpty() ? "Host is missing\n" : "";
            message += database.isEmpty() ? "Database is missing\n" : "";
            message += username.isEmpty() ? "Username is missing\n" : "";
            message += password.isEmpty() ? "Password is missing" : "";

            alert.setContentText(message);
            alert.show();
            return;
        }

        boolean connected = model.establishConnection(host, database, username, password);
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
