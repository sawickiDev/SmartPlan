package com.steveq.tracker.service;

import com.steveq.tracker.model.Task;

import java.util.List;

public interface FileService {
    void writeTaskToFile(Task task);
    List<Task> getTodayTasks();
    List<Task> getYesterdayTasks();
    List<Task> getOlderTasks();
}
