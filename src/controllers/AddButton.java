package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controllers.forms.*;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddButton extends JFXButton {

    Pane replaceable;
    String type;
    public static ProjectsFormController controller;
    @FXML
    private JFXTextField projectName;

    public AddButton(Pane replaceable, String type) {
        this.replaceable = replaceable;
        this.type = type;
        this.setText("Add " + type);
        this.getStyleClass().add("addingBtn");
        this.setButtonType(ButtonType.RAISED);
        Text plus = GlyphsDude.createIcon(FontAwesomeIcon.PLUS);
        plus.setFill(Color.WHITE);
        this.setGraphic(plus);
        this.setPadding(new Insets(10, 30, 10, 30));
        this.setFont(Font.font(15));
        setOnAction(e -> {
            try {
                ProjectsFormController.updateForm = false;
                StatusFormController.updateForm = false;
                TaskFormController.updateForm = false;
                PrioritiesFormController.updateForm = false;
                CategoriesFormController.updateForm = false;
                Pane pane = FXMLLoader.load(getClass().getResource("../views/forms/" + type + "FormView.fxml"));
                replaceable.getChildren().retainAll();
                replaceable.getChildren().add(pane);

            } catch (IOException er) {
                er.printStackTrace();
            }
        });
    }
}
