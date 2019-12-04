package controllers;

import Const.Const;
import Database.CSP.CSP;
import Database.CSP.CSPDAO;
import Database.CSP.Category.CategoryDAO;
import Database.CSP.Priority.PriorityDAO;
import Database.CSP.Status.StatusDAO;
import Database.Project.Project;
import Database.Project.ProjectDAO;
import com.jfoenix.controls.JFXDatePicker;
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

    CSPDAO cspdao;

    ObservableList<PieChart.Data> pieChartData;
    HashMap<String, Integer> statisticList;
    ArrayList<Project> openProject;

    @FXML
    JFXDatePicker datePicker;

    @FXML
    private void statusButtonEvent(ActionEvent event) {
        createPieChart(Const.TABLE_STATUS, "Status");
    }

    private String getMonthName(int month) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[month];
    }

    @FXML
    private void categoriesButtonEvent(ActionEvent event) {
        createPieChart(Const.TABLE_CATEGORY, "Category");
    }

    @FXML
    private void priorityButtonEvent(ActionEvent event) {
        createPieChart(Const.TABLE_PRIORITY, "Priority");
    }


    public void createPieChart(String table, String title) {
        cspdao = new CSPDAO(table);
        int totalProjects = projectDAO.getFilteredProjects(Const.OPEN).size();
        statisticList = new HashMap<String, Integer>();
        openProject = projectDAO.getFilteredProjects(Const.OPEN);
        pieChartData = FXCollections.observableArrayList();
        for (CSP csp : cspdao.getAll()) {
            statisticList.put(csp.getName(), projectDAO.getProjectsCountByCSP(openProject, csp));
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
    }
}