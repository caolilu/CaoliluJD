package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.RegBean;



public interface IZhuceListener {
    public String getName();
    public String getpwd();
    public void zhuce(RegBean regBean);
}
