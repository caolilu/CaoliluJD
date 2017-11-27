package test.bawie.fangjd.presenter;

import test.bawie.fangjd.model.IUserInfoModel;
import test.bawie.fangjd.model.UserInfoModel;
import test.bawie.fangjd.model.bean.UserinfoBean;
import test.bawie.fangjd.view.IUserListener;



public class UserInfoPresenter {

    private IUserListener iUserListener;
    private final UserInfoModel userInfoModel;

    public UserInfoPresenter(IUserListener iUserListener){
        this.iUserListener = iUserListener;
        userInfoModel = new UserInfoModel();
    }
    public void lianjie(){
        int uid = iUserListener.uid();
        String token = iUserListener.token();

        userInfoModel.getData(uid, token, new IUserInfoModel() {
            @Override
            public void onSuccess(UserinfoBean bean) {
                iUserListener.getdata(bean);
            }
        });
    }
}
