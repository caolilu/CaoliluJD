package test.bawie.fangjd.model;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.AddGoodsBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class AddGoodsModel{

    public void getData(int uid, int pid, String token, final IAddGoodsModel iAddGoodsModel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.ADDGOODS);
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<AddGoodsBean> observable = apiService.addgoods(uid, pid, token);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<AddGoodsBean, AddGoodsBean>() {
                    @Override
                    public AddGoodsBean call(AddGoodsBean addGoodsBean) {
                        return addGoodsBean;
                    }
                }).subscribe(new Subscriber<AddGoodsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AddGoodsBean addGoodsBean) {
                iAddGoodsModel.onSuccess(addGoodsBean);
            }
        });
    }
}
