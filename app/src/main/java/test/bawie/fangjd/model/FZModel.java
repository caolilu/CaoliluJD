package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;

import test.bawie.fangjd.model.bean.FenZuoBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;



public class FZModel {

    private Context context;

    public FZModel(Context context) {
        this.context = context;
    }

    public void getdata(final IFZModel ifzModel){
        OkHttpUtils.getInstance(context).doget(Api.FENZUO, FenZuoBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                FenZuoBean bean = (FenZuoBean) o;
                ifzModel.onSuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    };
}
