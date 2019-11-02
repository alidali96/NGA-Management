package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Controller{
    @FXML
    AnchorPane tableContainer;

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
