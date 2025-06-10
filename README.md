# Task Tracker Project
> **Note:** To do this project, you can go to [https://roadmap.sh/projects/task-tracker](https://roadmap.sh/projects/task-tracker).

## Overview

A Java-based command line interface (CLI) task tracker that helps you manage your tasks efficiently. The project demonstrates practical implementation of file handling, JSON manipulation, and command-line argument processing.

## Requirements

The application runs from the command line and stores tasks in a JSON file. Features include:

- Add, Update, and Delete tasks
- Mark tasks as in progress or done
- List all tasks
- List tasks by status (done, todo, in-progress)


## Constraints
- You can use any programming language to build this project.
- Use positional arguments in command line to accept user inputs.
- Use a JSON file to store the tasks in the current directory.
- The JSON file should be created if it does not exist.
- Use the native file system module of your programming language to interact with the JSON file.
- Do not use any external libraries or frameworks to build this project.
- Ensure to handle errors and edge cases gracefully.

## Implementation Details

### Core Components

1. **Task Class**
   - Properties: id, description, status, createdAt, updatedAt
   - Uses Java's LocalDate for date handling
   - Custom JSON serialization/deserialization methods

2. **TaskManager Class**
   - Handles all file operations using RandomAccessFile
   - Implements CRUD operations for tasks
   - Uses regex for JSON parsing
   - Stream API for task filtering and manipulation

### File Operations

1. **Reading Tasks**
   - Uses RandomAccessFile in read mode
   - Reads entire file content as bytes
   - Parses JSON objects using regex pattern matching
   - Converts JSON strings to Task objects

2. **Writing Tasks**
   - Uses RandomAccessFile in read-write mode
   - Maintains JSON array structure
   - Special handling for empty files
   - Efficient append operations

3. **Updating Tasks**
   - Reads all tasks into memory
   - Updates specific task
   - Rewrites entire file with updated content

## Example Commands

```bash
# Adding a new task
task-cli add "Buy groceries"

# Updating a task
task-cli update 1 "Buy groceries and cook dinner"

# Deleting a task
task-cli delete 1

# Marking task status
task-cli mark-in-progress 1
task-cli mark-done 1

# Listing tasks
task-cli list
task-cli list done
task-cli list todo
task-cli list in-progress
```

## Technical Implementation

### Task Structure
```java
public class Task {
    Integer id;
    String description;
    TaskStatus status;
    LocalDate createdAt, updatedAt;
    // Methods for JSON conversion and property access
}
```

### File Handling Strategy
1. **Adding Tasks**:
   ```java
   RandomAccessFile file = new RandomAccessFile(fileName, "rw");
   if(file.read() == -1) {
       file.write("[".getBytes());
       file.write(taskJson.getBytes());
       file.write("]".getBytes());
   } else {
       file.seek(file.length() - 1);
       file.write(",".getBytes());
       file.write(taskJson.getBytes());
       file.write("]".getBytes());
   }
   ```

2. **Task Management**:
   - Uses ArrayList for in-memory task manipulation
   - Stream API for filtering and updating tasks
   - Regular expressions for parsing JSON elements

### Error Handling
- Input validation for command-line arguments
- File operation error handling with try-catch blocks
- Task existence verification before updates