package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.UserinfoBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;



public class GetInfoModel {
    public Context context;
    public GetInfoModel(Context context){
        this.context = context;
    }
    public void getData(int uid, final IGetInfoModel iGetInfoModel){
        Map<String,String> params = new HashMap<>();
        params.put("uid",uid+"");
        OkHttpUtils.getInstance(context).doPost(Api.USERINFO, params, UserinfoBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                UserinfoBean bean = (UserinfoBean) o;
                iGetInfoModel.onSuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    };
}
