package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.IShangChuanModel;
import test.bawie.fangjd.model.ShangChuanModel;
import test.bawie.fangjd.view.IShangChuanListener;



public class ShangChuanPresenter {

    private IShangChuanListener iShangChuanListener;
    private final ShangChuanModel shangChuanModel;

    public ShangChuanPresenter(IShangChuanListener iShangChuanListener){
        this.iShangChuanListener  = iShangChuanListener;
        shangChuanModel = new ShangChuanModel();
    }
    public void lianjie(){
        String uid = iShangChuanListener.getuid();
        String token = iShangChuanListener.gettoken();
        shangChuanModel.getData(uid, token, new IShangChuanModel() {
            @Override
            public void onSuccess(String s) {
                iShangChuanListener.getShang(s);
            }
        });
    }
}
