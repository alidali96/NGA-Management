package controllers;


import Database.CSP.CSPDAO;
import Database.CSP.Category.TestCategory;
import Database.CSP.Priority.TestPriority;
import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
import Database.DatabaseConnection;
import Database.CSP.Status.TestStatus;
import Database.Project.TestProject;
import Database.Task.TestTask;
import com.jfoenix.controls.JFXButton;
import controllers.ToolBarController;
import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static de.jensd.fx.glyphs.GlyphsDude.createIcon;
import static de.jensd.fx.glyphs.GlyphsDude.createIconLabel;

public class MainController implements Initializable {
    @FXML
    VBox tableContainer;

    @FXML
    JFXButton projectsButton;

    @FXML
    Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeTable("Project");
        // Create Database Connection
//        DatabaseConnection.getInstance();


        // Test Status Table Class
//        TestStatus testStatus =  = new CategoryDAO()new TestStatus();

//         Test Category Table Class
//        TestCategory testCategory = new TestCategory();

//         Test Priority Table Class
//        TestPriority testPriority = new TestPriority();

        // Test Project Table Class
//        TestProject testProject = new TestProject();

        // Test Task Table Class
//        TestTask testTask = new TestTask();


//        Label icon=createIconLabel("Project");
//        projectsButton.setGraphic(GlyphsDude.createIcon(FontAwesomeIcons.BRIEFCASE,"13px"));

    }


    public void switchTable(ActionEvent event) {
        Button button = (Button) event.getSource();

        switch (button.getId()) {
            case "projectsButton":
                changeTable("Project");
                break;
            case "tasksButton":
                changeTable("Task");
                break;
            case "categoriesButton":
                changeTable("Category");
                break;
            case "statusButton":
                changeTable("Status");
                break;
            case "prioritiesButton":
                changeTable("Priority");
                break;
            case "settingsButton":
                changeTable("Settings");
                break;
            default:
                System.out.println("ID not found");
        }
    }

    private void changeTable(String view) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../views/" + view + "View.fxml"));
            tableContainer.getChildren().retainAll();
            tableContainer.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logout(ActionEvent actionEvent) {
        try {
            DatabaseConnection.getInstance().closeConnection();
            Pane pane = FXMLLoader.load(getClass().getResource("../views/DBLoginView.fxml"));
            Scene scene = new Scene(pane);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}