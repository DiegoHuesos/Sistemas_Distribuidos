
package mx.itam.packages.rmi.serializableobjects;

import java.util.Objects;

public class Task {

    private String taskId;
    private String requirementId;
    private int length;


    public Task(){

    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    public Task(String taskId, String requirementId, int length) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return length == task.length && Objects.equals(taskId, task.taskId) && Objects.equals(requirementId, task.requirementId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, requirementId, length);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", requirementId='" + requirementId + '\'' +
                ", length=" + length +
                '}';
    }
}
