package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.UserinfoBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;


public class UserInfoModel {

    public void getData(int uid, String token, final IUserInfoModel iUserInfoModel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.USER);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<UserinfoBean> observable = apiService.getuserinfo(uid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<UserinfoBean, UserinfoBean>() {
                    @Override
                    public UserinfoBean call(UserinfoBean bean) {
                        return bean;
                    }
                }).subscribe(new Subscriber<UserinfoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserinfoBean bean) {
                iUserInfoModel.onSuccess(bean);
            }
        });
    }
}
