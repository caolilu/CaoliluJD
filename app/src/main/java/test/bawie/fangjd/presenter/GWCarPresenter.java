package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.GWCarModel;
import test.bawie.fangjd.model.IGWCarModel;
import test.bawie.fangjd.model.bean.GoodsCarBean;
import test.bawie.fangjd.view.fragment.IGouWuListener;


public class GWCarPresenter {

    private IGouWuListener iGouWuListener;
    private final GWCarModel gwCarModel;

    public GWCarPresenter(IGouWuListener iGouWuListener){
        this.iGouWuListener = iGouWuListener;
        gwCarModel = new GWCarModel();
    }
    public void lianjie(){
        int uid = iGouWuListener.uid();
        String token = iGouWuListener.token();

        gwCarModel.getData(uid, token, new IGWCarModel() {
            @Override
            public void onSuccess(GoodsCarBean bean) {
                if(iGouWuListener!=null){
                    iGouWuListener.getData(bean);
                }
            }
        });
    }
}
