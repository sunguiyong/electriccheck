package com.example.electricpower.entity.to.wenshidu;

import java.util.List;

public class WenShiGet {

    /**
     * message : success
     * result : {"nodes":[{"id":"288818882926469120","gmtCreator":null,"gmtCreate":1561013012000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001289","name":"湿度","nodeCode":"0001","bytenum":"2","word":"1","unit":"1","unitNameType":-1,"unitName":"%","dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null},{"id":"288818883287179264","gmtCreator":null,"gmtCreate":1561013013000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001288","name":"温度","nodeCode":"0000","bytenum":"2","word":"1","unit":"1","unitNameType":0,"unitName":null,"dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null}],"event":[{"devId":"149","nodeId":"149000001289","lastData":1396.2412999999983,"newData":1396.4521999999984,"diffData":0.21090000000003783,"data":null,"dateStr":"2019-06-20 16:08:53"},{"devId":"149","nodeId":"149000001288","lastData":1377.9026999999949,"newData":1378.4564999999948,"diffData":0.5537999999999101,"data":null,"dateStr":"2019-06-20 16:08:53"}],"device":{"id":"288818882557370368","sdevId":"149","sdevTypeid":"103","scolId":"52","devName":"设备1","devCode":"249f0fb55abb4ae1a9d9431eb48dde33","devAddr":1,"devType":0,"state":true,"lng":116.40443,"lat":39.9077,"location":null,"allowAlarm":false,"descript":null,"gmtCreate":1561013012000}}
     * status : 200
     * timestamp : 1561018133789
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
         * nodes : [{"id":"288818882926469120","gmtCreator":null,"gmtCreate":1561013012000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001289","name":"湿度","nodeCode":"0001","bytenum":"2","word":"1","unit":"1","unitNameType":-1,"unitName":"%","dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null},{"id":"288818883287179264","gmtCreator":null,"gmtCreate":1561013013000,"gmtModifier":null,"gmtModified":null,"devId":"288818882557370368","sdevId":"149","sdevNodeid":"149000001288","name":"温度","nodeCode":"0000","bytenum":"2","word":"1","unit":"1","unitNameType":0,"unitName":null,"dataMax":0,"dataMin":0,"alarm":false,"alamType":0,"alarmRate":0,"precis":2,"descript":null}]
         * event : [{"devId":"149","nodeId":"149000001289","lastData":1396.2412999999983,"newData":1396.4521999999984,"diffData":0.21090000000003783,"data":null,"dateStr":"2019-06-20 16:08:53"},{"devId":"149","nodeId":"149000001288","lastData":1377.9026999999949,"newData":1378.4564999999948,"diffData":0.5537999999999101,"data":null,"dateStr":"2019-06-20 16:08:53"}]
         * device : {"id":"288818882557370368","sdevId":"149","sdevTypeid":"103","scolId":"52","devName":"设备1","devCode":"249f0fb55abb4ae1a9d9431eb48dde33","devAddr":1,"devType":0,"state":true,"lng":116.40443,"lat":39.9077,"location":null,"allowAlarm":false,"descript":null,"gmtCreate":1561013012000}
         */

        private DeviceBean device;
        private List<NodesBean> nodes;
        private List<EventBean> event;

        public DeviceBean getDevice() {
            return device;
        }

        public void setDevice(DeviceBean device) {
            this.device = device;
        }

        public List<NodesBean> getNodes() {
            return nodes;
        }

        public void setNodes(List<NodesBean> nodes) {
            this.nodes = nodes;
        }

        public List<EventBean> getEvent() {
            return event;
        }

        public void setEvent(List<EventBean> event) {
            this.event = event;
        }

        public static class DeviceBean {
            /**
             * id : 288818882557370368
             * sdevId : 149
             * sdevTypeid : 103
             * scolId : 52
             * devName : 设备1
             * devCode : 249f0fb55abb4ae1a9d9431eb48dde33
             * devAddr : 1
             * devType : 0
             * state : true
             * lng : 116.40443
             * lat : 39.9077
             * location : null
             * allowAlarm : false
             * descript : null
             * gmtCreate : 1561013012000
             */

            private String id;
            private String sdevId;
            private String sdevTypeid;
            private String scolId;
            private String devName;
            private String devCode;
            private int devAddr;
            private int devType;
            private boolean state;
            private double lng;
            private double lat;
            private Object location;
            private boolean allowAlarm;
            private Object descript;
            private long gmtCreate;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getSdevId() {
                return sdevId;
            }

            public void setSdevId(String sdevId) {
                this.sdevId = sdevId;
            }

            public String getSdevTypeid() {
                return sdevTypeid;
            }

            public void setSdevTypeid(String sdevTypeid) {
                this.sdevTypeid = sdevTypeid;
            }

            public String getScolId() {
                return scolId;
            }

            public void setScolId(String scolId) {
                this.scolId = scolId;
            }

            public String getDevName() {
                return devName;
            }

            public void setDevName(String devName) {
                this.devName = devName;
            }

            public String getDevCode() {
                return devCode;
            }

            public void setDevCode(String devCode) {
                this.devCode = devCode;
            }

            public int getDevAddr() {
                return devAddr;
            }

            public void setDevAddr(int devAddr) {
                this.devAddr = devAddr;
            }

            public int getDevType() {
                return devType;
            }

            public void setDevType(int devType) {
                this.devType = devType;
            }

            public boolean isState() {
                return state;
            }

            public void setState(boolean state) {
                this.state = state;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public boolean isAllowAlarm() {
                return allowAlarm;
            }

            public void setAllowAlarm(boolean allowAlarm) {
                this.allowAlarm = allowAlarm;
            }

            public Object getDescript() {
                return descript;
            }

            public void setDescript(Object descript) {
                this.descript = descript;
            }

            public long getGmtCreate() {
                return gmtCreate;
            }

            public void setGmtCreate(long gmtCreate) {
                this.gmtCreate = gmtCreate;
            }
        }

        public static class NodesBean {
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

        public static class EventBean {
            /**
             * devId : 149
             * nodeId : 149000001289
             * lastData : 1396.2412999999983
             * newData : 1396.4521999999984
             * diffData : 0.21090000000003783
             * data : null
             * dateStr : 2019-06-20 16:08:53
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
