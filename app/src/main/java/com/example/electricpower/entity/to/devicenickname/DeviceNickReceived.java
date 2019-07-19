package com.example.electricpower.entity.to.devicenickname;

public class DeviceNickReceived {

    /**
     * message : success
     * result : {"deviceId":"389","devEUI":"9c65f9fffe7900D0","mac":"003100340031","deviceName":null}
     * status : 200
     * timestamp : 1563241369221
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
         * deviceId : 389
         * devEUI : 9c65f9fffe7900D0
         * mac : 003100340031
         * deviceName : null
         */

        private String deviceId;
        private String devEUI;
        private String mac;
        private String deviceName;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDevEUI() {
            return devEUI;
        }

        public void setDevEUI(String devEUI) {
            this.devEUI = devEUI;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }
    }
}
