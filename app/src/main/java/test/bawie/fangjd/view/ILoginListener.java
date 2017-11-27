package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.LoginBean;



public interface ILoginListener {
    public String getName();
    public String getPwd();
    public void show(LoginBean bean);
}
