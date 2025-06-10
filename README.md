https://roadmap.sh/projects/task-tracker

javac MyApp.java
java MyApp add "By orange"



    public String toJsonElem()
    {
        return "{ " +
                "\"id\":" + id + "," +
                "\"description\":\"" + description + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"createdAt\":\"" + createdAt + "\"," +
                "\"updatedAt\":\"" + updatedAt + "\"" +
                " }";
    }


I Tried using OutputStreamWriter and InputStreamReader and because of that in making the add Task to replace the last 
char "]" with "," 
i can't do that because u dont have seek in it
so i replaced it by RandomAccessFile(fileName,Mode); Mode = "rw";

adding a task algorithm:
    if the file is empty 
        add "["
        add {task}
        add "]"

    else
        seek the end of the file
        replace "]" with ","
        add {task}
        add "]"

TaskManager Utils:
    toJson : transfer Task to String;
    toTask : tansfer String to Task;
