package test.bawie.fangjd.presenter;

import android.content.Context;

import test.bawie.fangjd.model.IShanchuModel;
import test.bawie.fangjd.model.ShanchuModel;
import test.bawie.fangjd.model.bean.ShanchuBean;
import test.bawie.fangjd.view.IShanchuView;

/**
 * Created by cll on 2017/11/20.
 */

public class ShanchuPresenter {
    private Context context;
    private IShanchuView iShanchuView;
    private final ShanchuModel shanchuModel;

    public ShanchuPresenter(Context context, IShanchuView iShanchuView) {
        this.context = context;
        this.iShanchuView = iShanchuView;
        shanchuModel = new ShanchuModel(context);
    }

    public void getWuxiian() {
        int uid = iShanchuView.uid();
        int pid = iShanchuView.pscid();
        shanchuModel.shouye(new IShanchuModel() {
            @Override
            public void onsuccess(ShanchuBean shanchuBean) {
                iShanchuView.getBean(shanchuBean);
            }
        }, uid, pid);
    }
}
