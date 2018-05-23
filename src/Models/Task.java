package Models;

import jdk.jfr.Timestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Task {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long ID;
    private String name;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date deadline;

    @OneToOne
    @JoinColumn(name = "Priority_ID")
    private Priority priority;

    public Task(){ }

    public Task(String name, String description, Date deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                '}';
    }
}
