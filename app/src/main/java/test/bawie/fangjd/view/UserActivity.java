package test.bawie.fangjd.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.UserinfoBean;
import test.bawie.fangjd.model.net.OkHttpUtils;
import test.bawie.fangjd.presenter.ShangChuanPresenter;
import test.bawie.fangjd.presenter.UserInfoPresenter;

public class UserActivity extends BaseActivity implements View.OnClickListener, IUserListener ,IShangChuanListener{

    private ImageView mIvExit;
    /**
     * 京东登录
     */
    private TextView mTvTitle;
    private ImageView mIvTouxiang;
    /**
     * 退出登录
     */
    private Button mBtExit;
    /**
     * 确定
     */
    private Button mBtTrue;
    /**
     * 45679
     */
    private TextView mTvPhone;
    private SharedPreferences sharedPreferences;
    private String token;
    private int uid;
    /**
     * 上传头像
     */
    private Button mShangchan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        uid = sharedPreferences.getInt("uid", 0);
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(this);
        userInfoPresenter.lianjie();
    }

    private void initView() {
        mIvExit = (ImageView) findViewById(R.id.iv_exit);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvTouxiang = (ImageView) findViewById(R.id.iv_touxiang);
        mBtExit = (Button) findViewById(R.id.bt_exit);
        mBtExit.setOnClickListener(this);
        mTvTitle.setText("用户信息");
        mIvExit.setOnClickListener(this);
        mIvTouxiang.setOnClickListener(this);
        mBtTrue = (Button) findViewById(R.id.bt_true);
        mBtTrue.setOnClickListener(this);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);

        mShangchan = (Button) findViewById(R.id.shangchan);
        mShangchan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_exit:

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                intent(UserActivity.this, MainActivity.class);
                toast("退出成功");

                break;
            case R.id.iv_exit:
                intent(UserActivity.this, MainActivity.class);
                break;
            case R.id.iv_touxiang:
                String url = "https://b-ssl.duitang.com/uploads/item/201709/16/20170916125258_zFG5X.thumb.700_0.jpeg";
                OkHttpUtils.getInstance(this).download(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mIvTouxiang.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
                break;
            case R.id.bt_true:
                intent(UserActivity.this, MainActivity.class);
                break;
            case R.id.shangchan:

                ShangChuanPresenter shangChuanPresenter = new ShangChuanPresenter(this);
                shangChuanPresenter.lianjie();
                break;
        }
    }

    @Override
    public String token() {
        return token;
    }

    @Override
    public int uid() {
        return uid;
    }

    @Override
    public void getdata(UserinfoBean bean) {

        String mobile = bean.getData().getMobile();
        mTvPhone.setText(mobile);

    }

    @Override
    public String getuid() {
        return uid+"";
    }

    @Override
    public String gettoken() {
        return token;
    }

    @Override
    public void getShang(String s) {
        toast(s);
    }
}
