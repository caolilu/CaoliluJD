package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.UserinfoBean;



public interface IUserListener {
    public String token();
    public int uid();
    public void getdata(UserinfoBean bean);
}
