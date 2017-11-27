package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.DeleteModel;
import test.bawie.fangjd.model.IDeleteModel;
import test.bawie.fangjd.model.bean.DeleteGoods;
import test.bawie.fangjd.view.IDeleteListener;


public class DeletePresenter {

    private IDeleteListener iDeleteListener;
    private final DeleteModel deleteModel;

    public DeletePresenter(IDeleteListener iDeleteListener){
        this.iDeleteListener = iDeleteListener;
        deleteModel = new DeleteModel();
    }
    public void lianjie(){
        int pid = iDeleteListener.getPid();
        int uid = iDeleteListener.getUid();
        String token = iDeleteListener.getToken();
        deleteModel.getdata(uid, pid, token, new IDeleteModel() {
            @Override
            public void onSuccess(DeleteGoods bean) {
                iDeleteListener.getData(bean);
            }
        });
    }
}
