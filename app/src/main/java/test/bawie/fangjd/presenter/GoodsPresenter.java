package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.GoodsModel;
import test.bawie.fangjd.model.IGoodsModel;
import test.bawie.fangjd.model.bean.GoodsBean;
import test.bawie.fangjd.view.IGoodsListener;


public class GoodsPresenter {

    private final GoodsModel goodsModel;
    private IGoodsListener iGoodsListener;
    public GoodsPresenter(IGoodsListener iGoodsListener){
        this.iGoodsListener = iGoodsListener;
        goodsModel = new GoodsModel();
    }
    public void lianjie(){
        int pid = iGoodsListener.getPid();
        goodsModel.getoods(pid, new IGoodsModel() {
            @Override
            public void onSuccess(GoodsBean bean) {
                iGoodsListener.getdata(bean);
            }
        });
    }
}
