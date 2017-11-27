package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.DanModel;
import test.bawie.fangjd.model.IDanModel;
import test.bawie.fangjd.model.bean.DanBean;
import test.bawie.fangjd.view.IDanListener;


public class DanPresenter {
    private IDanListener iDanListener;
    private Context context;
    private final DanModel danModel;

    public DanPresenter(IDanListener iDanListener){
        this.iDanListener = iDanListener;
        this.context = (Context) iDanListener;
        danModel = new DanModel();
    }
    public void lianjie(){
        String uid = iDanListener.uid();
        danModel.getData(context, uid, new IDanModel() {
            @Override
            public void onSuccess(DanBean bean) {
                iDanListener.setDan(bean);
            }
        });
    }
}
