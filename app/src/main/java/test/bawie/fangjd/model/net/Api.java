package test.bawie.fangjd.model.net;



public interface Api {
    public static final boolean isOnline = false;
//    public static  final String devIp = "http://120.27.23.105";
    public static  final String devIp = "https://www.zhaoapi.cn";
    public static final String workIp = "";
    public static final String HOST = isOnline ? workIp : devIp;

    public static final String LOGIN = HOST+"/user/login";
    public static final String REG = HOST+"/user/reg";
    public static final String USERINFO = HOST+"/user/getUserInfo";
    public static final String SHOUYEGG = HOST+"/ad/getAd";
    public static final String FENZUO = HOST+"/product/getCatagory";
    public static final String FENYOU = HOST+"/product/";
    public static final String FENYOUMAIN = HOST+"/product/";
    public static final String GOODS = HOST+"/product/";
    public static final String SOUSUO = HOST+"/product/";
    public static final String USER = HOST+"/user/";
    public static final String ADDGOODS = HOST+"/product/";
    public static final String GWCAR = HOST+"/product/";
    public static final String DELETE = HOST+"/product/";
    public static final String ADDRES = HOST+"/user/getAddrs";
    public static final String TIANJIA = HOST+"/user/addAddr";
    public static final String DINGDAN = HOST+"/product/createOrder";
    public static final String DAN = HOST+"/product/getOrders";
    public static final String  GETFI= HOST+"/";
    public static final String  ADDMM= HOST+"/user/setAddr";
    public static final String  GETMM= HOST+"/user/getDefaultAddr";


}
