package com.steveq.tracker.controller;

import com.steveq.tracker.model.Task;
import com.steveq.tracker.timer.TimerAPI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SmartPlanTrackerController implements Initializable{

    @FXML
    private TextField projectTextField;

    @FXML
    private TextField activityTestField;

    @FXML
    private TableColumn<Task, String> todayProjectTableColumn;

    @FXML
    private Button startButton;

    @FXML
    private TableColumn<Task, String> todayDateTableColumn;

    @FXML
    private TableColumn<Task, String> todayTimeTableColumn;

    @FXML
    private TitledPane todayTitlePane;

    @FXML
    private TableView<Task> todayTableView;

    @FXML
    private Label timeLabel;

    @FXML
    private TableColumn<Task, String> todayActivityTableColumn;

    private TimerAPI timerAPI;

    public SmartPlanTrackerController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timerAPI = new TimerAPI();

        timeLabel.setText(timerAPI.getElapsedTimeString().get());
        timerAPI.getElapsedTimeString().addListener((observable, oldValue, newValue) -> {
            System.out.println("NEW VALUE : " + newValue);
            timeLabel.setText(newValue);
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timerAPI.startTicking();
            }
        });
    }

    public TextField getProjectTextField() {
        return projectTextField;
    }

    public void setProjectTextField(TextField projectTextField) {
        this.projectTextField = projectTextField;
    }

    public TextField getActivityTestField() {
        return activityTestField;
    }

    public void setActivityTestField(TextField activityTestField) {
        this.activityTestField = activityTestField;
    }

    public Button getStartButton() {
        return startButton;
    }

    public void setStartButton(Button startButton) {
        this.startButton = startButton;
    }

    public Label getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(Label timeLabel) {
        this.timeLabel = timeLabel;
    }

    public TableColumn<Task, String> getTodayProjectTableColumn() {
        return todayProjectTableColumn;
    }

    public void setTodayProjectTableColumn(TableColumn<Task, String> todayProjectTableColumn) {
        this.todayProjectTableColumn = todayProjectTableColumn;
    }

    public TableColumn<Task, String> getTodayDateTableColumn() {
        return todayDateTableColumn;
    }

    public void setTodayDateTableColumn(TableColumn<Task, String> todayDateTableColumn) {
        this.todayDateTableColumn = todayDateTableColumn;
    }

    public TableColumn<Task, String> getTodayTimeTableColumn() {
        return todayTimeTableColumn;
    }

    public void setTodayTimeTableColumn(TableColumn<Task, String> todayTimeTableColumn) {
        this.todayTimeTableColumn = todayTimeTableColumn;
    }

    public TitledPane getTodayTitlePane() {
        return todayTitlePane;
    }

    public void setTodayTitlePane(TitledPane todayTitlePane) {
        this.todayTitlePane = todayTitlePane;
    }

    public TableView<Task> getTodayTableView() {
        return todayTableView;
    }

    public void setTodayTableView(TableView<Task> todayTableView) {
        this.todayTableView = todayTableView;
    }

    public TableColumn<Task, String> getTodayActivityTableColumn() {
        return todayActivityTableColumn;
    }

    public void setTodayActivityTableColumn(TableColumn<Task, String> todayActivityTableColumn) {
        this.todayActivityTableColumn = todayActivityTableColumn;
    }
}
