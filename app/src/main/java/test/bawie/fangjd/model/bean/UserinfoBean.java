package test.bawie.fangjd.model.bean;



public class UserinfoBean extends BaseBean{
    /**
     * data : {"createtime":"2017-11-09T09:00:19","gender":0,"mobile":"13653637501","money":0,"password":"123456","token":"4B5DAF274221936555E01E5F7BC271F4","uid":552,"username":"13653637501"}
     * msg : 获取用户信息成功
     */

    private DataBean data;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * createtime : 2017-11-09T09:00:19
         * gender : 0
         * mobile : 13653637501
         * money : 0
         * password : 123456
         * token : 4B5DAF274221936555E01E5F7BC271F4
         * uid : 552
         * username : 13653637501
         */

        private String createtime;
        private int gender;
        private String mobile;
        private int money;
        private String password;
        private String token;
        private int uid;
        private String username;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
