package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.DeleteGoods;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class DeleteModel {

    public void getdata(int uid, int pid, String token, final IDeleteModel iDeleteModel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.DELETE);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<DeleteGoods> observable = apiService.deletegoods(uid, pid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<DeleteGoods, DeleteGoods>() {
                    @Override
                    public DeleteGoods call(DeleteGoods deleteGoods) {
                        return deleteGoods;
                    }
                }).subscribe(new Subscriber<DeleteGoods>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DeleteGoods deleteGoods) {
                iDeleteModel.onSuccess(deleteGoods);
            }
        });
    }
}
