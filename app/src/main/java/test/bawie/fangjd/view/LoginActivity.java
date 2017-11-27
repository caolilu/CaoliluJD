package test.bawie.fangjd.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;

import java.util.Map;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.LoginBean;
import test.bawie.fangjd.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginListener {

    /**
     * 京东登录
     */
    private TextView mTvTitle;
    /**
     * 用户名/邮箱/手机号
     */
    private EditText mEtName;
    /**
     * 请输入密码
     */
    private EditText mEtPassword;
    /**
     * 登录
     */
    private Button mBtLogin;
    /**
     * 手机快速注册
     */
    private TextView mTvZhuce;
    /**
     * 忘记密码
     */
    private TextView mTvWangji;
    private LoginPresenter loginPresenter;
    private ImageView mIvExit;
    private ImageView mQq;
    private ImageView mShouquan;
    private ImageView mWeixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(this);
        mTvZhuce = (TextView) findViewById(R.id.tv_zhuce);
        mTvZhuce.setOnClickListener(this);
        mTvWangji = (TextView) findViewById(R.id.tv_wangji);
        mTvWangji.setOnClickListener(this);
        loginPresenter = new LoginPresenter(this);

        mIvExit = (ImageView) findViewById(R.id.iv_exit);
        mIvExit.setOnClickListener(this);
        mQq = (ImageView) findViewById(R.id.qq);
        mQq.setOnClickListener(this);
        mShouquan = (ImageView) findViewById(R.id.shouquan);
        mShouquan.setOnClickListener(this);
        mWeixin = (ImageView) findViewById(R.id.weixin);
        mWeixin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                loginPresenter.login();
                break;
            case R.id.tv_zhuce:
                Intent intent = new Intent(LoginActivity.this, ZhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_wangji:
                toast("等待开发。。。");
                break;
            case R.id.iv_exit:
                finish();
                break;
            case R.id.qq:
                ShareWeb(R.mipmap.ic_launcher);
                break;
            case R.id.shouquan:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.weixin:
                ShareWeb2(R.mipmap.jd_buy_icon);
                break;
        }
    }

    @Override
    public String getName() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtPassword.getText().toString().trim();
    }

    @Override
    public void show(LoginBean bean) {

        toast(bean.getMsg());
        if (bean.getCode().equals("0")) {
            SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", bean.getData().getToken());
            editor.putInt("uid", bean.getData().getUid());
            editor.putString("phone", bean.getData().getMobile());
            editor.putString("name", bean.getData().getUsername());
            editor.commit();
            intent(LoginActivity.this, MainActivity.class);
            ;
        }
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };
    //qq微信
    private void ShareWeb2(int thumb_img) {
        UMImage thumb = new UMImage(this, thumb_img);
        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setThumb(thumb);
        web.setDescription("测试一下");
        web.setTitle("分享");
        new ShareAction(this).withMedia(web).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(shareListener).share();
    }
    //qq分享
    private void ShareWeb(int thumb_img) {
        UMImage thumb = new UMImage(this, thumb_img);
        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setThumb(thumb);
        web.setDescription("测试一下");
        web.setTitle("分享");
        new ShareAction(this).withMedia(web).setPlatform(SHARE_MEDIA.QQ).setCallback(shareListener).share();
    }

    //qq授权

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Toast.makeText(LoginActivity.this, "开始了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            String s = data.toString();
            Log.e("xxx", s);
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
