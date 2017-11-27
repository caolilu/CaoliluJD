package test.bawie.fangjd.model;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import test.bawie.fangjd.model.bean.ShangChuanBean;
import test.bawie.fangjd.model.net.Api;
import test.bawie.fangjd.model.net.ApiService;
import test.bawie.fangjd.model.net.RetrofitUtils;



public class ShangChuanModel {
    public void getData(String uid, String token, final IShangChuanModel iShangChuanModel){
        Retrofit retrofit = RetrofitUtils.getRetrofit(Api.GETFI);
        ApiService apiService = retrofit.create(ApiService.class);

        File file = new File("/mnt/shared/Image/r.jpg");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody uids = RequestBody.create(MediaType.parse("multipart/form-data"), uid+"");
        RequestBody tokens = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Observable<ShangChuanBean> observable = apiService.uploadPic(uids, body,tokens);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<ShangChuanBean, ShangChuanBean>() {
                    @Override
                    public ShangChuanBean call(ShangChuanBean bean) {
                        return bean;
                    }
                }).subscribe(new Subscriber<ShangChuanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShangChuanBean bean) {

                iShangChuanModel.onSuccess(bean.getMsg());
            }
        });

    }
}
