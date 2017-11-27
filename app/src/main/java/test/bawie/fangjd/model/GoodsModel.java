package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.GoodsBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class GoodsModel {
    public void getoods(int pid, final IGoodsModel iGoodsModel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.GOODS);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<GoodsBean> observable = apiService.getNoParams3(pid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GoodsBean, GoodsBean>() {
                    @Override
                    public GoodsBean call(GoodsBean goodsBean) {
                        return goodsBean;
                    }
                }).subscribe(new Subscriber<GoodsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodsBean goodsBean) {
                iGoodsModel.onSuccess(goodsBean);
            }
        });
    }
}
