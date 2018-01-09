package com.steveq.tracker.service;

import com.steveq.tracker.model.Task;
import com.steveq.tracker.timer.TimerAPI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FileServiceImpl implements FileService{

    private static final String FILE_NAME = "local.txt";

    private String directory;
    private Path filePath;
    private File file;
    private TimerAPI timerAPI;

    public FileServiceImpl(){

        timerAPI = new TimerAPI();
        directory = resolveDirectory();
        filePath = Paths.get(directory + "/" + FILE_NAME);
        initializeDirectory(directory);
        file = new File(directory + "/" + FILE_NAME);
        if(!file.exists()) {
            try {
                if(file.createNewFile()){
                    System.out.println("File created");
                } else {
                    System.out.println("File not created");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeDirectory(String directory){
        if(!Files.exists(Paths.get(directory))){
            try {
                Files.createDirectories(Paths.get(directory));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void writeTaskToFile(Task task) {

        List<String> lines = new ArrayList<>();
        LinkedList<String> linkedLines = new LinkedList<>();

        try {
            lines = Files.readAllLines(filePath);
            linkedLines.addAll(lines);
            linkedLines.addFirst(createTaskEntry(task));
            System.out.println("LINKED LINES : " + linkedLines);
            Files.write(filePath, createFileFromLines(linkedLines).getBytes(), StandardOpenOption.WRITE);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private String createFileFromLines(List<String> lines){

        StringBuilder builder = new StringBuilder();

        for(String line : lines){
            builder.append(line);
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public List<Task> getTodayTasks() {

        List<Task> todayTasks = new ArrayList<>();
        for(Task task : loadAllTasks()){

            if(timerAPI.getCurrentDateString().equals(task.getDate())){
                todayTasks.add(task);
            }
        }

        return todayTasks;
    }

    @Override
    public List<Task> getYesterdayTasks() {
        return null;
    }

    @Override
    public List<Task> getOlderTasks() {
        return null;
    }

    private List<Task> loadAllTasks() {

        List<String> lines = new ArrayList<>();
        List<Task> tasks = new ArrayList<>();

        try {
            lines = Files.readAllLines(filePath);
            for(String line : lines){
                if(line != null && !line.equals("")){
                    System.out.println("LINE : " +line);
                    String[] chunks = line.split(",");
                    tasks.add(new Task(chunks[0], chunks[1], chunks[2], chunks[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private String createTaskEntry(Task task){

        StringBuilder builder = new StringBuilder();
        builder.append(task.getActivity());
        builder.append(",");
        builder.append(task.getProject());
        builder.append(",");
        builder.append(task.getTime());
        builder.append(",");
        builder.append(task.getDate());

        return builder.toString();
    }

    private String resolveDirectory(){

        String pathString = "";

        switch(System.getProperty("os.name")){
            case "Linux" :
                pathString = System.getProperty("user.home") + "/.SmartPlan";
                break;
            case "Windows" :
                pathString = System.getProperty("user.home") + "/AppData/local/SmartPlan";
                break;
            default :
                break;
        }

        return pathString;
    }
}
