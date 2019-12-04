package controllers;

import Const.Const;
import Database.CSP.CSP;
import Database.CSP.CSPDAO;
import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
import Database.CSP.Priority.Priority;
import Database.CSP.Priority.PriorityDAO;
import Database.CSP.Status.Status;
import Database.CSP.Status.StatusDAO;
import Database.Project.Project;
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

    CSPDAO cspdao;

    ObservableList<PieChart.Data> pieChartData;
    HashMap<String, Integer> statisticList;
    ArrayList<Project> openProject;


    @FXML
    private void statusButtonEvent(ActionEvent event) {
//        int totalProjects = projectDAO.getFilteredProjects(Const.OPEN).size();
//        statisticList = new HashMap<String, Integer>();
//        openProject = projectDAO.getFilteredProjects(1);
//        pieChartData = FXCollections.observableArrayList();
//        for (Status status : (ArrayList<Status>) statusDAO.getAll()) {
//            statisticList.put(status.getName(), projectDAO.getProjectsCountByStatus(openProject, status.getId()));
//        }
//        for (Map.Entry<String, Integer> data : statisticList.entrySet()) {
//            double parentage = 100.0 * data.getValue() / totalProjects;
//            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
//        }
//        piechart.setTitle("Project Statistic");
//        piechart.setData(pieChartData);
        chart(Const.TABLE_STATUS, "Status");

    }

    @FXML
    private void categoriesButtonEvent(ActionEvent event) {
//        int totalProjects = projectDAO.getFilteredProjects(Const.OPEN).size();
//        statisticList = new HashMap<String, Integer>();
//        openProject = projectDAO.getFilteredProjects(1);
//        pieChartData = FXCollections.observableArrayList();
//        for (Category category : (ArrayList<Category>) categoryDAO.getAll()) {
//            statisticList.put(category.getName(), projectDAO.getProjectsCountByCategory(openProject, category.getId()));
//        }
//        for (Map.Entry<String, Integer> data : statisticList.entrySet()) {
//            double parentage = 100.0 * data.getValue() / totalProjects;
//            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
//        }
//        piechart.setTitle("Categories Statistic");
//        piechart.setData(pieChartData);
        chart(Const.TABLE_CATEGORY, "Category");

    }

    @FXML
    private void priorityButtonEvent(ActionEvent event) {
//        int totalProjects = projectDAO.getFilteredProjects(Const.OPEN).size();
//        statisticList = new HashMap<String, Integer>();
//        openProject = projectDAO.getFilteredProjects(1);
//        pieChartData = FXCollections.observableArrayList();
//        for (Priority priority : (ArrayList<Priority>) priorityDAO.getAll()) {
//            statisticList.put(priority.getName(), projectDAO.getProjectsCountByPriority(openProject, priority.getId()));
//        }
//        for (Map.Entry<String, Integer> data : statisticList.entrySet()) {
//            double parentage = 100.0 * data.getValue() / totalProjects;
//            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
//        }
//        piechart.setTitle("Categories Statistic");
//        piechart.setData(pieChartData);
        chart(Const.TABLE_PRIORITY, "Priority");
    }


    public void chart(String table, String title) {
        cspdao = new CSPDAO(table);
        int totalProjects = projectDAO.getFilteredProjects(Const.OPEN).size();
        statisticList = new HashMap<String, Integer>();
        openProject = projectDAO.getFilteredProjects(Const.OPEN);
        pieChartData = FXCollections.observableArrayList();
        for (CSP csp :  cspdao.getAll()) {
         //   statisticList.put(csp.getName(), projectDAO.getProjectsCountByCSP(openProject, csp));
        }
        for (Map.Entry<String, Integer> data : statisticList.entrySet()) {
            double parentage = 100.0 * data.getValue() / totalProjects;
            pieChartData.add(new PieChart.Data(data.getKey() + " %" + String.format("%.2f", parentage), data.getValue()));
        }
        piechart.setTitle(title + " Statistic");
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