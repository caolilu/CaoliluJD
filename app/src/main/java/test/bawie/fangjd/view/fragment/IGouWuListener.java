package test.bawie.fangjd.view.fragment;

import test.bawie.fangjd.model.bean.GoodsCarBean;


public interface IGouWuListener {
    public int uid();
    public String token();
    public void getData(GoodsCarBean bean);
}
