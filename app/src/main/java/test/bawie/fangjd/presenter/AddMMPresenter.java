package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.AddMMModel;
import test.bawie.fangjd.model.IAddMMModel;
import test.bawie.fangjd.model.bean.AddMMBean;
import test.bawie.fangjd.view.IAddMMListener;



public class AddMMPresenter {

    private IAddMMListener iAddMMListener;
    private final AddMMModel addMMModel;
    private Context context;
    public AddMMPresenter(IAddMMListener iAddMMListener,Context context){
        this.context = context;
        this.iAddMMListener = iAddMMListener;
        addMMModel = new AddMMModel();
    }
    public void lianjie(){
        String addrid = iAddMMListener.addrid();
        String status = iAddMMListener.status();
        String token = iAddMMListener.token();
        int uid2 = iAddMMListener.uid();
        String uid = String.valueOf(uid2);
        addMMModel.getData(context,uid,addrid,status,token ,new IAddMMModel() {
            @Override
            public void onsuccess(AddMMBean bean) {
                iAddMMListener.getMMdata(bean);
            }
        });
    }
}
