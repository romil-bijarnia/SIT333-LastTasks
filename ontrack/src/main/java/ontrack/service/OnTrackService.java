package ontrack.service;

import ontrack.model.Task;
import java.util.*;

public class OnTrackService {
    // Sample data: mapping from student ID to list of task IDs they submitted
    private static final Map<String, List<Integer>> submittedTasksMap = new HashMap<>();
    // Sample data: mapping from task ID to Task details
    private static final Map<Integer, Task> taskDetailsMap = new HashMap<>();

    static {
        // Initialize some dummy data for testing
        submittedTasksMap.put("S1001", Arrays.asList(101, 102));
        submittedTasksMap.put("S1002", Arrays.asList(102));
        // Task details (for tasks 101 and 102)
        taskDetailsMap.put(101, new Task(101, "Assignment 1", "Submitted on 2025-04-01"));
        taskDetailsMap.put(102, new Task(102, "Assignment 2", "Submitted on 2025-04-10"));
    }

    /**
     * Returns a list of task IDs that the given student has submitted.
     * If the student has no submissions or is not found, returns an empty list.
     */
    public static List<Integer> getSubmittedTasks(String studentId) {
        // Use an empty list as default if student not found
        return submittedTasksMap.getOrDefault(studentId, Collections.emptyList());
    }

    /**
     * Returns the Task details for the given task ID.
     * @throws NoSuchElementException if the task ID is not found.
     */
    public static Task getTaskDetails(int taskId) {
        if (!taskDetailsMap.containsKey(taskId)) {
            throw new NoSuchElementException("Task ID " + taskId + " not found");
        }
        return taskDetailsMap.get(taskId);
    }
}
