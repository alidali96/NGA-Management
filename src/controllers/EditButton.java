package controllers;

import Database.CSP.Category.Category;
import Database.CSP.Priority.Priority;
import Database.CSP.Status.Status;
import Database.Project.Project;
import Database.Task.Task;
import com.jfoenix.controls.JFXButton;
import controllers.forms.*;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class EditButton<S, T> extends TableCell<S, T> {


    JFXButton btn;
    Pane replaceable;
    String form;

    public EditButton(Pane replaceable, String form) {
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
                if (object instanceof Project) {
                    ProjectsFormController.editingProject = (Project) object;
                } else if (object instanceof Task) {
                    TaskFormController.editingTask = (Task) object;
                } else if (object instanceof Category) {
                    CategoriesFormController.editingCategory = (Category) object;
                } else if (object instanceof Database.CSP.Priority.Priority) {
                    PrioritiesFormController.editingPriority = (Priority) object;
                } else if (object instanceof Status) {
                    StatusFormController.editingStatus = (Status) object;
                }


                editProject(event);
            });
            setGraphic(btn);
            setText(null);
        }
    }

    public void editProject(ActionEvent actionEvent) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../views/forms/" + form + "FormView.fxml"));
            replaceable.getChildren().retainAll();
            replaceable.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
