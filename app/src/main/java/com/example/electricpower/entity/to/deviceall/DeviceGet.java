package com.example.electricpower.entity.to.deviceall;

import java.util.List;

public class DeviceGet {

    /**
     * message : success
     * result : [{"deviceId":"288818882557370368","deviceName":"设备1","deviceType":"0","aLiYunDeviceState":"UNACTIVE","nodeList":[{"id":"288818882926469120","gmtCreator":null,"gmtCreate":1561013012000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001289","name":"湿度","nodeCode":"0001","bytenum":"2","word":"1","unit":"1","unitNameType":-1,"unitName":"%","dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null},{"id":"288818883287179264","gmtCreator":null,"gmtCreate":1561013013000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001288","name":"温度","nodeCode":"0000","bytenum":"2","word":"1","unit":"1","unitNameType":0,"unitName":null,"dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null}],"storeData":[{"devId":"149","nodeId":"149000001289","lastData":13543.606399999982,"newData":13544.581299999982,"diffData":0.974899999999252,"data":null,"dateStr":"2019-06-21 10:43:02"},{"devId":"149","nodeId":"149000001288","lastData":13558.537799999844,"newData":13558.796999999844,"diffData":0.2592000000004191,"data":null,"dateStr":"2019-06-21 10:43:02"}]},{"deviceId":"288836221503922176","deviceName":"朗威设备","deviceType":"0","aLiYunDeviceState":"UNACTIVE","nodeList":[{"id":"288836222166622208","gmtCreator":null,"gmtCreate":1561017146000,"gmtModifier":null,"gmtModified":null,"devId":"288836221503922176","sdevId":"168","sdevNodeid":"168000001304","name":"湿度","nodeCode":"0001","bytenum":"2","word":"1","unit":"0.01","unitNameType":-1,"unitName":"%","dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null},{"id":"288836222548303872","gmtCreator":null,"gmtCreate":1561017147000,"gmtModifier":null,"gmtModified":null,"devId":"288836221503922176","sdevId":"168","sdevNodeid":"168000001303","name":"温度","nodeCode":"0000","bytenum":"2","word":"1","unit":"0.01","unitNameType":0,"unitName":null,"dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null}],"storeData":[null,null]},{"deviceId":"288836846388109312","deviceName":"朗威设备2","deviceType":"0","aLiYunDeviceState":"UNACTIVE","nodeList":[{"id":"288836846941757440","gmtCreator":null,"gmtCreate":1561017295000,"gmtModifier":null,"gmtModified":null,"devId":"288836846388109312","sdevId":"169","sdevNodeid":"169000001304","name":"湿度","nodeCode":"0001","bytenum":"2","word":"1","unit":"0.01","unitNameType":-1,"unitName":"%","dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null},{"id":"288836847507988480","gmtCreator":null,"gmtCreate":1561017296000,"gmtModifier":null,"gmtModified":null,"devId":"288836846388109312","sdevId":"169","sdevNodeid":"169000001303","name":"温度","nodeCode":"0000","bytenum":"2","word":"1","unit":"0.01","unitNameType":0,"unitName":null,"dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null}],"storeData":[null,null]}]
     * status : 200
     * timestamp : 1561084982353
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
         * deviceId : 288818882557370368
         * deviceName : 设备1
         * deviceType : 0
         * aLiYunDeviceState : UNACTIVE
         * nodeList : [{"id":"288818882926469120","gmtCreator":null,"gmtCreate":1561013012000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001289","name":"湿度","nodeCode":"0001","bytenum":"2","word":"1","unit":"1","unitNameType":-1,"unitName":"%","dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null},{"id":"288818883287179264","gmtCreator":null,"gmtCreate":1561013013000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001288","name":"温度","nodeCode":"0000","bytenum":"2","word":"1","unit":"1","unitNameType":0,"unitName":null,"dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null}]
         * storeData : [{"devId":"149","nodeId":"149000001289","lastData":13543.606399999982,"newData":13544.581299999982,"diffData":0.974899999999252,"data":null,"dateStr":"2019-06-21 10:43:02"},{"devId":"149","nodeId":"149000001288","lastData":13558.537799999844,"newData":13558.796999999844,"diffData":0.2592000000004191,"data":null,"dateStr":"2019-06-21 10:43:02"}]
         */

        private String deviceId;
        private String deviceName;
        private String deviceType;
        private String aLiYunDeviceState;
        private List<NodeListBean> nodeList;
        private List<StoreDataBean> storeData;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getALiYunDeviceState() {
            return aLiYunDeviceState;
        }

        public void setALiYunDeviceState(String aLiYunDeviceState) {
            this.aLiYunDeviceState = aLiYunDeviceState;
        }

        public List<NodeListBean> getNodeList() {
            return nodeList;
        }

        public void setNodeList(List<NodeListBean> nodeList) {
            this.nodeList = nodeList;
        }

        public List<StoreDataBean> getStoreData() {
            return storeData;
        }

        public void setStoreData(List<StoreDataBean> storeData) {
            this.storeData = storeData;
        }

        public static class NodeListBean {
            /**
             * id : 288818882926469120
             * gmtCreator : null
             * gmtCreate : 1561013012000
             * gmtModifier : null
             * gmtModified : null
             * devId : 288818882557370368
             * sdevId : 149
             * sdevNodeid : 149000001289
             * name : 湿度
             * nodeCode : 0001
             * bytenum : 2
             * word : 1
             * unit : 1
             * unitNameType : -1
             * unitName : %
             * dataMax : 0
             * dataMin : 0
             * alarm : false
             * alamType : 0
             * alarmRate : 0
             * precis : 2
             * descript : null
             */

            private String id;
            private Object gmtCreator;
            private long gmtCreate;
            private Object gmtModifier;
            private Object gmtModified;
            private String devId;
            private String sdevId;
            private String sdevNodeid;
            private String name;
            private String nodeCode;
            private String bytenum;
            private String word;
            private String unit;
            private int unitNameType;
            private String unitName;
            private int dataMax;
            private int dataMin;
            private boolean alarm;
            private int alamType;
            private int alarmRate;
            private int precis;
            private Object descript;

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

            public String getDevId() {
                return devId;
            }

            public void setDevId(String devId) {
                this.devId = devId;
            }

            public String getSdevId() {
                return sdevId;
            }

            public void setSdevId(String sdevId) {
                this.sdevId = sdevId;
            }

            public String getSdevNodeid() {
                return sdevNodeid;
            }

            public void setSdevNodeid(String sdevNodeid) {
                this.sdevNodeid = sdevNodeid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNodeCode() {
                return nodeCode;
            }

            public void setNodeCode(String nodeCode) {
                this.nodeCode = nodeCode;
            }

            public String getBytenum() {
                return bytenum;
            }

            public void setBytenum(String bytenum) {
                this.bytenum = bytenum;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getUnitNameType() {
                return unitNameType;
            }

            public void setUnitNameType(int unitNameType) {
                this.unitNameType = unitNameType;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public int getDataMax() {
                return dataMax;
            }

            public void setDataMax(int dataMax) {
                this.dataMax = dataMax;
            }

            public int getDataMin() {
                return dataMin;
            }

            public void setDataMin(int dataMin) {
                this.dataMin = dataMin;
            }

            public boolean isAlarm() {
                return alarm;
            }

            public void setAlarm(boolean alarm) {
                this.alarm = alarm;
            }

            public int getAlamType() {
                return alamType;
            }

            public void setAlamType(int alamType) {
                this.alamType = alamType;
            }

            public int getAlarmRate() {
                return alarmRate;
            }

            public void setAlarmRate(int alarmRate) {
                this.alarmRate = alarmRate;
            }

            public int getPrecis() {
                return precis;
            }

            public void setPrecis(int precis) {
                this.precis = precis;
            }

            public Object getDescript() {
                return descript;
            }

            public void setDescript(Object descript) {
                this.descript = descript;
            }
        }

        public static class StoreDataBean {
            /**
             * devId : 149
             * nodeId : 149000001289
             * lastData : 13543.606399999982
             * newData : 13544.581299999982
             * diffData : 0.974899999999252
             * data : null
             * dateStr : 2019-06-21 10:43:02
             */

            private String devId;
            private String nodeId;
            private double lastData;
            private double newData;
            private double diffData;
            private Object data;
            private String dateStr;

            public String getDevId() {
                return devId;
            }

            public void setDevId(String devId) {
                this.devId = devId;
            }

            public String getNodeId() {
                return nodeId;
            }

            public void setNodeId(String nodeId) {
                this.nodeId = nodeId;
            }

            public double getLastData() {
                return lastData;
            }

            public void setLastData(double lastData) {
                this.lastData = lastData;
            }

            public double getNewData() {
                return newData;
            }

            public void setNewData(double newData) {
                this.newData = newData;
            }

            public double getDiffData() {
                return diffData;
            }

            public void setDiffData(double diffData) {
                this.diffData = diffData;
            }

            public Object getData() {
                return data;
            }

            public void setData(Object data) {
                this.data = data;
            }

            public String getDateStr() {
                return dateStr;
            }

            public void setDateStr(String dateStr) {
                this.dateStr = dateStr;
            }
        }
    }
}
