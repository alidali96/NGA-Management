package controllers;

import Forms.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;

public class AddProjectButton extends JFXButton{

    Pane replaceable;
    String form;
    public static ProjectsFormController controller;
    @FXML
    private JFXTextField projectName;

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
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Forms/" + form + "FormView.fxml"));
                Pane pane = FXMLLoader.load(getClass().getResource("../Forms/" + form + "FormView.fxml"));
//                Pane pane = (Pane) loader.load();
//                controller = loader.getController();
//                System.out.println((char[]) loader.getController());
//                ProjectsFormController.projectName.setText("ckemii");
                ProjectsFormController customControl = new ProjectsFormController();
//                customControl.setText("Hello!");
                customControl.setProjectName("hii");

                replaceable.getChildren().retainAll();
                replaceable.getChildren().add(pane);
                this.setStyle("visibility: hidden;");
            } catch (IOException er) {
                er.printStackTrace();
            }
        });
    }
}
