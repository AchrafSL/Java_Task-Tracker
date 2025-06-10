package org.example;

import org.example.Models.Task;
import org.example.Utils.TaskStatus;
import org.example.Utils.TaskManager;
import java.time.LocalDate;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static boolean isNumber(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public static void main(String[] args)
    {
        if (0 == args.length || isNumber(args[0]) )
        {
            System.out.println("Unknown command. Use 'task-cli help' for usage.");
            return;
        }

        switch (args[0].toLowerCase()){

            case "add":
                if (args.length != 2) {
                    System.out.println("Usage: task-cli add \"Task description\"");
                    return;
                }
                TaskManager.addTask(args[1]);
                break;

            case "update":
                if (args.length != 3 || !isNumber(args[1])) {
                    System.out.println("Usage: task-cli update <id> \"New description\"");
                    return;
                }
                TaskManager.updateTask(args[2],Integer.parseInt(args[1]));
                break;

            case "delete":
                if (args.length != 2 || !isNumber(args[1])) {
                    System.out.println("Usage: task-cli delete <id>");
                    return;
                }
                TaskManager.deleteTask(Integer.parseInt(args[1]));
                break;

            case "list":
                if (args.length == 1)
                TaskManager.listAllTasks();
                else if (args.length == 2 && ( args[1].equalsIgnoreCase("done") || args[1].equalsIgnoreCase("todo") || args[1].equalsIgnoreCase("in-progress") ) )
                {
                    TaskStatus ele = args[1].equalsIgnoreCase("in-progress")?TaskStatus.IN_PROGRESS:TaskStatus.valueOf(args[1].toUpperCase());
                    TaskManager.listAllTasks(ele);

                }
                else
                    System.out.println("Usage: task-cli list {for general listing}\n{for more specific listing} task-cli list [done|todo|in-progress]");
                break;

            case "mark-in-progress":
                if(args.length != 2 ||  !isNumber(args[1]))
                {
                    System.out.println("Usage: task-cli mark-in-progress <id>");
                    return;
                }
                TaskManager.mark(TaskStatus.IN_PROGRESS,Integer.parseInt(args[1]));
                break;


            case "mark-done":
                if(args.length != 2 || !isNumber(args[1]))
                {
                    System.out.println("Usage: task-cli mark-done <id>");
                    return;
                }
                TaskManager.mark(TaskStatus.DONE,Integer.parseInt(args[1]));
                break;



            case "help":
                System.out.println("Usage:");
                System.out.println("  task-cli add \"Task description\"");
                System.out.println("  task-cli update <id> \"New description\"");
                System.out.println("  task-cli delete <id>");
                System.out.println("  task-cli list [done|todo|in-progress]");
                System.out.println("  task-cli mark-in-progress <id>");
                System.out.println("  task-cli mark-done <id>");
                break;


            default:
                System.out.println("Unknown command. Use 'task-cli help' for usage.");






        }

    }
}