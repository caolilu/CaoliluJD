package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.FYMainModel;
import test.bawie.fangjd.model.IFYMainModel;
import test.bawie.fangjd.model.bean.FenYouMainBean;
import test.bawie.fangjd.view.fragment.IFenYouMainListener;



public class FYMainPresenter {

    private Context context;
    private IFenYouMainListener iFenYouMainListener;
    private final FYMainModel fyMainModel;

    public FYMainPresenter(Context context, IFenYouMainListener iFenYouListener){
        this.context = context;
        this.iFenYouMainListener = iFenYouListener;
        fyMainModel = new FYMainModel();
    }
    public void lianjie(){
        final int cid = iFenYouMainListener.getuid();
        fyMainModel.getData(new IFYMainModel() {
            @Override
            public void onSuccess(FenYouMainBean bean) {
                iFenYouMainListener.setData(bean);
            }
        }, cid);
    }
}
