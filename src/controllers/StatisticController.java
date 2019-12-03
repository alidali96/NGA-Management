package controllers;

import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
import Database.CSP.Priority.Priority;
import Database.CSP.Priority.PriorityDAO;
import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
import Database.Project.ProjectDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.*;

public class StatisticController implements Initializable {

    @FXML
    private PieChart piechart;
    ProjectDAO projectDAO;
    StatusDAO statusDAO;
    CategoryDAO categoryDAO;
    PriorityDAO priorityDAO;

    @FXML
    private void statusButtonEvent(ActionEvent event) {
        HashMap<String, Integer> projectList = new HashMap<String, Integer>();
        int totalProjects = projectDAO.getAll().size();
        for (Status status : (ArrayList<Status>) statusDAO.getAll()) {
            projectList.put(status.getName(), projectDAO.getProjectsCountByStatus(status.getId()));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> data : projectList.entrySet()) {
            double parentage = 100.0 * data.getValue() / totalProjects;
            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
        }
        piechart.setTitle("Project Statistic");
        piechart.setData(pieChartData);
    }

    @FXML
    private void categoriesButtonEvent(ActionEvent event) {
        HashMap<String, Integer> categoriesList = new HashMap<String, Integer>();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int totalProjects = projectDAO.getAll().size();
        for (Category category : (ArrayList<Category>) categoryDAO.getAll()) {
            categoriesList.put(category.getName(), projectDAO.getProjectsCountByCategory(category.getId()));
        }
        for (Map.Entry<String, Integer> data : categoriesList.entrySet()) {
            double parentage = 100.0 * data.getValue() / totalProjects;
            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
        }
        piechart.setTitle("Categories Statistic");
        piechart.setData(pieChartData);
    }

    @FXML
    private void priorityButtonEvent(ActionEvent event) {
        HashMap<String, Integer> priorityList = new HashMap<String, Integer>();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int totalPriority = priorityDAO.getAll().size();
//        for (Priority priority : (ArrayList<Priority>) priorityDAO.getAll()) {
//          //  priorityList.put(priority.getName(), priority.(priority.getId()));
//        }
        for (Map.Entry<String, Integer> data : priorityList.entrySet()) {
            double parentage = 100.0 * data.getValue() / totalPriority;
            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
        }
        piechart.setTitle("Categories Statistic");
        piechart.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projectDAO = ProjectDAO.getInstance();
        statusDAO = StatusDAO.getInstance();
        categoryDAO = CategoryDAO.getInstance();
        priorityDAO = PriorityDAO.getInstance();
    }
}