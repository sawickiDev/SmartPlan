package com.steveq.tracker.service;

import com.steveq.tracker.model.Task;

import java.util.List;

public interface SmartPlanTrackerService {
    void toggleTimer();
    List<Task> loadTodayTasks();
}
