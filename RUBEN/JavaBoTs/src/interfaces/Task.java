package interfaces;

import java.io.Serializable;

public class Task implements Serializable {

    private String taskId;
    private String requirementId;
    private int length;
    private String output;

    public Task(String taskId, String requirementId, int length, String output) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
        this.output = output;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public int getLength() {
        return length;
    }

    public String getOutput() {
        return output;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
