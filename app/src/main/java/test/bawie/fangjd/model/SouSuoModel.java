package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.SouSuoBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class SouSuoModel {

    public void getData(String keywords, final ISouSuoModel isousuomodel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.SOUSUO);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<SouSuoBean> observable = apiService.getsousuo(keywords, 1, "android");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<SouSuoBean, SouSuoBean>() {
                    @Override
                    public SouSuoBean call(SouSuoBean souSuoBean) {
                        return souSuoBean;
                    }
                }).subscribe(new Subscriber<SouSuoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SouSuoBean souSuoBean) {
                isousuomodel.onsuccess(souSuoBean);
            }
        });

    }
}
