package test.bawie.fangjd.model.net;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    //单例模式，懒汉式
    private static OkHttpUtils okHttpUtils;
    private final OkHttpClient client;
    private Context context;
    private Handler handler = new Handler(Looper.getMainLooper());
    private OkHttpUtils(Context context){
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(log)
                .build();
        this.context = context;
    }
    public static OkHttpUtils getInstance(Context context){
        if(okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils==null){
                    okHttpUtils = new OkHttpUtils(context);
                }
            }
        }
        return okHttpUtils;
    }

    //doget请求
    public void doget(String url, final Class clazz, final OnNetListener onNetListener){

        //网络判断
        if (!NetWorkUtil.isNetworkAvailable(context)) {
            Toast.makeText(context, "没有网络，请查看设置", Toast.LENGTH_SHORT).show();
            return;
        }

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    onNetListener.onError(e);
            }

            @Override
            public void onResponse(Call call,Response response) throws IOException {
                final String string = response.body().string();
                final Object o = new Gson().fromJson(string, clazz);
                handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                onNetListener.onSuccess(o);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });


            }
        });
    }

    //dopost请求
    public void doPost(String url, Map<String,String> params, final Class clazz, final OnNetListener onNetListener){
        //网络判断
        if (!NetWorkUtil.isNetworkAvailable(context)) {
            Toast.makeText(context, "没有网络，请查看设置", Toast.LENGTH_SHORT).show();
            return;
        }
        if(params != null && params.size() > 0){


            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            FormBody formBody = builder.build();

        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final Object o = new Gson().fromJson(response.body().string(), clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            onNetListener.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
    }

    //下载
    public void download(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * 上传
     *
     * @param url
     * @param fileName
     */
    public void uploadFile(String url, String fileName,String uid,String token,final Class clazz, final OnNetListener onNetListener) {
        String file = Environment.getExternalStorageState() + "/" + fileName;
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //创建RequestBody 设置类型等
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, fileBody).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                final Object o = new Gson().fromJson(string, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            onNetListener.onSuccess(o);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
