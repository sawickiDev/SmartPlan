package com.steveq.tracker.timer;

import com.steveq.tracker.service.FileService;
import com.steveq.tracker.service.FileServiceImpl;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.ParseException;
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
    private static final String DATE_FORMAT = "dd/MM/yyy";
    private Timer timer;

    private long startTime;
    private LocalTime start;
    private boolean isRunning = false;

    private StringProperty elapsedTimeString;

    public TimerAPI() {
        elapsedTimeString = new SimpleStringProperty("00:00:00");
    }

    public void startTicking(){
        timer = new Timer("Time Measure Timer");
        startTime = getCurrentTimestamp();
        start = LocalTime.now();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    long elapsedTime = getCurrentTimestamp() - startTime;
                    System.out.println(elapsedTime);
                    System.out.println("DATE : " + formatTime(elapsedTime));
                    elapsedTimeString.setValue(formatTime(elapsedTime));
                });
            }
        }, 0, 1 * 1000);
        isRunning = true;
    }

    public void stopTicking(){
        timer.cancel();
        isRunning = false;
    }

    public String getCurrentDateString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date currentDate = new Date();
        return simpleDateFormat.format(currentDate);
    }

    public Date parseStringToDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        System.out.println("Parsed Date : " +simpleDateFormat.parse(date));
        return simpleDateFormat.parse(date);
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

    public boolean isRunning() {
        return isRunning;
    }
}
