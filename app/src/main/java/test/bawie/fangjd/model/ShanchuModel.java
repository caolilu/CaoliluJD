package test.bawie.fangjd.model;

import android.content.Context;

import java.io.IOException;

import test.bawie.fangjd.model.bean.ShanchuBean;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.model.net.OnNetListener;

/**
 * Created by cll on 2017/11/20.
 */

public class ShanchuModel {
    private Context context;

    public ShanchuModel(Context context) {
        this.context = context;
    }
    public void shouye(final IShanchuModel iShanchuModel, int uid, int pid){
        String url="https://www.zhaoapi.cn/product/deleteCart?uid=509&token=0300F8113E6CAB6CCC04E99B1FACEDF0&pid="+pid;
        OkHttpUtils.getInstance(context).doget(url, ShanchuBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Object o) throws IOException {
                ShanchuBean shanchuBean= (ShanchuBean) o;
                iShanchuModel.onsuccess(shanchuBean);
            }

            @Override
            public void onError(IOException e) {

            }
        });
    }
}
