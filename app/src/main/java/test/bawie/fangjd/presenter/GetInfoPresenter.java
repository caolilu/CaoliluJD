package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.GetInfoModel;
import test.bawie.fangjd.model.IGetInfoModel;
import test.bawie.fangjd.model.bean.UserinfoBean;
import test.bawie.fangjd.view.fragment.IWodeListener;



public class GetInfoPresenter {
    private IWodeListener iWodeListener;
    private Context context;
    private GetInfoModel getInfoModel;

    public GetInfoPresenter(IWodeListener iWodeListener,Context context){
        this.context = context;
        this.iWodeListener = iWodeListener;
        getInfoModel = new GetInfoModel(context);
    }
    public void getinfo(){
        int uid = iWodeListener.getuid();
        if(uid!=0){
            getInfoModel.getData(uid, new IGetInfoModel() {
                @Override
                public void onSuccess(UserinfoBean bean) {
                    iWodeListener.setdata(bean);
                }
            });
        }

    }
}
