package controllers;


import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectController implements Initializable {

    @FXML
    TableView table;

    @FXML
    TableColumn<ProjectItem, String> id;
    @FXML
    TableColumn<ProjectItem, String> projectName;
    @FXML
    TableColumn<ProjectItem, String> category;
    @FXML
    TableColumn<ProjectItem, String> startDate;
    @FXML
    TableColumn<ProjectItem, String> dueDate;
    @FXML
    TableColumn<ProjectItem, String> status;
    @FXML
    TableColumn<ProjectItem, String> priority;
    @FXML
    TableColumn<ProjectItem, ProjectItem> edit;

    @FXML
    private Pane replaceable;

    @FXML
    JFXButton addProjectsBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        projectName.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

        edit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));


        edit.setCellFactory(param -> new TableCell<ProjectItem, ProjectItem>() {
            @Override
            public void updateItem(ProjectItem project, boolean empty) {
                super.updateItem(project, empty);

                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    JFXButton btn = new JFXButton();
                    btn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PENCIL));

                    btn.setButtonType(JFXButton.ButtonType.RAISED);
                    btn.setOnAction(event -> {
//                        System.out.println(project.getId());
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });

        ObservableList<ProjectItem> projectModel1 = FXCollections.observableArrayList();

        projectModel1.add(new ProjectItem("1", "Project1", "Design", "2019", "Acive", "fast", "2020"));


        table.setItems(projectModel1);
    }

    public void addProject(ActionEvent actionEvent) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../Forms/ProjectsFormView.fxml"));
            replaceable.getChildren().retainAll();
            replaceable.getChildren().add(pane);
            addProjectsBtn.setStyle("visibility: hidden;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

