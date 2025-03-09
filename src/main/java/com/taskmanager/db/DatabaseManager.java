package com.taskmanager.db;

import com.taskmanager.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            String url = "jdbc:mysql://localhost:3306/task_manager";
            String username = "root";
            String password = ""; //Enter your Password
            connection = DriverManager.getConnection(url, username, password);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255)," +
                "description TEXT," +
                "due_date DATE," +
                "priority INT," +
                "category VARCHAR(50)," +
                "creation_date DATE)";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }

    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO tasks (title, description, due_date, priority, category, creation_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, task.getTitle());
        pstmt.setString(2, task.getDescription());
        pstmt.setDate(3, Date.valueOf(task.getDueDate()));
        pstmt.setInt(4, task.getPriority());
        pstmt.setString(5, task.getCategory());
        pstmt.setDate(6, Date.valueOf(task.getCreationDate()));
        pstmt.executeUpdate();
    }

    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE tasks SET title = ?, description = ?, due_date = ?, priority = ?, category = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, task.getTitle());
        pstmt.setString(2, task.getDescription());
        pstmt.setDate(3, Date.valueOf(task.getDueDate()));
        pstmt.setInt(4, task.getPriority());
        pstmt.setString(5, task.getCategory());
        pstmt.setInt(6, task.getId());
        pstmt.executeUpdate();
    }

    public void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public Task getTaskById(int id) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("due_date").toLocalDate(),
                rs.getInt("priority"),
                rs.getString("category")
            );
        }
        return null;
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            tasks.add(new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getDate("due_date").toLocalDate(),
                rs.getInt("priority"),
                rs.getString("category")
            ));
        }
        return tasks;
    }
}

