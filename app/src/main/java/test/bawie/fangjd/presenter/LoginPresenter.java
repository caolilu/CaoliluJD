package test.bawie.fangjd.presenter;

import android.content.Context;

import java.io.IOException;

import test.bawie.fangjd.model.ILoginModel;
import test.bawie.fangjd.model.LoginModel;
import test.bawie.fangjd.model.bean.LoginBean;
import test.bawie.fangjd.view.ILoginListener;


public class LoginPresenter {
    private ILoginListener iLoginListener;
    private final LoginModel loginModel;
    private Context context;
    public LoginPresenter(ILoginListener iLoginListener){
        this.context = (Context) iLoginListener;
        this.iLoginListener = iLoginListener;
        loginModel = new LoginModel(context);
    }
    public void login(){
        String name = iLoginListener.getName();
        String pwd = iLoginListener.getPwd();
        loginModel.login(name, pwd, new ILoginModel() {
            @Override
            public void onSuccess(LoginBean bean) {
                iLoginListener.show(bean);
            }

            @Override
            public void onFail(IOException e) {

            }
        });

    }
}
