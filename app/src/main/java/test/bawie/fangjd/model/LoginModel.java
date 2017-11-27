package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import test.bawie.fangjd.model.bean.LoginBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;


public class LoginModel {
    private Context context;
    public LoginModel(Context context){
        this.context = context;
    }

    public void login(String name, String pwd, final ILoginModel iLoginModel){
        Map<String,String> params = new HashMap<>();
        params.put("mobile",name);
        params.put("password",pwd);
        OkHttpUtils.getInstance(context).doPost(Api.LOGIN, params, LoginBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                LoginBean bean = (LoginBean) o;
                iLoginModel.onSuccess(bean);
            }

            @Override
            public void onError(IOException e) {
                iLoginModel.onFail(e);
            }
        });
    };
}
