package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<ProjectItem, ProjectItem> {

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
}
