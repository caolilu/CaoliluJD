package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.FenYouMainBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class FYMainModel {

    public void getData(final IFYMainModel ifymodel,int cid) {
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.FENYOUMAIN);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FenYouMainBean> observable = apiService.getNoParams2(cid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<FenYouMainBean, FenYouMainBean>() {
                    @Override
                    public FenYouMainBean call(FenYouMainBean fenYouMainBean) {
                        return fenYouMainBean;
                    }
                }).subscribe(new Subscriber<FenYouMainBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FenYouMainBean fenYouBean) {
                ifymodel.onSuccess(fenYouBean);
            }
        });
    }
}
