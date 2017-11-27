package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.RegBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;


public class ZhuceModel{
    private Context context;
    private IZhuceModel iZhuceModel;
    public ZhuceModel(Context context){
        this.context = context;
    }
    public void getData(String name, String pwd, final IZhuceModel iZhuceModel){
        Map<String,String> parmas = new HashMap<>();
        parmas.put("mobile",name);
        parmas.put("password",pwd);
        OkHttpUtils.getInstance(context).doPost(Api.REG, parmas, RegBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                RegBean regBean = (RegBean) o;
                iZhuceModel.onSuccess(regBean);
            }

            @Override
            public void onError(IOException e) {
                iZhuceModel.onFail(e);
            }
        });
    };
}
