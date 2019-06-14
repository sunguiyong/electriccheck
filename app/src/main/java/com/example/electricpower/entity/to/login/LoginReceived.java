package com.example.electricpower.entity.to.login;

public class LoginReceived {

    /**
     * message : success
     * result : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiIxNzcwMTU0MDEwNSIsImlkIjoiMjg1OTYxNzM4MzcxODUwMjQwIiwiZXhwIjoxNTYwNTcwMDk4fQ.mwLU0EFl-2mtyaikKC7Ly1xX60BvIHTeTd3Z_iuwmlo","refresh":"8D95AFF78F3C06F522EC42E0B6FF0CD9","expire":1560570098125,"nick":"666","role":"USER"}
     * status : 200
     * timestamp : 1560483698126
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
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiIxNzcwMTU0MDEwNSIsImlkIjoiMjg1OTYxNzM4MzcxODUwMjQwIiwiZXhwIjoxNTYwNTcwMDk4fQ.mwLU0EFl-2mtyaikKC7Ly1xX60BvIHTeTd3Z_iuwmlo
         * refresh : 8D95AFF78F3C06F522EC42E0B6FF0CD9
         * expire : 1560570098125
         * nick : 666
         * role : USER
         */

        private String token;
        private String refresh;
        private long expire;
        private String nick;
        private String role;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRefresh() {
            return refresh;
        }

        public void setRefresh(String refresh) {
            this.refresh = refresh;
        }

        public long getExpire() {
            return expire;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
