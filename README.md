# Task Manager
Task Manager is a Java application using MySQL to manage tasks. Allows you to add, edit, remove and view tasks with support for categories, priorities and completion dates.

## Functional
- Adding tasks with name, description, category, priority and completion time.
- View the task list with a notification if no tasks.
- Edit and delete tasks.
- Error handling when entering a date.

## Technologies
- Java
- MySQL
- Maven
- Eclipse

## Installation
1. Install MySQL and create the database 'task_manager'.
   CREATE DATABASE task_manager;

    USE task_manager;

    CREATE TABLE tasks (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255),
        description TEXT,
        due_date DATE,
        priority INT,
        category VARCHAR(50),
        creation_date DATE
    );


3. Configure the connection to the DatabaseManager.java (specify your MySQL password).
4. Import the project into Eclipse as a Maven project.
5. Run 'Main.java'.

## Use
- Select option 1 to add a task.
- Select option 2 to view the tasks.
- Use the options 3 and 4 to edit or delete.
