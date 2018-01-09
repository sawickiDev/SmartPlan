package com.steveq.tracker.controller;

import com.steveq.tracker.model.Task;
import com.steveq.tracker.service.SmartPlanTrackerService;
import com.steveq.tracker.service.SmartPlanTrackerServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SmartPlanTrackerController implements Initializable{

    private ObservableList<Task> todayTasks = FXCollections.observableArrayList();

    @FXML
    private TextField projectTextField;

    @FXML
    private TextField activityTextField;

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

    private SmartPlanTrackerService smartPlanTrackerService;

    public SmartPlanTrackerController() {
        smartPlanTrackerService = new SmartPlanTrackerServiceImpl(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        todayTableView.setItems(todayTasks);

        todayActivityTableColumn.setCellValueFactory(rowData -> rowData.getValue().activityProperty());
        todayProjectTableColumn.setCellValueFactory(rowData -> rowData.getValue().projectProperty());
        todayTimeTableColumn.setCellValueFactory(rowData -> rowData.getValue().timeProperty());
        todayDateTableColumn.setCellValueFactory(rowData -> rowData.getValue().dateProperty());

        todayTasks.addAll(smartPlanTrackerService.loadTodayTasks());

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                smartPlanTrackerService.toggleTimer();
            }
        });

        activityTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                boolean isNewValueBlank = newValue == null || newValue.trim().equals("");

                if(!isNewValueBlank)
                    startButton.setDisable(false);
                else
                    startButton.setDisable(true);
            }
        });
    }

    public TextField getProjectTextField() {
        return projectTextField;
    }

    public void setProjectTextField(TextField projectTextField) {
        this.projectTextField = projectTextField;
    }

    public TextField getActivityTextField() {
        return activityTextField;
    }

    public void setActivityTextField(TextField activityTextField) {
        this.activityTextField = activityTextField;
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

    public ObservableList<Task> getTodayTasks() {
        return todayTasks;
    }

    public void setTodayTasks(ObservableList<Task> todayTasks) {
        this.todayTasks = todayTasks;
    }
}
