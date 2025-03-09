package com.taskmanager;

import com.taskmanager.service.TaskService;
import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService(scanner);
        taskService.run();
    }
}