package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.FYModel;
import test.bawie.fangjd.model.IFYModel;
import test.bawie.fangjd.model.bean.FenYouBean;
import test.bawie.fangjd.view.fragment.IFenYouListener;



public class FYPresenter {

    private Context context;
    private final FYModel fyModel;
    private IFenYouListener iFenYouListener;
    public FYPresenter(Context context,IFenYouListener iFenYouListener){
        this.context = context;
        this.iFenYouListener = iFenYouListener;
        fyModel = new FYModel();
    }
    public void lianjie(){
        final int cid = iFenYouListener.getuid();
        fyModel.getData(new IFYModel() {
            @Override
            public void onSuccess(FenYouBean bean) {
                iFenYouListener.setData(bean);
            }
        },cid);
    }
}
