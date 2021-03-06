package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.GoodsCarBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class GWCarModel {

    public void getData(int uid, String token, final IGWCarModel igwcarmodel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.GWCAR);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<GoodsCarBean> observable = apiService.gouwucar(uid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GoodsCarBean, GoodsCarBean>() {
                    @Override
                    public GoodsCarBean call(GoodsCarBean bean) {
                        return bean;
                    }
                }).subscribe(new Subscriber<GoodsCarBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodsCarBean bean) {
                if(igwcarmodel!=null){
                    igwcarmodel.onSuccess(bean);
                }
            }
        });
    }
}
