package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.GetMMModel;
import test.bawie.fangjd.model.IGetMMModel;
import test.bawie.fangjd.model.bean.GetMMBean;
import test.bawie.fangjd.view.IGetMMListener;



public class GetMMPresenter {

    private IGetMMListener iGetMMListener;
    private final GetMMModel getMMModel;
    private Context context;
    public GetMMPresenter(IGetMMListener iGetMMListener, Context context){
        this.context = context;
        this.iGetMMListener = iGetMMListener;
        getMMModel = new GetMMModel();
    }
    public void lianjie(){
        String token = iGetMMListener.token();
        String uid = iGetMMListener.uid();
        getMMModel.getdata(context, uid, token, new IGetMMModel() {
            @Override
            public void onsuccess(GetMMBean bean) {
                iGetMMListener.getMM(bean);
            }
        });

    }
}
