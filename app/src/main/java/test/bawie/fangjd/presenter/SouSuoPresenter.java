package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.ISouSuoModel;
import test.bawie.fangjd.model.SouSuoModel;
import test.bawie.fangjd.model.bean.SouSuoBean;
import test.bawie.fangjd.view.ISouSuoLitener;



public class SouSuoPresenter {
    private ISouSuoLitener iSouSuoLitener;
    private final SouSuoModel souSuoModel;

    public SouSuoPresenter(ISouSuoLitener iSouSuoLitener){
        this.iSouSuoLitener = iSouSuoLitener;
        souSuoModel = new SouSuoModel();
    }
    public void lianjie(){
        String keywords = iSouSuoLitener.getKeywords();
        souSuoModel.getData(keywords, new ISouSuoModel() {
            @Override
            public void onsuccess(SouSuoBean bean) {
                iSouSuoLitener.setData(bean);
            }
        });
    }
}
