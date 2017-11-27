package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.DanBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;



public class DanModel {
    public void getData(Context context, String uid, final IDanModel model){

        Map<String,String> par = new HashMap<>();
        par.put("uid",uid);
        OkHttpUtils.getInstance(context).doPost(Api.DAN, par, DanBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                DanBean bean = (DanBean) o;
                model.onSuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
