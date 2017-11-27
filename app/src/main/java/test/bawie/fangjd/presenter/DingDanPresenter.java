package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.DingDanModel;
import test.bawie.fangjd.model.IDingDanModel;
import test.bawie.fangjd.model.bean.DingDanBean;
import test.bawie.fangjd.view.IDingDanListener;



public class DingDanPresenter {

    private IDingDanListener iDingDanListener;
    private final DingDanModel dingDanModel;
    private Context context;

    public DingDanPresenter(IDingDanListener iDingDanListener){
        this.context = (Context) iDingDanListener;
        this.iDingDanListener = iDingDanListener;
        dingDanModel = new DingDanModel();
    }
    public void lianjie(){
        String price = iDingDanListener.price();
        String token = iDingDanListener.token();
        String uid = iDingDanListener.uid();
        dingDanModel.getData(context, uid, price, token, new IDingDanModel() {
            @Override
            public void onSuccess(DingDanBean bean) {
                iDingDanListener.setDingDan(bean);
            }
        });
    }
}
