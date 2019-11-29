package controllers;


import Database.DatabaseConnection;
import Database.Project.ProjectDAO;
import Database.Task.TaskDAO;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import Database.Project.Project;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {

    @FXML
    TableView table;

    @FXML
    TableColumn<Project, String> id;
    @FXML
    TableColumn<Project, String> projectName;
    @FXML
    TableColumn<Project, String> category;
    @FXML
    TableColumn<Project, String> startDate;
    @FXML
    TableColumn<Project, String> dueDate;
    @FXML
    TableColumn<Project, String> status;
    @FXML
    TableColumn<Project, String> priority;
    @FXML
    TableColumn<Project, Project> edit;

    @FXML
    private VBox replaceable;

    @FXML
    JFXButton addProjectsBtn;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        DatabaseConnection.getInstance();
        projectName.setCellValueFactory(new PropertyValueFactory<>("title"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        replaceable.getChildren().set(0, new AddProjectButton(replaceable, "Projects"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        edit.setCellFactory(param -> new ButtonCell(replaceable, "Projects"));

        ObservableList<Project> projectModel1 = FXCollections.observableArrayList();


        Date date = new Date(System.currentTimeMillis());
        Date due = new Date(System.currentTimeMillis());
        due.setTime(System.currentTimeMillis() + 999999999);
        ProjectDAO projectDAO = ProjectDAO.getInstance();
        projectDAO.testPrintAll();
        for(int i=0;i<projectDAO.getAll().size();i++){
            projectModel1.add(
                    new Project(
                            projectDAO.getAll().get(i).getTitle(),
                            projectDAO.getAll().get(i).getDescription(),
                            projectDAO.getAll().get(i).getStatus(),
                            projectDAO.getAll().get(i).getCategory(),
                            projectDAO.getAll().get(i).getPriority(),
                            projectDAO.getAll().get(i).getStartDate(),
                            projectDAO.getAll().get(i).getDueDate()
                    )
            );
        }
//        projectModel1.add(new Project("Tower 1 Defense", "DESCRIPTION 1 ABOUT THE GAME", 66, 1, 1, date, due));
//        projectModel1.add(new Project("Tower 2 Defense", "DESCRIPTION 2 ABOUT THE GAME", 66, 1, 1, date, due));
//        projectModel1.add(new Project("Tower 3 Defense", "DESCRIPTION 3 ABOUT THE GAME", 66, 1, 1, date, due));
//        projectModel1.add(new Project("Tower 4 Defense", "DESCRIPTION 4 ABOUT THE GAME", 66, 1, 1, date, due));
//        projectModel1.add(new Project("Tower 5 Defense", "DESCRIPTION 5 ABOUT THE GAME", 66, 1, 1, date, due));

        table.setItems(projectModel1);
    }
}

