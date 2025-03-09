package com.taskmanager.service;

import com.taskmanager.db.DatabaseManager;
import com.taskmanager.model.Task;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;

public class TaskService {
    private DatabaseManager dbManager;
    private Scanner scanner;

    public TaskService(Scanner scanner) {
        this.scanner = scanner;
        this.dbManager = new DatabaseManager();
    }

    public void run() {
        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Show Tasks");
            System.out.println("3. Edit Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("\nChoose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: addTask(); break;
                case 2: showTasks(); break;
                case 3: editTask(); break;
                case 4: deleteTask(); break;
                case 5: System.out.print("\nBye!"); System.exit(0);
                default: System.out.println("Invalid option!");
            }
        }
    }

    public void addTask() {
    	
    	LocalDate dueDate = null;
    	boolean correct = false;
    	
        System.out.print("\nEnter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");        
        String description = scanner.nextLine();

        System.out.print("Enter due date (YYYY-MM-DD): ");   
        while (!correct) {
        	try {
            	dueDate = LocalDate.parse(scanner.nextLine());
            	correct = true;
            	
            } catch (java.time.format.DateTimeParseException e) {
            	
            	System.out.print("\nEnter due date once again (YYYY-MM-DD): ");
            }
        }
        
        System.out.print("Enter priority (1-3): ");
        int priority = Integer.parseInt(scanner.nextLine());       
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        Task task = new Task(0, title, description, dueDate, priority, category);
        try {
            dbManager.addTask(task);
            System.out.println("\nTask added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public void showTasks() {
        try {
        	List<Task> tasks = dbManager.getAllTasks();
        	if (tasks.isEmpty()) {
        		System.out.println("\nYou have no tasks!");
        	}
        	
            System.out.println("\nAll tasks:");
            for (Task task : dbManager.getAllTasks()) {
                System.out.println(task);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
    }

    public void editTask() {
        System.out.print("Enter task ID to edit: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            Task taskToEdit = dbManager.getTaskById(id);
            if (taskToEdit == null) {
                System.out.println("Task not found!");
                return;
            }

            System.out.println("Current task: " + taskToEdit);
            System.out.print("New title (Enter to skip): ");
            String title = scanner.nextLine();
            if (!title.isEmpty()) taskToEdit.setTitle(title);

            System.out.print("New description (Enter to skip): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) taskToEdit.setDescription(description);

            System.out.print("New due date (YYYY-MM-DD, Enter to skip): ");
            String dueDateStr = scanner.nextLine();
            if (!dueDateStr.isEmpty()) taskToEdit.setDueDate(LocalDate.parse(dueDateStr));

            System.out.print("New priority (1-3, Enter to skip): ");
            String priorityStr = scanner.nextLine();
            if (!priorityStr.isEmpty()) taskToEdit.setPriority(Integer.parseInt(priorityStr));

            dbManager.updateTask(taskToEdit);
            System.out.println("Task updated: " + taskToEdit);
        } catch (SQLException e) {
            System.out.println("Error editing task: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid priority format!");
        }
    }

    public void deleteTask() {
        System.out.print("\nEnter task ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            dbManager.deleteTask(id);
            System.out.println("\nTask deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting task: " + e.getMessage());
        }
    }
}