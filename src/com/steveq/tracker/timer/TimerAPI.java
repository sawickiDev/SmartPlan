package com.steveq.tracker.timer;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class TimerAPI {

    private static final String TIME_FORMAT = "HH:mm:ss";
    private Timer timer;

    private long startTime;
    private LocalTime start;

    private StringProperty elapsedTimeString;

    private TimerTask timeMeasureTask = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
                long elapsedTime = getCurrentTimestamp() - startTime;
                System.out.println(elapsedTime);
                System.out.println("DATE : " + formatTime(elapsedTime));
                elapsedTimeString.setValue(formatTime(elapsedTime));
            });
        }
    };

    public TimerAPI() {
        timer = new Timer("Time Measure Timer");
        elapsedTimeString = new SimpleStringProperty("00:00:00");
    }

    public void startTicking(){
        startTime = getCurrentTimestamp();
        start = LocalTime.now();
        timer.scheduleAtFixedRate(timeMeasureTask, 0, 1*1000);
    }

    private String formatTime(long timestamp){
        Date tempDate = new Date();
        tempDate.setTime(timestamp);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return simpleDateFormat.format(tempDate);
    }

    private long getCurrentTimestamp(){
        return new Date().getTime();
    }

    public StringProperty getElapsedTimeString() {
        return elapsedTimeString;
    }

    public StringProperty elapsedTimeStringProperty() {
        return elapsedTimeString;
    }

    public void setElapsedTimeString(String elapsedTimeString) {
        this.elapsedTimeString.set(elapsedTimeString);
    }
}
