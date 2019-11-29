package Forms;

import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
import Database.Project.Project;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static Const.Const.toRGBCode;

public class CategoriesFormController implements Initializable {

    @FXML
    Button submitButton;
    @FXML
    Label title;

    @FXML
    JFXTextField name;

    @FXML
    JFXColorPicker color;

    @FXML
    VBox errorDisplay;

    String categoryNameStr;
    String colorNameStr;

    public static boolean updateForm = false;


    public void processCategoryForm(ActionEvent actionEvent) {
        LinkedList<String> errors = new LinkedList<>();
        colorNameStr = toRGBCode(color.getValue());
        CategoryDAO categoryDAO=CategoryDAO.getInstance();
        List<String> strings = categoryDAO.getAll().stream()
                .map(object -> Objects.toString(object.toString().toLowerCase(), null))
                .collect(Collectors.toList());
        if(strings.contains(name.getText().toLowerCase())){
            errors.add(name.getText()+" Already exists. Try to edit it or add another one with different name.");
            name.setStyle("-fx-border-color: red;");
        }else if(name.getText().isEmpty() || name.getText().length() < 3) {
            errors.add("Category should contain 2 or more letters");
            name.setStyle("-fx-border-color: red;");
        } else {
            name.setStyle("-fx-border-color: none;");
            categoryNameStr = name.getText();
        }

        if(colorNameStr.isEmpty() || colorNameStr.equals("#FFFFFF")){
            errors.add("white color is not that nice :)");
            color.setStyle("-fx-border-color: red;");
        }else{
            color.setStyle("-fx-border-color: none;");
            colorNameStr = toRGBCode(color.getValue());
        }
        errorDisplay.getChildren().clear();
        if(errors.size()>0){
            for(int i=0;i<errors.size();i++){
                Label errorLabel=new Label(errors.get(i));
                errorDisplay.getChildren().add(errorLabel);
            }
        }else{
            Category category = new Category(categoryNameStr,colorNameStr);
            if(categoryDAO.create(category)==1){
                errorDisplay.getChildren().add(new Label(categoryNameStr+" added successfully."));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (updateForm) {
            title.setText("Edit Category");
            submitButton.setText("Submit");
        }
    }


}
