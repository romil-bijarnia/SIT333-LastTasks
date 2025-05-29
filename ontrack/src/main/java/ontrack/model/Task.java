package ontrack.model;

public class Task {
    private int id;
    private String title;
    private String details;

    public Task(int id, String title, String details) {
        this.id = id;
        this.title = title;
        this.details = details;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title='" + title + "', details='" + details + "'}";
    }
}
