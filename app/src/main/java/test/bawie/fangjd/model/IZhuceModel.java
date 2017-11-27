package test.bawie.fangjd.model;

import java.io.IOException;

import test.bawie.fangjd.model.bean.RegBean;



public interface IZhuceModel {
    public void onSuccess(RegBean regBean);
    void onFail(IOException e);
}
