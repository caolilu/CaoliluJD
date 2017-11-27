package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.GetMMBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;



public class GetMMModel {

    public void getdata(Context context, String uid, String token, final IGetMMModel iGetMMModel){
        Map<String,String> parmas = new HashMap<>();
        parmas.put("uid",uid);
        parmas.put("token",token);
        OkHttpUtils.getInstance(context).doPost(Api.GETMM, parmas, GetMMBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                GetMMBean bean = (GetMMBean) o;
                iGetMMModel.onsuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
