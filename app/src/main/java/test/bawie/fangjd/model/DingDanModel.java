package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.DingDanBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;



public class DingDanModel {
    public void getData(Context context, String uid, String price, String token, final IDingDanModel iDingDanModel){
        Map<String,String> par = new HashMap<>();
        par.put("uid",uid);
        par.put("price",price);
        par.put("token",token);
        OkHttpUtils.getInstance(context).doPost(Api.DINGDAN, par, DingDanBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                DingDanBean bean = (DingDanBean) o;
                iDingDanModel.onSuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
