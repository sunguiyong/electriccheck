package com.example.electricpower.entity.to.mpdata;

import java.util.List;

public class MPToday {

    /**
     * message : success
     * result : [{"sdev_id":"149","snode_id":"149000001289","last_data":37928.2036,"new_data":38587.0695,"diff_data":663.7612,"data":38259.51635,"gmt_create":1561219200000},{"sdev_id":"149","snode_id":"149000001288","last_data":38023.7924,"new_data":38667.3965,"diff_data":654.6403,"data":38342.2062,"gmt_create":1561219200000},{"sdev_id":"149","snode_id":"149000001289","last_data":38591.9648,"new_data":39236.8539,"diff_data":649.8848,"data":38917.74357,"gmt_create":1561222800000},{"sdev_id":"149","snode_id":"149000001288","last_data":38676.7085,"new_data":39324.6491,"diff_data":657.036,"data":38996.05018,"gmt_create":1561222800000},{"sdev_id":"149","snode_id":"149000001289","last_data":39241.8496,"new_data":39894.347,"diff_data":658.0378,"data":39571.55623,"gmt_create":1561226400000},{"sdev_id":"149","snode_id":"149000001288","last_data":39334.8989,"new_data":39988.8944,"diff_data":665.0649,"data":39655.96656,"gmt_create":1561226400000},{"sdev_id":"149","snode_id":"149000001289","last_data":39899.8874,"new_data":40537.9608,"diff_data":643.2651,"data":40221.38206,"gmt_create":1561230000000},{"sdev_id":"149","snode_id":"149000001288","last_data":40000.4469,"new_data":40645.1142,"diff_data":655.3992,"data":40321.04264,"gmt_create":1561230000000},{"sdev_id":"149","snode_id":"149000001289","last_data":40543.1525,"new_data":41207.1985,"diff_data":668.57,"data":40876.47268,"gmt_create":1561233600000},{"sdev_id":"149","snode_id":"149000001288","last_data":40654.2683,"new_data":41287.3503,"diff_data":642.3189,"data":40965.43436,"gmt_create":1561233600000},{"sdev_id":"149","snode_id":"149000001289","last_data":41211.7225,"new_data":41869.832,"diff_data":662.9685,"data":41537.57744,"gmt_create":1561237200000},{"sdev_id":"149","snode_id":"149000001288","last_data":41296.9175,"new_data":41941.1379,"diff_data":653.9012,"data":41610.56072,"gmt_create":1561237200000},{"sdev_id":"149","snode_id":"149000001289","last_data":41874.691,"new_data":42541.486,"diff_data":671.303,"data":42211.58577,"gmt_create":1561240800000},{"sdev_id":"149","snode_id":"149000001288","last_data":41953.4681,"new_data":42604.9078,"diff_data":663.6098,"data":42272.18512,"gmt_create":1561240800000},{"sdev_id":"149","snode_id":"149000001289","last_data":42545.994,"new_data":43205.9968,"diff_data":664.7025,"data":42883.68482,"gmt_create":1561244400000},{"sdev_id":"149","snode_id":"149000001288","last_data":42616.3681,"new_data":43253.6185,"diff_data":648.9764,"data":42937.52679,"gmt_create":1561244400000},{"sdev_id":"149","snode_id":"149000001289","last_data":43210.6965,"new_data":43862.9041,"diff_data":657.057,"data":43538.6574,"gmt_create":1561248000000},{"sdev_id":"149","snode_id":"149000001288","last_data":43266.1832,"new_data":43919.0965,"diff_data":665.7617,"data":43590.72593,"gmt_create":1561248000000},{"sdev_id":"149","snode_id":"149000001289","last_data":43867.7535,"new_data":44505.2705,"diff_data":643.4226,"data":44194.22542,"gmt_create":1561251600000},{"sdev_id":"149","snode_id":"149000001288","last_data":43933.7994,"new_data":44557.9899,"diff_data":638.732,"data":44237.79484,"gmt_create":1561251600000},{"sdev_id":"149","snode_id":"149000001289","last_data":44511.1761,"new_data":45144.7652,"diff_data":638.8891,"data":44827.88072,"gmt_create":1561255200000},{"sdev_id":"149","snode_id":"149000001288","last_data":44568.5931,"new_data":45211.448,"diff_data":653.625,"data":44889.93768,"gmt_create":1561255200000},{"sdev_id":"149","snode_id":"149000001289","last_data":45150.0652,"new_data":45799.0732,"diff_data":653.7235,"data":45475.09005,"gmt_create":1561258800000},{"sdev_id":"149","snode_id":"149000001288","last_data":45222.4614,"new_data":45861.8262,"diff_data":650.1266,"data":45540.34592,"gmt_create":1561258800000},{"sdev_id":"149","snode_id":"149000001289","last_data":45804.7198,"new_data":46445.695,"diff_data":646.176,"data":46126.86873,"gmt_create":1561262400000},{"sdev_id":"149","snode_id":"149000001288","last_data":45868.6461,"new_data":46508.6629,"diff_data":647.0252,"data":46186.18731,"gmt_create":1561262400000},{"sdev_id":"149","snode_id":"149000001289","last_data":46450.3921,"new_data":47084.7488,"diff_data":643.444,"data":46772.17457,"gmt_create":1561266000000},{"sdev_id":"149","snode_id":"149000001288","last_data":46513.3412,"new_data":47161.6565,"diff_data":653.1419,"data":46837.38473,"gmt_create":1561266000000},{"sdev_id":"149","snode_id":"149000001289","last_data":47093.4087,"new_data":47718.2486,"diff_data":628.7325,"data":47407.43618,"gmt_create":1561269600000},{"sdev_id":"149","snode_id":"149000001288","last_data":47171.4607,"new_data":47794.1092,"diff_data":632.2046,"data":47475.38305,"gmt_create":1561269600000},{"sdev_id":"149","snode_id":"149000001289","last_data":47722.7704,"new_data":48374.9486,"diff_data":656.9697,"data":48050.44579,"gmt_create":1561273200000},{"sdev_id":"149","snode_id":"149000001288","last_data":47799.079,"new_data":48451.2778,"diff_data":656.884,"data":48123.59078,"gmt_create":1561273200000}]
     * status : 200
     * timestamp : 1561340014069
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
         * sdev_id : 149
         * snode_id : 149000001289
         * last_data : 37928.2036
         * new_data : 38587.0695
         * diff_data : 663.7612
         * data : 38259.51635
         * gmt_create : 1561219200000
         */

        private String sdev_id;
        private String snode_id;
        private double last_data;
        private double new_data;
        private double diff_data;
        private double data;
        private long gmt_create;

        public String getSdev_id() {
            return sdev_id;
        }

        public void setSdev_id(String sdev_id) {
            this.sdev_id = sdev_id;
        }

        public String getSnode_id() {
            return snode_id;
        }

        public void setSnode_id(String snode_id) {
            this.snode_id = snode_id;
        }

        public double getLast_data() {
            return last_data;
        }

        public void setLast_data(double last_data) {
            this.last_data = last_data;
        }

        public double getNew_data() {
            return new_data;
        }

        public void setNew_data(double new_data) {
            this.new_data = new_data;
        }

        public double getDiff_data() {
            return diff_data;
        }

        public void setDiff_data(double diff_data) {
            this.diff_data = diff_data;
        }

        public double getData() {
            return data;
        }

        public void setData(double data) {
            this.data = data;
        }

        public long getGmt_create() {
            return gmt_create;
        }

        public void setGmt_create(long gmt_create) {
            this.gmt_create = gmt_create;
        }
    }
}
