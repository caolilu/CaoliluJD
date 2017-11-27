package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.IShowYeModel;
import test.bawie.fangjd.model.ShowYeModel;
import test.bawie.fangjd.model.bean.ShouYeLunboBean;
import test.bawie.fangjd.view.fragment.IShowyeListener;



public class ShowYePresenter {
    private IShowyeListener iShowyeListener;
    private final ShowYeModel showYeModel;
    private Context context;
    public ShowYePresenter(IShowyeListener iShowyeListener, Context context){
        this.context = context;
        this.iShowyeListener = iShowyeListener;
        showYeModel = new ShowYeModel(context);
    }
    public void getlunbo(){
        showYeModel.getlunbo(new IShowYeModel() {
            @Override
            public void onSuccess(ShouYeLunboBean bean) {
                iShowyeListener.setlunbo(bean);
            }
        });
    };
}
