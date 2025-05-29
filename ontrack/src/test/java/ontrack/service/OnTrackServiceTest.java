package ontrack.service;

import ontrack.model.Task;
import org.junit.Assert;
import org.junit.Test;
// User Story: As a student, I want to view all tasks I have submitted so that I can track my progress.
// User Story: As an instructor, I want to retrieve the details of a specific submitted task so that I can review it.
// [Screenshot - Tests failing before implementation]
// [Screenshot - All tests passing after implementation]

public class OnTrackServiceTest {

    @Test
    public void testGetSubmittedTasks_existingStudent() {
        // Student S1001 has submitted tasks 101 and 102
        Assert.assertEquals(
            java.util.Arrays.asList(101, 102),
            OnTrackService.getSubmittedTasks("S1001")
        );
    }

    @Test
    public void testGetSubmittedTasks_noSubmissions() {
        // Student with no submissions (or non-existent student) should return an empty list
        Assert.assertTrue(OnTrackService.getSubmittedTasks("S9999").isEmpty());
    }

    @Test
    public void testGetTaskDetails_validId() {
        // Task 101 details should match the expected title and details
        Task task = OnTrackService.getTaskDetails(101);
        Assert.assertEquals(101, task.getId());
        Assert.assertEquals("Assignment 1", task.getTitle());
        Assert.assertEquals("Submitted on 2025-04-01", task.getDetails());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testGetTaskDetails_invalidId() {
        // Requesting details for a non-existent task should throw an exception
        OnTrackService.getTaskDetails(999);
    }
}
