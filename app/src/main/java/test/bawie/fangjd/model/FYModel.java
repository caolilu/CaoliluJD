package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.FenYouBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class FYModel {

    public void getData(final IFYModel ifymodel,int cid) {
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.FENYOU);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<FenYouBean> observable = apiService.getNoParams(cid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<FenYouBean, FenYouBean>() {
                    @Override
                    public FenYouBean call(FenYouBean fenYouBean) {
                        return fenYouBean;
                    }
                }).subscribe(new Subscriber<FenYouBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FenYouBean fenYouBean) {
                ifymodel.onSuccess(fenYouBean);
            }
        });
    }
}
