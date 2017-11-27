package test.bawie.fangjd.model.bean;

import java.util.List;



public class DanBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-11-03T08:56:04","orderid":1407,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:06","orderid":1408,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:11","orderid":1409,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:11","orderid":1410,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:12","orderid":1411,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:12","orderid":1412,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:12","orderid":1413,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:12","orderid":1414,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:12","orderid":1415,"price":59000,"status":0,"title":"","uid":552},{"createtime":"2017-11-03T08:56:14","orderid":1416,"price":59000,"status":0,"title":"","uid":552}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-11-03T08:56:04
         * orderid : 1407
         * price : 59000.0
         * status : 0
         * title :
         * uid : 552
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
