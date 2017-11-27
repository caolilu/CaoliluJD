package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.DeleteGoods;


public interface IDeleteListener {
    public int getUid();
    public int getPid();
    public String getToken();
    public void getData(DeleteGoods bean);
}
