package org.example.Utils;

import org.example.Models.Task;

import java.io.*;

public class TaskManager {

    final static String fileName = "task.json";

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



    //ListTasks ( SELECT )

    //readTasks ( SELECT )

    //WriteTasks ( Update / Insert )

}
