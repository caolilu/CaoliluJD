package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.AddrsBean;



public interface IAddresListener {
    public int getUid();
    public String token();
    public void setData(AddrsBean bean);
}
