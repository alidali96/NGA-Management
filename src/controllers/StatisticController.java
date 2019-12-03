package controllers;

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

    HashMap<String, Integer> dataList = new HashMap<String, Integer>();
    @FXML
    private void handleButton1Action(ActionEvent event) {

        for (Status status : (ArrayList<Status>) statusDAO.getAll()) {
            dataList.put(status.getName(), projectDAO.getProjectsCountByStatus(status.getId()));
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//                new PieChart.Data("January", 100),
//                new PieChart.Data("February", 200),
//                new PieChart.Data("March", 50),
//                new PieChart.Data("April", 75),
//                new PieChart.Data("May", 110),
//                new PieChart.Data("June", 300),
//                new PieChart.Data("July", 111),
//                new PieChart.Data("August", 30),
//                new PieChart.Data("September", 75),
//                new PieChart.Data("October", 55),
//                new PieChart.Data("November", 225),
//                new PieChart.Data("December", 99));


        for(Map.Entry<String, Integer> data : dataList.entrySet()) {
            pieChartData.add(new PieChart.Data(data.getKey(), data.getValue()));
        }

        piechart.setTitle("Monthly Record");
        piechart.setData(pieChartData);
    }

    @FXML
    private void handleButton2Action(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Sunday", 30),
                        new PieChart.Data("Monday", 45),
                        new PieChart.Data("Tuesday", 70),
                        new PieChart.Data("Wednesday", 97),
                        new PieChart.Data("Thursday", 100),
                        new PieChart.Data("Friday", 80),
                        new PieChart.Data("Saturday", 10));

        piechart.setTitle("Weekly Record");
        piechart.setData(pieChartData);
    }

    @FXML
    private void handleButtonClearAction(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        piechart.setTitle("");
        piechart.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        projectDAO = ProjectDAO.getInstance();
        statusDAO = StatusDAO.getInstance();
    }
}
