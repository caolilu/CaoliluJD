package test.bawie.fangjd.view;

import test.bawie.fangjd.model.bean.DingDanBean;


public interface IDingDanListener {
    public String price();
    public String uid();
    public String token();
    public void setDingDan(DingDanBean bean);
}
