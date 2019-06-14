package com.example.electricpower.entity.to.changepsw;

public class ChangepswPost {

    /**
     * oldpassword : 123456
     * newpassword : 111222
     */

    private String oldpassword;
    private String newpassword;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
