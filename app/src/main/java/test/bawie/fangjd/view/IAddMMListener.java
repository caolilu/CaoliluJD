package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.AddMMBean;



public interface IAddMMListener {
    public int uid();
    public String addrid();
    public String status();
    public String token();
    public void getMMdata(AddMMBean bean);
}
