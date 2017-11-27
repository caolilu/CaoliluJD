package test.bawie.fangjd.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.TianJiaBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;

import static com.umeng.socialize.utils.DeviceConfig.context;



public class TianJiaModel {
    public void getData(int uid, String addr, String phone, String name, String token, final ITianJiaModel itianjia){
        Map<String,String> par = new HashMap<>();
        par.put("uid",uid+"");
        par.put("addr",addr);
        par.put("mobile",phone);
        par.put("name",name);
        par.put("token",token);
        OkHttpUtils.getInstance(context).doPost(Api.TIANJIA, par, TianJiaBean.class, new OnNetListener() {
                    @Override
                    public void onSuccess(Object o) throws IOException {
                        TianJiaBean bean = (TianJiaBean) o;
                        itianjia.onSuccess(bean);
                    }

                    @Override
                    public void onError(IOException e) {

                    }
                }
        );
    };
}
