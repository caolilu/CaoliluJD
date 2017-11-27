package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.TianJiaBean;



public interface TianJiaListener {
    public int uid();
    public String addr();
    public String phone();
    public String name();
    public String token();
    public void getData(TianJiaBean bean);
}
