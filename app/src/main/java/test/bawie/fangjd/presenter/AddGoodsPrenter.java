package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.AddGoodsModel;
import test.bawie.fangjd.model.IAddGoodsModel;
import test.bawie.fangjd.model.bean.AddGoodsBean;
import test.bawie.fangjd.view.IAddGoodsListener;



public class AddGoodsPrenter {

    private IAddGoodsListener iAddGoodsListener;
    private final AddGoodsModel addGoodsModel;

    public AddGoodsPrenter(IAddGoodsListener iAddGoodsListener){
        this.iAddGoodsListener = iAddGoodsListener;
        addGoodsModel = new AddGoodsModel();
    }
    public void lianjie(){
        int pid = iAddGoodsListener.getpid();
        int uid = iAddGoodsListener.getuid();
        String token = iAddGoodsListener.gettoken();
        addGoodsModel.getData(uid, pid, token, new IAddGoodsModel() {
            @Override
            public void onSuccess(AddGoodsBean bean) {
                iAddGoodsListener.getdata(bean);
            }
        });
    }
}
