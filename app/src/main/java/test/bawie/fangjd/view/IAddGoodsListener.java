package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.AddGoodsBean;



public interface IAddGoodsListener {
    public int getuid();
    public int getpid();
    public String gettoken();
    public void getdata(AddGoodsBean bean);
}
