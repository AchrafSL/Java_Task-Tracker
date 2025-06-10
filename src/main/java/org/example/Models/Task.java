package org.example.Models;

import org.example.Utils.TaskStatus;

import java.time.LocalDate;

public class Task {
    Integer id;
    String description;
    TaskStatus status;
    LocalDate createdAt,updatedAt;

    public Task(Integer id, String description, TaskStatus status, LocalDate createdAt, LocalDate updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String toJsonElem()
    {
        return "{" +
                "\"id\":" + id + "," +
                "\"description\":\"" + description + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"createdAt\":\"" + createdAt + "\"," +
                "\"updatedAt\":\"" + updatedAt + "\"" +
                "}";
    }


    public static Task toTaskElem(String jsonElem)
    {
        String[] elemts = jsonElem.split(",");
        String[] taskE = new String[5];
        for(int i = 0; i < 5;i++)
        {

          taskE[i] = elemts[i].split(":",2)[1]  ;
          System.out.println(taskE[i]);

        }
        taskE[4] = taskE[4].replace("}","");


       return new Task(Integer.parseInt(taskE[0]),taskE[1].replace("\"",""), TaskStatus.valueOf(taskE[2].replace("\"", "") )  , LocalDate.parse(taskE[3].replace("\"", "")),LocalDate.parse(taskE[4].replace("\"", ""))  );

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
