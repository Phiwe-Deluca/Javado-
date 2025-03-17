import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private List<String> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    // iteration 1
    public void processMenuChoice(int choice) {
        if (choice < 1 || choice > 5) {
            throw new IllegalArgumentException("Invalid menu option!");
        }
    }

    public void addTask(String task) {
        if (tasks.contains(task)) {
            System.out.println("This task already exists.");
        } else {
            tasks.add(task);
            System.out.println("Task added!");
        }
    }

    public List<String> listTasks() {
        return tasks;
    }

    public void deleteTask(String task) {
        if (!tasks.contains(task)) {
            throw new IllegalArgumentException("Task not found!");
        }
        tasks.remove(task);
        System.out.println("Task '" + task + "' removed successfully!");
    }
//iteration 2
    public static void saveTasksToCSV(List<String> tasks, String csvFile) {
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.append("Tasks:\n");
            for (String task : tasks) {
                writer.append(task).append("\n");
            }
            writer.flush();
            System.out.println("Tasks saved to CSV!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to CSV.");
            e.printStackTrace();
        }
    }

    public void exit(Scanner scanner) {
        System.out.print("Are you sure you want to exit? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("Exiting Task Manager...");
            System.exit(0);
        } else {
            System.out.println("Exit canceled.");
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        //
        while (true) {
            System.out.println("\nTask Manager:");
            System.out.println("1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Save Tasks to CSV");
            System.out.println("5. Exit");
            System.out.print("Please choose an option (1,2,3,4,5): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                taskManager.processMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // iteration 4
            switch (choice) {
                case 1:
                    System.out.print("Enter your task: ");
                    String task = scanner.nextLine();
                    taskManager.addTask(task);
                    break;

                case 2:
                    System.out.println("\nTasks List:");
                    List<String> tasks = taskManager.listTasks();
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks added yet.");
                    } else {
                        for (String t : tasks) {
                            System.out.println("- " + t);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter the task to delete: ");
                    String taskToDelete = scanner.nextLine();
                    try {
                        taskManager.deleteTask(taskToDelete);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter the CSV file path: ");
                    String csvFile = scanner.nextLine();
                    saveTasksToCSV(taskManager.listTasks(), csvFile);
                    break;

                case 5:
                    taskManager.exit(scanner);
                    break;


}
