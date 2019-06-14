package com.example.electricpower.entity.to.feedback;

public class FeedBackReceived {

    /**
     * message : success
     * result : {"id":"286656968410652672","gmtCreator":null,"gmtCreate":1560497571793,"gmtModifier":null,"gmtModified":null,"info":"测试反馈信息01","mobile":"17701540105","userId":null}
     * status : 200
     * timestamp : 1560497571977
     */

    private String message;
    private ResultBean result;
    private int status;
    private String timestamp;

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        /**
         * id : 286656968410652672
         * gmtCreator : null
         * gmtCreate : 1560497571793
         * gmtModifier : null
         * gmtModified : null
         * info : 测试反馈信息01
         * mobile : 17701540105
         * userId : null
         */

        private String id;
        private Object gmtCreator;
        private long gmtCreate;
        private Object gmtModifier;
        private Object gmtModified;
        private String info;
        private String mobile;
        private Object userId;

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

        public long getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(long gmtCreate) {
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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }
    }
}
