package test.bawie.fangjd.model;

import java.io.IOException;

import test.bawie.fangjd.model.bean.LoginBean;



public interface ILoginModel {
    public void onSuccess(LoginBean bean);
    public void onFail(IOException e);
}
