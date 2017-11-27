package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.ITianJiaModel;
import test.bawie.fangjd.model.TianJiaModel;
import test.bawie.fangjd.model.bean.TianJiaBean;
import test.bawie.fangjd.view.TianJiaListener;


public class TianJiaPresentr {

    private TianJiaListener tianJiaListener;
    private final TianJiaModel tianJiaModel;

    public TianJiaPresentr(TianJiaListener tianJiaListener){
        this.tianJiaListener = tianJiaListener;
        tianJiaModel = new TianJiaModel();
    }
    public void lianji(){
        String addr = tianJiaListener.addr();
        String name = tianJiaListener.name();
        String phone = tianJiaListener.phone();
        String token = tianJiaListener.token();
        int uid = tianJiaListener.uid();
        tianJiaModel.getData(uid, addr, phone, name, token, new ITianJiaModel() {
            @Override
            public void onSuccess(TianJiaBean bean) {
                tianJiaListener.getData(bean);
            }
        });
    }
}
