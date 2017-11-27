package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.AddrsModel;
import test.bawie.fangjd.model.IAddrsModel;
import test.bawie.fangjd.model.bean.AddrsBean;
import test.bawie.fangjd.view.IAddresListener;



public class AddrsPresenter {

    private IAddresListener iAddresListener;
    private final AddrsModel addrsModel;
    private Context context;

    public AddrsPresenter(IAddresListener iAddresListener){
        this.context = (Context) iAddresListener;
        this.iAddresListener = iAddresListener;
        addrsModel = new AddrsModel();
    }

    public void lianjie(){
        String uid = String.valueOf(iAddresListener.getUid());
        String token = iAddresListener.token();
        addrsModel.getData(uid, token,context, new IAddrsModel() {
            @Override
            public void onSuccess(AddrsBean bean) {
                iAddresListener.setData(bean);
            }
        });
    }
}
