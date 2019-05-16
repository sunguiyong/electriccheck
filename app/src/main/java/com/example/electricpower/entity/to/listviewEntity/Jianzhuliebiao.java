package com.example.electricpower.entity.to.listviewEntity;

public class Jianzhuliebiao {
    private String addr;
    private int shebeiCount;
    private int zaixianCount;

    public Jianzhuliebiao(String addr, int shebeiCount, int zaixianCount) {
        this.addr = addr;
        this.shebeiCount = shebeiCount;
        this.zaixianCount = zaixianCount;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getShebeiCount() {
        return shebeiCount;
    }

    public void setShebeiCount(int shebeiCount) {
        this.shebeiCount = shebeiCount;
    }

    public int getZaixianCount() {
        return zaixianCount;
    }

    public void setZaixianCount(int zaixianCount) {
        this.zaixianCount = zaixianCount;
    }
}
