package com.example.electricpower.entity.to.register;

public class RegisterReceived {

    /**
     * message : success
     * result : {"id":"290945805093822464","gmtCreator":"290945805093822464","gmtCreate":1561520110297,"gmtModifier":null,"gmtModified":null,"account":null,"salt":"yXRRBcXJiNEqVkONsbjQ","password":"42E5EC3DEF9DA157DF1DC5FB119FDB87","nick":null,"role":"USER","brithday":null,"avatar":"http://localhost:9981/upload/avatar/984ab0e6f4ce48b523303dd11f8f7bcf.png","email":null,"emailCertified":1,"mobile":"13775452153","mobileCertified":1,"company":null,"sex":0,"address":null,"descript":null,"sort":null,"isEnabled":1,"loginCount":0}
     * status : 200
     * timestamp : 1561520110342
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
         * id : 290945805093822464
         * gmtCreator : 290945805093822464
         * gmtCreate : 1561520110297
         * gmtModifier : null
         * gmtModified : null
         * account : null
         * salt : yXRRBcXJiNEqVkONsbjQ
         * password : 42E5EC3DEF9DA157DF1DC5FB119FDB87
         * nick : null
         * role : USER
         * brithday : null
         * avatar : http://localhost:9981/upload/avatar/984ab0e6f4ce48b523303dd11f8f7bcf.png
         * email : null
         * emailCertified : 1
         * mobile : 13775452153
         * mobileCertified : 1
         * company : null
         * sex : 0
         * address : null
         * descript : null
         * sort : null
         * isEnabled : 1
         * loginCount : 0
         */

        private String id;
        private String gmtCreator;
        private long gmtCreate;
        private Object gmtModifier;
        private Object gmtModified;
        private Object account;
        private String salt;
        private String password;
        private Object nick;
        private String role;
        private Object brithday;
        private String avatar;
        private Object email;
        private int emailCertified;
        private String mobile;
        private int mobileCertified;
        private Object company;
        private int sex;
        private Object address;
        private Object descript;
        private Object sort;
        private int isEnabled;
        private int loginCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGmtCreator() {
            return gmtCreator;
        }

        public void setGmtCreator(String gmtCreator) {
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

        public Object getAccount() {
            return account;
        }

        public void setAccount(Object account) {
            this.account = account;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getNick() {
            return nick;
        }

        public void setNick(Object nick) {
            this.nick = nick;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Object getBrithday() {
            return brithday;
        }

        public void setBrithday(Object brithday) {
            this.brithday = brithday;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public int getEmailCertified() {
            return emailCertified;
        }

        public void setEmailCertified(int emailCertified) {
            this.emailCertified = emailCertified;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMobileCertified() {
            return mobileCertified;
        }

        public void setMobileCertified(int mobileCertified) {
            this.mobileCertified = mobileCertified;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getDescript() {
            return descript;
        }

        public void setDescript(Object descript) {
            this.descript = descript;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public int getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(int isEnabled) {
            this.isEnabled = isEnabled;
        }

        public int getLoginCount() {
            return loginCount;
        }

        public void setLoginCount(int loginCount) {
            this.loginCount = loginCount;
        }
    }
}
