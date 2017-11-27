package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.GetMMBean;



public interface IGetMMListener {
    public String uid();
    public String token();
    public void getMM(GetMMBean bean);
}
