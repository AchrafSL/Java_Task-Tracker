package org.example.Utils;

import org.example.Models.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class TaskManager {

    final static String fileName = "task.json";



    //Get the next ID:
    public static Integer getNextId()
    {
        List<Task> tasks = readAllTasks();

        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }



    //add Task ( Another version )
    public static void addTask(String description)
    {
        LocalDate createdAt = LocalDate.now();
        Integer idT = getNextId();

        addTask(new Task(idT,description,TaskStatus.TODO,createdAt,null));
    }















    //addTask ( CREATE )
    public static void addTask(Task task)
    {
        String data = task.toJsonElem();

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");

            //Check if the file is empty:
            if(randomAccessFile.read() == -1)
            {
                randomAccessFile.write("[".getBytes());
                randomAccessFile.write(data.getBytes());
                randomAccessFile.write("]".getBytes());
            }
            else{
                // Delete the lastChar and replace it with ,
                randomAccessFile.seek(randomAccessFile.length() - 1);
                randomAccessFile.write(",".getBytes());
                randomAccessFile.write(data.getBytes());
                randomAccessFile.write("]".getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }







    // updateTask ( UPDATE )
    public static void updateTask(String newTaskDesc,Integer taskID)
    {
        //Check if that task exist:
        List<Task> taskList = readAllTasks();
        boolean exits = false;
        int index = 0;
        for(Task e:taskList)
        {
            if (e.getId().equals(taskID)) {
                exits = true;
                break;
            }
            index ++;

        }


        // if so update the values:
        if(exits)
        {
            taskList.get(index).setDescription(newTaskDesc);
            taskList.get(index).setUpdatedAt(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)));
            writeAllTasks(taskList);

        }
        // print: Error ( Task doesn't exist )
        else{
            System.out.println("Task doesn't exist!");
        }






    }





    //Read All Tasks:
    public static List<Task> readAllTasks()
    {
        List<Task> taskList = new ArrayList<>();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, "r");
            randomAccessFile.seek(0);

            byte [] bytes= new byte[(int) randomAccessFile.length()];
            randomAccessFile.readFully(bytes);

            String fileData = new String(bytes);

            // Define the regex pattern
            Pattern pattern = Pattern.compile("\\{.*?\\}", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(fileData);

            // Find all matches
            while (matcher.find()) {
                taskList.add(Task.toTaskElem(matcher.group()));
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return taskList;
    }







    //Write All Tasks:
    public static void writeAllTasks(List<Task> TaskList)
    {
        //Delete all tasks:
        try
        {
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileName,"rw");
            randomAccessFile.setLength(0);

            for(Task e:TaskList)
            {
                TaskManager.addTask(e);
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }



    public static void deleteTask(Integer id)
    {
        List<Task> taskList = readAllTasks();

        taskList.removeIf(task-> task.getId().equals(id));

        writeAllTasks(taskList);

    }


    // list All tasks:
    public static void listAllTasks()
    {
        List<Task> taskL = readAllTasks();

        taskL.stream().map(task -> task.toJsonElem()).forEach(System.out::println);
    }


    public static void listAllTasks(TaskStatus status)
    {
        List<Task> taskL = readAllTasks();
        taskL.stream()
                .filter(task -> task.getStatus().equals(status))
                .map(task -> task.toJsonElem())
                .forEach(System.out::println);
    }


    public static void mark(TaskStatus status, Integer id)
    {
        List<Task> taskL = readAllTasks();

        taskL.stream()
                .filter(task -> task.getId().equals(id))
                .forEach(task -> task.setStatus(status));

        writeAllTasks(taskL);


    }



}
