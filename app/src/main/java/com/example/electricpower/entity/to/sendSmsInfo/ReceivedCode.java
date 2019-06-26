package com.example.electricpower.entity.to.sendSmsInfo;

public class ReceivedCode {

    /**
     * message : success
     * result : 30B0BD2D13BE404788A949DD3703E69C
     * status : 200
     * timestamp : 1561520773420
     */

    private String message;
    private String result;
    private int status;
    private String timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
