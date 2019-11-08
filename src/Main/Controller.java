package Main;

import Database.CSP.CSPDAO;
import Database.CSP.Category.TestCategory;
import Database.CSP.Priority.TestPriority;
import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
import Database.DatabaseConnection;
import Database.CSP.Status.TestStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    AnchorPane tableContainer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Create Database Connection
        DatabaseConnection.getInstance();

        // Test Status Table Class
//        TestStatus testStatus = new TestStatus();

        // Test Category Table Class
//        TestCategory testCategory = new TestCategory();

        // Test Priority Table Class
        TestPriority testPriority = new TestPriority();


    }



    public void switchTable(ActionEvent event)  {
        Button button = (Button)event.getSource();

        switch (button.getId()) {
            case "projectsButton":
                changeTable("Projects");
                break;
            case "tasksButton":
                changeTable("Tasks");
                break;
            case "categoriesButton":
                changeTable("Categories");
                break;
            default:
                System.out.println("ID not found");
        }
    }

    private void changeTable(String table)  {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../Tables/" + table + "View.fxml"));
            tableContainer.getChildren().retainAll();
            tableContainer.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
