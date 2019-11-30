package Forms;

import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
import Database.CSP.Priority.Priority;
import Database.CSP.Priority.PriorityDAO;
import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
import Database.Project.Project;
import Database.Project.ProjectDAO;
import Database.Task.Task;
import Database.Task.TaskDAO;
import com.jfoenix.controls.*;
import controllers.ButtonCell;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.Date;
import java.util.*;


import java.net.URL;


public class ProjectsFormController implements Initializable {

    @FXML
    VBox TasksHBox;
    @FXML
    Label taskOneLabel;
    HBox vBox;
    private Object button;

    public JFXTextField getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.setText(projectName);
    }


    @FXML
    private JFXTextField projectName;
    @FXML
    private JFXTextArea projectDescription;
    @FXML
     JFXComboBox<Category> category;
    @FXML
     JFXComboBox<Priority> priority;
    @FXML
     JFXComboBox<Status> status;
    @FXML
    private JFXDatePicker startDatePicker;
    @FXML
    private JFXDatePicker dueDatePicker;

    @FXML
    private VBox errorDisplay;

    private String projectNameStr="";
    private String projectDescriptionStr="";
    private int categoryStr;
    private int priorityStr;
    private int statusStr;
    private String startDateStr="";
    private String endDateStr="";

    public static Project editingProject;

    @FXML
    private Label projectTitle;
    @FXML
    private Button submitButton;

    @FXML
    private ToggleButton closeProject;

    public static boolean updateForm = false;
    CategoryDAO categoryDAO = CategoryDAO.getInstance();
    PriorityDAO priorityDAO = PriorityDAO.getInstance();
    StatusDAO statusDAO =  StatusDAO.getInstance();


    public void addNewTask(ActionEvent actionEvent) {

        vBox = new HBox();
        JFXTextField newTask = new JFXTextField();
        JFXButton remove = new JFXButton();
        remove.setText("-");
        remove.setStyle("-fx-min-width: 10px; " +
                "-fx-background-radius: 5em; " +
                "-fx-min-height: 30px; " +
                "-fx-max-width: 30px; " +
                "-fx-border-color: #d9534f; " +
                "-fx-text-fill: #903; " +
                "-fx-max-height: 30px;");
        /**
         * Remove the task row,when - clicked
         */
        remove.setOnAction(event -> {

            HBox thisParent= (HBox) remove.getParent();
            TasksHBox.getChildren().remove(thisParent);
            HBox parent = (HBox) remove.getParent();
            TasksHBox.getChildren().remove(parent);
        });
        taskOneLabel.setText("Task #1");
        Label label = new Label("Task #" + (TasksHBox.getChildren().size() + 1));
        label.setMinHeight(40);
        label.setMinWidth(100);
        newTask.setMinWidth(300);
        newTask.setMinHeight(40);

        vBox.getChildren().addAll(label,newTask,remove);
        if(TasksHBox.getChildren().size()<6){
            TasksHBox.getChildren().add(vBox);
        }
    }


    public void processProjectsForm(ActionEvent actionEvent) {

        LinkedList<String>tasksList=new LinkedList<>();
        LinkedList<String>errors=new LinkedList<>();


        //check each field if the data is entered
        if(projectName.getText().isEmpty() || projectName.getText().length()<10){
            errors.add("Project name should contain at least 10 letters");
            projectName.setStyle("-fx-border-color: red;");
        }else{
            projectName.setStyle("-fx-border-color: none;");
            projectNameStr=projectName.getText();
        }
        if(projectDescription.getText().isEmpty() || projectDescription.getText().length()<20){
            errors.add("Project description should contain at least 10 letters");
            projectDescription.setStyle("-fx-border-color: red;");
        }else{
            projectDescription.setStyle("-fx-border-color: none;");
            projectDescriptionStr=projectDescription.getText();
        }
        for (Node child : TasksHBox.getChildren()) {
            HBox box = (HBox) child;
            for (Node achild : box.getChildren()) {
                if (achild instanceof TextField) {
                    if(((TextField) achild).getText().isEmpty() || ((TextField) achild).getText().length()<10){
                        errors.add("Each Task should contain at least 10 letters");
                        achild.setStyle("-fx-border-color: red;");
                    }else{
                        achild.setStyle("-fx-border-color: none;");
                        tasksList.add(((TextField) achild).getText());
                    }
                }
            }
        }
        if(category.getValue()==null){
            errors.add("Select a Category");
            HBox catParent= (HBox) category.getParent();
            catParent.getChildren().add(new Label("Select a Category"));
            category.setStyle("-fx-border-color: red;");
        }else{
            category.setStyle("-fx-border-color: none;");
            categoryStr=category.getSelectionModel().getSelectedItem().getId();
        }
        if(priority.getValue()==null){
            errors.add("Select a Priority");
            priority.setStyle("-fx-border-color: red;");
        }else{
            priority.setStyle("-fx-border-color: none;");
            priorityStr=priority.getSelectionModel().getSelectedItem().getId();
        }


        if(status.getValue()==null){
            errors.add("Select a Status");
            status.setStyle("-fx-border-color: red;");
        }else{
            status.setStyle("-fx-border-color: none;");
            statusStr=status.getSelectionModel().getSelectedItem().getId();
        }
        if(startDatePicker.getValue()==null){
            errors.add("Select a Start Date for your project");
            startDatePicker.setStyle("-fx-border-color: red;");
        }else{
            startDatePicker.setStyle("-fx-border-color: none;");
            startDateStr=startDatePicker.getValue().toString();
        }
        if(dueDatePicker.getValue()==null){
            errors.add("Select a Start Date for your project");
            dueDatePicker.setStyle("-fx-border-color: red;");
        }else{
            dueDatePicker.setStyle("-fx-border-color: none;");
            endDateStr=dueDatePicker.getValue().toString();
        }


        //cleaning the Error container each click to remove old cache
        errorDisplay.getChildren().clear();
        if(errors.size()>0){
            for(int i=0;i<errors.size();i++){
                Label errorLabel=new Label(errors.get(i));
                errorDisplay.getChildren().add(errorLabel);
            }
        }else{
            errorDisplay.getChildren().add(new Label("ALL SET"));

            ProjectDAO projectDAO= ProjectDAO.getInstance();
            TaskDAO taskDAO = TaskDAO.getInstance();

                Date startdate= Date.valueOf(startDateStr);
                Date duedate= Date.valueOf(endDateStr);

                Project project = new Project(projectNameStr, projectDescriptionStr, statusStr, categoryStr, priorityStr, startdate, duedate);
                projectDAO.create(project);
                int lastInsertedId=projectDAO.getAll().get(projectDAO.getAll().size()-1).getId();

            for(int i=0;i<tasksList.size();i++){
                Task task = new Task(tasksList.get(i), lastInsertedId, 1);
                taskDAO.create(task);
            }


        }
        category.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                //TODO change me to something please
                }
        );


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeProject.setStyle("visibility: hidden;");

        //populate the combobox from database
        category.setItems(FXCollections.observableArrayList((ArrayList<Category>)categoryDAO.getAll()));
        priority.setItems(FXCollections.observableArrayList((ArrayList<Priority>)priorityDAO.getAll()));
        status.setItems(FXCollections.observableArrayList((ArrayList<Status>)statusDAO.getAll()));
        System.out.println("here"+ButtonCell.getEditProject());

        if (updateForm) {
            System.out.println("ktu"+editingProject);

            projectTitle.setText("Edit Project");
            submitButton.setText("Submit");
            closeProject.setStyle("visibility: visible;");
//                System.out.println(ButtonCell.getEditProject());
//                projectName.setText(ButtonCell.getEditProject().getTitle());
//                projectDescription.setText(ButtonCell.getEditProject().getDescription());
//                LocalDate date=new LocalDate(ButtonCell.getEditProject().getDueDate());
//                dueDatePicker.setValue( ButtonCell.getEditProject().getDueDate());

        }
    }


    public void closeTheProject(ActionEvent actionEvent) {
        System.out.println("ktu"+editingProject);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Warning!");
        alert.setContentText("Are you sure you want to close the project?");
        if (closeProject.isSelected()) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                projectTitle.setText("Closed!"); // test
                // database query should go here!

//                category.setValue(new Category(ButtonCell.getEditProject().getCategory()));


            } else {
                closeProject.setSelected(false);
            }
        }
    }
}
