package controllers;

import Const.Const;
import Database.CSP.Category.Category;
import Database.CSP.Category.CategoryDAO;
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

    @FXML
    private void handleButton1Action(ActionEvent event) {
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
    private void handleButton2Action(ActionEvent event) {

    }

    @FXML
    private void handleButtonClearAction(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        piechart.setTitle("");
        piechart.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        projectDAO = ProjectDAO.getInstance();
        statusDAO = StatusDAO.getInstance();
        categoryDAO = CategoryDAO.getInstance();
    }
}
