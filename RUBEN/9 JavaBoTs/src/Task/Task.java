/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task;

import java.io.Serializable;

/**
 *
 * @author sdist
 */

public class Task implements Serializable{
    
    private String taskId;
    private int requirementId;
    private int length;
    private int output;
    private String requirement;

    public Task(String taskId, int requirementId, int length, int output) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
        this.output = output;
        
        switch(requirementId){
            case 1: 
                this.requirement="BioInformatics";
                break;
            case 2:
                this.requirement="DataMining";
                break;
            case 3:
                this.requirement="ImageProcessing";
                break;
        }
    }

    public String getTaskId() {
        return taskId;
    }

    public int getRequirementId() {
        return requirementId;
    }

    public int getLength() {
        return length;
    }

    public int getOutput() {
        return output;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", requirementId=" + requirementId + ", length=" + length + ", output=" + output + ", requirement=" + requirement + '}';
    }
    
    
    
}
