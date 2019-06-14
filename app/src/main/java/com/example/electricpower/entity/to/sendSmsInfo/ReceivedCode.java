package com.example.electricpower.entity.to.sendSmsInfo;

public class ReceivedCode {

    /**
     * message : string
     * result : {}
     * status : 0
     * timestamp : 0
     */

    private String message;
    private ResultBean result;
    private int status;
    private int timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
    }
}
