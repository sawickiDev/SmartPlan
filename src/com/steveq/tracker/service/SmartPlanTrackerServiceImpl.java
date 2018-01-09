package com.steveq.tracker.service;

import com.steveq.tracker.controller.SmartPlanTrackerController;
import com.steveq.tracker.model.Task;
import com.steveq.tracker.timer.TimerAPI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class SmartPlanTrackerServiceImpl implements SmartPlanTrackerService{

    private SmartPlanTrackerController controller;
    private TimerAPI timerAPI;
    private FileService fileService;

    public SmartPlanTrackerServiceImpl(SmartPlanTrackerController controller){
        this.controller = controller;
        this.timerAPI = new TimerAPI();
        fileService = new FileServiceImpl();


        timerAPI.getElapsedTimeString().addListener((observable, oldValue, newValue) -> {
            controller.getTimeLabel().setText(newValue);
        });
    }

    @Override
    public void toggleTimer() {
        if(!timerAPI.isRunning()){
            timerAPI.startTicking();
            controller.getStartButton().setText("STOP");

            controller.getStartButton().getStyleClass().remove("button-green");
            controller.getStartButton().getStyleClass().add("button-red");
        } else {
            timerAPI.stopTicking();
            controller.getStartButton().setText("GO");
            Task newlyCreatedTask =
                    new Task(
                            controller.getActivityTextField().textProperty().get(),
                            controller.getProjectTextField().textProperty().get(),
                            timerAPI.getElapsedTimeString().get(),
                            timerAPI.getCurrentDateString()
                    );
            fileService.writeTaskToFile(newlyCreatedTask);

            controller.getStartButton().getStyleClass().remove("button-red");
            controller.getStartButton().getStyleClass().add("button-green");

            controller.getTodayTasks().add(0, newlyCreatedTask);
        }
    }

    @Override
    public List<Task> loadTodayTasks() {
        return fileService.getTodayTasks();
    }
}
