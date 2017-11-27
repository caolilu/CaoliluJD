package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.FZModel;
import test.bawie.fangjd.model.IFZModel;
import test.bawie.fangjd.model.bean.FenZuoBean;
import test.bawie.fangjd.view.fragment.IFenLeiListener;



public class FZPresenter {

    private Context context;
    private IFenLeiListener iFenLeiListener;
    private final FZModel fzModel;

    public FZPresenter(IFenLeiListener iFenLeiListener,Context context){
        this.context = context;
        this.iFenLeiListener = iFenLeiListener;
        fzModel = new FZModel(context);
    }

    public void lianjie(){
        fzModel.getdata(new IFZModel() {
            @Override
            public void onSuccess(FenZuoBean bean) {
                iFenLeiListener.getdata(bean);
            }
        });
    }
}
