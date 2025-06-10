package org.example;

import org.example.Models.Task;
import org.example.Utils.TaskStatus;
import org.example.Utils.TaskManager;
import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args)
    {
        Task task1 = new Task(1,"Ana ghadi ldar", TaskStatus.TODO, LocalDate.of(2015,11,20), LocalDate.of(2025,5,10));
        TaskManager.addTask(task1);

        Task task2 = new Task(
                42,
                "Complete the project report",
                TaskStatus.IN_PROGRESS,
                LocalDate.of(2024, 3, 15),
                LocalDate.of(2024, 4, 1)
        );

        TaskManager.addTask(task2);

    }
}