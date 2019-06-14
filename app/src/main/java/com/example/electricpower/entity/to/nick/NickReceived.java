package com.example.electricpower.entity.to.nick;

public class NickReceived {
    /**
     * message : success
     * result : {"id":"285961738371850240","gmtCreator":"285961738371850240","gmtCreate":1560331816000,"gmtModifier":null,"gmtModified":null,"account":"123456","salt":"CWiU4X7xDb39UvdGAfXj","password":"8B1D0524A268F26BECC477DBC9A9C4E6","nick":"小红","role":"USER","brithday":null,"avatar":null,"email":null,"emailCertified":1,"mobile":"17701540105","mobileCertified":1,"company":null,"sex":0,"address":null,"descript":null,"sort":null,"isEnabled":1,"loginCount":3}
     * status : 200
     * timestamp : 1560503588280
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
         * id : 285961738371850240
         * gmtCreator : 285961738371850240
         * gmtCreate : 1560331816000
         * gmtModifier : null
         * gmtModified : null
         * account : 123456
         * salt : CWiU4X7xDb39UvdGAfXj
         * password : 8B1D0524A268F26BECC477DBC9A9C4E6
         * nick : 小红
         * role : USER
         * brithday : null
         * avatar : null
         * email : null
         * emailCertified : 1
         * mobile : 17701540105
         * mobileCertified : 1
         * company : null
         * sex : 0
         * address : null
         * descript : null
         * sort : null
         * isEnabled : 1
         * loginCount : 3
         */

        private String id;
        private String gmtCreator;
        private long gmtCreate;
        private Object gmtModifier;
        private Object gmtModified;
        private String account;
        private String salt;
        private String password;
        private String nick;
        private String role;
        private Object brithday;
        private Object avatar;
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

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
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

        public Object getBrithday() {
            return brithday;
        }

        public void setBrithday(Object brithday) {
            this.brithday = brithday;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
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
