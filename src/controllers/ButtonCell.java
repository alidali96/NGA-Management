package controllers;

import Database.Project.Project;
import Forms.ProjectsFormController;
import Forms.StatusFormController;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ButtonCell extends TableCell<Project, Project> {

    JFXButton btn;
    VBox replaceable;
    String form;

    public ButtonCell(VBox replaceable, String form) {
        this.replaceable = replaceable;
        this.form = form;
        btn = new JFXButton();
    }

    @Override
    public void updateItem(Project project, boolean empty) {
        super.updateItem(project, empty);

        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            btn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PENCIL));

            btn.setButtonType(JFXButton.ButtonType.RAISED);
            btn.setOnAction(event -> {
                ProjectsFormController.updateForm = true;
                StatusFormController.updateForm = true;
                editProject(event);
            });
            setGraphic(btn);
            setText(null);
        }
    }

    public void editProject(ActionEvent actionEvent) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../Forms/" + form + "FormView.fxml"));
            replaceable.getChildren().retainAll();
            replaceable.getChildren().add(pane);
//            .setStyle("visibility: hidden;");
//           AddProjectButton.setStyle("visibility: hidden;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
