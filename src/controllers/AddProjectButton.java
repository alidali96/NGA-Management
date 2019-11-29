package controllers;

import Forms.*;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddProjectButton extends JFXButton{

    Pane replaceable;
    String form;

    public AddProjectButton(Pane replaceable, String form) {
        this.replaceable = replaceable;
        this.form = form;
        this.setText("Add");
        this.setStyle("-fx-background-color: #903; -fx-text-fill: white;");
        this.setButtonType(ButtonType.RAISED);
        Text plus=GlyphsDude.createIcon(FontAwesomeIcon.PLUS);
        plus.setFill(Color.WHITE);
        this.setGraphic(plus);
        this.setPadding(new Insets(10,30,10,30));
        this.setFont(Font.font(15));
        setOnAction(e -> {
            try {
                ProjectsFormController.updateForm = false;
                StatusFormController.updateForm = false;
                TaskFormController.updateForm = false;
                PrioritiesFormController.updateForm = false;
                CategoriesFormController.updateForm = false;
                Pane pane = FXMLLoader.load(getClass().getResource("../Forms/" + form + "FormView.fxml"));
                replaceable.getChildren().retainAll();
                replaceable.getChildren().add(pane);
                this.setStyle("visibility: hidden;");
            } catch (IOException er) {
                er.printStackTrace();
            }
        });
    }
}
