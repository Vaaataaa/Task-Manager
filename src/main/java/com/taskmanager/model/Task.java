package com.taskmanager.model;

import java.time.LocalDate;

public class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private int priority;
    private String category;
    private LocalDate creationDate;

    public Task(int id, String title, String description, LocalDate dueDate, int priority, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.creationDate = LocalDate.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public int getPriority() { return priority; }
    public String getCategory() { return category; }
    public LocalDate getCreationDate() { return creationDate; }
    
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setCategory(String category) {this.category = category;}
    public void setPriority(int priority) { this.priority = priority; }

    public String toString() {
        return String.format("ID: %d | %s | %s | Due: %s | Priority: %d | %s | Created: %s",
                id, title, description, dueDate, priority, category, creationDate);
    }
}