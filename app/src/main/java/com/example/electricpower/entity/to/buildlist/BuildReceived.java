package com.example.electricpower.entity.to.buildlist;

import java.util.List;

public class BuildReceived {

    /**
     * message : success
     * result : [{"id":"277971724375601152","gmtCreator":null,"gmtCreate":null,"gmtModifier":null,"gmtModified":null,"buildName":"苏州火车站北","deviceCount":0,"onLineCount":0},{"id":"277971749583368192","gmtCreator":null,"gmtCreate":null,"gmtModifier":null,"gmtModified":null,"buildName":"苏州火车站南","deviceCount":0,"onLineCount":0}]
     * status : 200
     * timestamp : 1560492041500
     */

    private String message;
    private int status;
    private String timestamp;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 277971724375601152
         * gmtCreator : null
         * gmtCreate : null
         * gmtModifier : null
         * gmtModified : null
         * buildName : 苏州火车站北
         * deviceCount : 0
         * onLineCount : 0
         */

        private String id;
        private Object gmtCreator;
        private Object gmtCreate;
        private Object gmtModifier;
        private Object gmtModified;
        private String buildName;
        private int deviceCount;
        private int onLineCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getGmtCreator() {
            return gmtCreator;
        }

        public void setGmtCreator(Object gmtCreator) {
            this.gmtCreator = gmtCreator;
        }

        public Object getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Object gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Object getGmtModifier() {
            return gmtModifier;
        }

        public void setGmtModifier(Object gmtModifier) {
            this.gmtModifier = gmtModifier;
        }

        public Object getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Object gmtModified) {
            this.gmtModified = gmtModified;
        }

        public String getBuildName() {
            return buildName;
        }

        public void setBuildName(String buildName) {
            this.buildName = buildName;
        }

        public int getDeviceCount() {
            return deviceCount;
        }

        public void setDeviceCount(int deviceCount) {
            this.deviceCount = deviceCount;
        }

        public int getOnLineCount() {
            return onLineCount;
        }

        public void setOnLineCount(int onLineCount) {
            this.onLineCount = onLineCount;
        }
    }
}
