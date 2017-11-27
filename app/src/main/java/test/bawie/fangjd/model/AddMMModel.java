package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.AddMMBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;


public class AddMMModel {

    public void getData(Context context,String uid,String addrid, String status,String token,final IAddMMModel iAddMMModel){

        Map<String,String> parmars = new HashMap<>();
        parmars.put("uid",uid);
        parmars.put("addrid",addrid);
        parmars.put("status",status);
        parmars.put("token",token);
        OkHttpUtils.getInstance(context).doPost(Api.ADDMM, parmars, AddMMBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                AddMMBean bean = (AddMMBean) o;
                iAddMMModel.onsuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
