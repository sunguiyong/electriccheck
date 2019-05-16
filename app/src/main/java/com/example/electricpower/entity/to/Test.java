package com.example.electricpower.entity.to;

import java.util.List;

public class Test {

    /**
     * total : 119
     * rows : [{"breakerid":null,"breakername":null,"breakertype":null,"breakerfirm":null,"product":{"name":"范总","id":106,"factoryName":"通讯产品","protocolID":1,"deviceType":"通讯","mobile":"18151119929","isValid":null,"productKey":null,"shouldBeChannels":null},"collector":{"id":54,"code":"000000000000001","name":"通讯网关","memo":"版本1.01","aLiYunDeviceId":null,"simcode":"12345678901234567890"},"manufacturer":{"id":15,"code":"scinan","name":"司南物联","iphone":"18151119929","address":"江苏苏州创业产业园","remark":null,"remark1":null,"remark2":null,"remark3":null,"factoryName":null,"i":null,"u":null,"rate":null,"electricEnergy":null,"userId":1,"factoryBgImg":null,"userDTO":null},"name":"通讯设备","id":153,"addr":1,"factoryID":15,"collectorID":54,"deviceTypeID":106,"creationDate":1557043946700,"lastEditDate":null,"deviceUserID":null,"i":null,"u":null,"rate":null,"deviceState":1,"deviceGuid":null,"deviceBZ":null,"code":"6330ac8871e64cadaa7db3e5c0e86804","electricEnergy":null,"deviceCreateAddress":null},{"breakerid":null,"breakername":null,"breakertype":null,"breakerfirm":null,"product":{"name":"范荣华","id":105,"factoryName":"类型int16设备","protocolID":1,"deviceType":"类型int16设备","mobile":"18151119929","isValid":null,"productKey":null,"shouldBeChannels":null},"collector":{"id":39,"code":"123456789012341","name":"测试网关","memo":"测试网关","aLiYunDeviceId":null,"simcode":"12345678901234567890"},"manufacturer":{"id":15,"code":"scinan","name":"司南物联","iphone":"18151119929","address":"江苏苏州创业产业园","remark":null,"remark1":null,"remark2":null,"remark3":null,"factoryName":null,"i":null,"u":null,"rate":null,"electricEnergy":null,"userId":1,"factoryBgImg":null,"userDTO":null},"name":"测试间隔模拟量","id":151,"addr":4,"factoryID":15,"collectorID":39,"deviceTypeID":105,"creationDate":1552622561017,"lastEditDate":null,"deviceUserID":null,"i":null,"u":null,"rate":null,"deviceState":1,"deviceGuid":null,"deviceBZ":null,"code":"16a7db728d4b410fa209b204a2c8564b","electricEnergy":null,"deviceCreateAddress":null},{"breakerid":null,"breakername":null,"breakertype":null,"breakerfirm":null,"product":{"name":"范荣华","id":104,"factoryName":"水表1","protocolID":1,"deviceType":"水表1","mobile":"18151119929","isValid":null,"productKey":null,"shouldBeChannels":null},"collector":{"id":53,"code":"000000000012345","name":"水表","memo":"水表","aLiYunDeviceId":null,"simcode":"12345678901234567890"},"manufacturer":{"id":15,"code":"scinan","name":"司南物联","iphone":"18151119929","address":"江苏苏州创业产业园","remark":null,"remark1":null,"remark2":null,"remark3":null,"factoryName":null,"i":null,"u":null,"rate":null,"electricEnergy":null,"userId":1,"factoryBgImg":null,"userDTO":null},"name":"水表测试设备","id":150,"addr":1,"factoryID":15,"collectorID":53,"deviceTypeID":104,"creationDate":1552534692257,"lastEditDate":null,"deviceUserID":null,"i":null,"u":null,"rate":null,"deviceState":1,"deviceGuid":null,"deviceBZ":null,"code":"808987f07ad84b52aab04d3ca5b85a60","electricEnergy":null,"deviceCreateAddress":null},{"breakerid":null,"breakername":null,"breakertype":null,"breakerfirm":null,"product":{"name":"于洋","id":103,"factoryName":"于测试产品1","protocolID":1,"deviceType":"于测试产品1","mobile":"17701010020","isValid":null,"productKey":null,"shouldBeChannels":null},"collector":{"id":52,"code":"798079807980798","name":"于测试网关1","memo":"于测","aLiYunDeviceId":null,"simcode":"11111111111111111111"},"manufacturer":{"id":15,"code":"scinan","name":"司南物联","iphone":"18151119929","address":"江苏苏州创业产业园","remark":null,"remark1":null,"remark2":null,"remark3":null,"factoryName":null,"i":null,"u":null,"rate":null,"electricEnergy":null,"userId":1,"factoryBgImg":null,"userDTO":null},"name":"1","id":149,"addr":1,"factoryID":15,"collectorID":52,"deviceTypeID":103,"creationDate":1552467744207,"lastEditDate":null,"deviceUserID":null,"i":null,"u":null,"rate":null,"deviceState":1,"deviceGuid":null,"deviceBZ":null,"code":"249f0fb55abb4ae1a9d9431eb48dde33","electricEnergy":null,"deviceCreateAddress":null},{"breakerid":null,"breakername":null,"breakertype":null,"breakerfirm":null,"product":{"name":"于洋","id":103,"factoryName":"于测试产品1","protocolID":1,"deviceType":"于测试产品1","mobile":"17701010020","isValid":null,"productKey":null,"shouldBeChannels":null},"collector":{"id":52,"code":"798079807980798","name":"于测试网关1","memo":"于测","aLiYunDeviceId":null,"simcode":"11111111111111111111"},"manufacturer":{"id":15,"code":"scinan","name":"司南物联","iphone":"18151119929","address":"江苏苏州创业产业园","remark":null,"remark1":null,"remark2":null,"remark3":null,"factoryName":null,"i":null,"u":null,"rate":null,"electricEnergy":null,"userId":1,"factoryBgImg":null,"userDTO":null},"name":"于测试设备","id":146,"addr":20,"factoryID":15,"collectorID":52,"deviceTypeID":103,"creationDate":1552462151730,"lastEditDate":null,"deviceUserID":null,"i":null,"u":null,"rate":null,"deviceState":1,"deviceGuid":null,"deviceBZ":null,"code":null,"electricEnergy":null,"deviceCreateAddress":null}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * breakerid : null
         * breakername : null
         * breakertype : null
         * breakerfirm : null
         * product : {"name":"范总","id":106,"factoryName":"通讯产品","protocolID":1,"deviceType":"通讯","mobile":"18151119929","isValid":null,"productKey":null,"shouldBeChannels":null}
         * collector : {"id":54,"code":"000000000000001","name":"通讯网关","memo":"版本1.01","aLiYunDeviceId":null,"simcode":"12345678901234567890"}
         * manufacturer : {"id":15,"code":"scinan","name":"司南物联","iphone":"18151119929","address":"江苏苏州创业产业园","remark":null,"remark1":null,"remark2":null,"remark3":null,"factoryName":null,"i":null,"u":null,"rate":null,"electricEnergy":null,"userId":1,"factoryBgImg":null,"userDTO":null}
         * name : 通讯设备
         * id : 153
         * addr : 1
         * factoryID : 15
         * collectorID : 54
         * deviceTypeID : 106
         * creationDate : 1557043946700
         * lastEditDate : null
         * deviceUserID : null
         * i : null
         * u : null
         * rate : null
         * deviceState : 1
         * deviceGuid : null
         * deviceBZ : null
         * code : 6330ac8871e64cadaa7db3e5c0e86804
         * electricEnergy : null
         * deviceCreateAddress : null
         */

        private Object breakerid;
        private Object breakername;
        private Object breakertype;
        private Object breakerfirm;
        private ProductBean product;
        private CollectorBean collector;
        private ManufacturerBean manufacturer;
        private String name;
        private long id;
        private int addr;
        private int factoryID;
        private int collectorID;
        private int deviceTypeID;
        private long creationDate;
        private Object lastEditDate;
        private Object deviceUserID;
        private Object i;
        private Object u;
        private Object rate;
        private int deviceState;
        private Object deviceGuid;
        private Object deviceBZ;
        private String code;
        private Object electricEnergy;
        private Object deviceCreateAddress;

        public Object getBreakerid() {
            return breakerid;
        }

        public void setBreakerid(Object breakerid) {
            this.breakerid = breakerid;
        }

        public Object getBreakername() {
            return breakername;
        }

        public void setBreakername(Object breakername) {
            this.breakername = breakername;
        }

        public Object getBreakertype() {
            return breakertype;
        }

        public void setBreakertype(Object breakertype) {
            this.breakertype = breakertype;
        }

        public Object getBreakerfirm() {
            return breakerfirm;
        }

        public void setBreakerfirm(Object breakerfirm) {
            this.breakerfirm = breakerfirm;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public CollectorBean getCollector() {
            return collector;
        }

        public void setCollector(CollectorBean collector) {
            this.collector = collector;
        }

        public ManufacturerBean getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(ManufacturerBean manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getAddr() {
            return addr;
        }

        public void setAddr(int addr) {
            this.addr = addr;
        }

        public int getFactoryID() {
            return factoryID;
        }

        public void setFactoryID(int factoryID) {
            this.factoryID = factoryID;
        }

        public int getCollectorID() {
            return collectorID;
        }

        public void setCollectorID(int collectorID) {
            this.collectorID = collectorID;
        }

        public int getDeviceTypeID() {
            return deviceTypeID;
        }

        public void setDeviceTypeID(int deviceTypeID) {
            this.deviceTypeID = deviceTypeID;
        }

        public long getCreationDate() {
            return creationDate;
        }

        public void setCreationDate(long creationDate) {
            this.creationDate = creationDate;
        }

        public Object getLastEditDate() {
            return lastEditDate;
        }

        public void setLastEditDate(Object lastEditDate) {
            this.lastEditDate = lastEditDate;
        }

        public Object getDeviceUserID() {
            return deviceUserID;
        }

        public void setDeviceUserID(Object deviceUserID) {
            this.deviceUserID = deviceUserID;
        }

        public Object getI() {
            return i;
        }

        public void setI(Object i) {
            this.i = i;
        }

        public Object getU() {
            return u;
        }

        public void setU(Object u) {
            this.u = u;
        }

        public Object getRate() {
            return rate;
        }

        public void setRate(Object rate) {
            this.rate = rate;
        }

        public int getDeviceState() {
            return deviceState;
        }

        public void setDeviceState(int deviceState) {
            this.deviceState = deviceState;
        }

        public Object getDeviceGuid() {
            return deviceGuid;
        }

        public void setDeviceGuid(Object deviceGuid) {
            this.deviceGuid = deviceGuid;
        }

        public Object getDeviceBZ() {
            return deviceBZ;
        }

        public void setDeviceBZ(Object deviceBZ) {
            this.deviceBZ = deviceBZ;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getElectricEnergy() {
            return electricEnergy;
        }

        public void setElectricEnergy(Object electricEnergy) {
            this.electricEnergy = electricEnergy;
        }

        public Object getDeviceCreateAddress() {
            return deviceCreateAddress;
        }

        public void setDeviceCreateAddress(Object deviceCreateAddress) {
            this.deviceCreateAddress = deviceCreateAddress;
        }

        public static class ProductBean {
            /**
             * name : 范总
             * id : 106
             * factoryName : 通讯产品
             * protocolID : 1
             * deviceType : 通讯
             * mobile : 18151119929
             * isValid : null
             * productKey : null
             * shouldBeChannels : null
             */

            private String name;
            private int id;
            private String factoryName;
            private int protocolID;
            private String deviceType;
            private String mobile;
            private Object isValid;
            private Object productKey;
            private Object shouldBeChannels;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFactoryName() {
                return factoryName;
            }

            public void setFactoryName(String factoryName) {
                this.factoryName = factoryName;
            }

            public int getProtocolID() {
                return protocolID;
            }

            public void setProtocolID(int protocolID) {
                this.protocolID = protocolID;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getIsValid() {
                return isValid;
            }

            public void setIsValid(Object isValid) {
                this.isValid = isValid;
            }

            public Object getProductKey() {
                return productKey;
            }

            public void setProductKey(Object productKey) {
                this.productKey = productKey;
            }

            public Object getShouldBeChannels() {
                return shouldBeChannels;
            }

            public void setShouldBeChannels(Object shouldBeChannels) {
                this.shouldBeChannels = shouldBeChannels;
            }
        }

        public static class CollectorBean {
            /**
             * id : 54
             * code : 000000000000001
             * name : 通讯网关
             * memo : 版本1.01
             * aLiYunDeviceId : null
             * simcode : 12345678901234567890
             */

            private int id;
            private String code;
            private String name;
            private String memo;
            private Object aLiYunDeviceId;
            private String simcode;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public Object getALiYunDeviceId() {
                return aLiYunDeviceId;
            }

            public void setALiYunDeviceId(Object aLiYunDeviceId) {
                this.aLiYunDeviceId = aLiYunDeviceId;
            }

            public String getSimcode() {
                return simcode;
            }

            public void setSimcode(String simcode) {
                this.simcode = simcode;
            }
        }

        public static class ManufacturerBean {
            /**
             * id : 15
             * code : scinan
             * name : 司南物联
             * iphone : 18151119929
             * address : 江苏苏州创业产业园
             * remark : null
             * remark1 : null
             * remark2 : null
             * remark3 : null
             * factoryName : null
             * i : null
             * u : null
             * rate : null
             * electricEnergy : null
             * userId : 1
             * factoryBgImg : null
             * userDTO : null
             */

            private int id;
            private String code;
            private String name;
            private String iphone;
            private String address;
            private Object remark;
            private Object remark1;
            private Object remark2;
            private Object remark3;
            private Object factoryName;
            private Object i;
            private Object u;
            private Object rate;
            private Object electricEnergy;
            private int userId;
            private Object factoryBgImg;
            private Object userDTO;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIphone() {
                return iphone;
            }

            public void setIphone(String iphone) {
                this.iphone = iphone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getRemark1() {
                return remark1;
            }

            public void setRemark1(Object remark1) {
                this.remark1 = remark1;
            }

            public Object getRemark2() {
                return remark2;
            }

            public void setRemark2(Object remark2) {
                this.remark2 = remark2;
            }

            public Object getRemark3() {
                return remark3;
            }

            public void setRemark3(Object remark3) {
                this.remark3 = remark3;
            }

            public Object getFactoryName() {
                return factoryName;
            }

            public void setFactoryName(Object factoryName) {
                this.factoryName = factoryName;
            }

            public Object getI() {
                return i;
            }

            public void setI(Object i) {
                this.i = i;
            }

            public Object getU() {
                return u;
            }

            public void setU(Object u) {
                this.u = u;
            }

            public Object getRate() {
                return rate;
            }

            public void setRate(Object rate) {
                this.rate = rate;
            }

            public Object getElectricEnergy() {
                return electricEnergy;
            }

            public void setElectricEnergy(Object electricEnergy) {
                this.electricEnergy = electricEnergy;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getFactoryBgImg() {
                return factoryBgImg;
            }

            public void setFactoryBgImg(Object factoryBgImg) {
                this.factoryBgImg = factoryBgImg;
            }

            public Object getUserDTO() {
                return userDTO;
            }

            public void setUserDTO(Object userDTO) {
                this.userDTO = userDTO;
            }
        }
    }
}
