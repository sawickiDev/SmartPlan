package com.steveq.tracker.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    private StringProperty activity;
    private StringProperty project;
    private StringProperty time;
    private StringProperty date;

    public Task() {
    }

    public Task(String activity, String project, String time, String date) {
        this.activity = new SimpleStringProperty(activity);
        this.project = new SimpleStringProperty(project);
        this.time = new SimpleStringProperty(time);
        this.date = new SimpleStringProperty(date);
    }

    public String getActivity() {
        return activity.get();
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity.set(activity);
    }

    public String getProject() {
        return project.get();
    }

    public StringProperty projectProperty() {
        return project;
    }

    public void setProject(String project) {
        this.project.set(project);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
