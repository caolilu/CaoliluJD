package test.bawie.fangjd.presenter;

import android.content.Context;

import java.io.IOException;

import test.bawie.fangjd.model.IZhuceModel;
import test.bawie.fangjd.model.ZhuceModel;
import test.bawie.fangjd.model.bean.RegBean;
import test.bawie.fangjd.view.IZhuceListener;



public class ZhucePresenter{
    private IZhuceListener izhuceListener;
    private final ZhuceModel zhuceModel;
    private Context context;
    public ZhucePresenter(IZhuceListener izhuceListener){
        this.context = (Context) izhuceListener;
        this.izhuceListener = izhuceListener;
        zhuceModel = new ZhuceModel(context);

    }
    public void zhuce(){
        String name = izhuceListener.getName();
        String pwd = izhuceListener.getpwd();
        zhuceModel.getData(name, pwd, new IZhuceModel() {
            @Override
            public void onSuccess(RegBean regBean) {
                izhuceListener.zhuce(regBean);
            }

            @Override
            public void onFail(IOException e) {

            }
        });
    }
}
