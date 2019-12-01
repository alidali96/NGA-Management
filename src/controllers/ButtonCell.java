package controllers;

import Database.Project.Project;
import Forms.*;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class ButtonCell<S, T> extends TableCell<S, T> {

    private static Project editProject;
    JFXButton btn;
    Pane replaceable;
    String form;

    public ButtonCell(Pane replaceable, String form) {
        this.replaceable = replaceable;
        this.form = form;
        btn = new JFXButton();
    }

    @Override
    public void updateItem(T object, boolean empty) {
        super.updateItem(object, empty);
        if (empty) {
            setGraphic(null);
            setText(null);
        } else {
            btn.setGraphic(GlyphsDude.createIcon(FontAwesomeIcon.PENCIL));

            btn.setButtonType(JFXButton.ButtonType.RAISED);
            btn.setOnAction(event -> {
                ProjectsFormController.updateForm = true;
                StatusFormController.updateForm = true;
                TaskFormController.updateForm = true;
                PrioritiesFormController.updateForm = true;
                CategoriesFormController.updateForm = true;
                editProject = (Project) object;
                ProjectsFormController.editingProject = editProject;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Project getEditProject() {
        return editProject;
    }

    public void setEditProject(Project editProject) {
        this.editProject = editProject;
    }
}
