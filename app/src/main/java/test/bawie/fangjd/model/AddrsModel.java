package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.AddrsBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;


public class AddrsModel {

    public void getData(String uid, String token, Context context,final IAddrsModel iAddrsModel){
        Map<String,String> par = new HashMap<>();
        par.put("uid",uid);
        par.put("token",token);
        OkHttpUtils.getInstance(context).doPost(Api.ADDRES, par, AddrsBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                AddrsBean addrsBean = (AddrsBean) o;
                iAddrsModel.onSuccess(addrsBean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
