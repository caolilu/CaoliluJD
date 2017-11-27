package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;

import test.bawie.fangjd.model.bean.ShouYeLunboBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;


public class ShowYeModel {
    private Context context;
    public ShowYeModel(Context context){
        this.context = context;
    }
    public void getlunbo(final IShowYeModel iShowYeModel){
        OkHttpUtils.getInstance(context).doget(Api.SHOUYEGG, ShouYeLunboBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                ShouYeLunboBean bean = (ShouYeLunboBean) o;
                iShowYeModel.onSuccess(bean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
