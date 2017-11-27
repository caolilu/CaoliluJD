package test.bawie.fangjd.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.RegBean;
import test.bawie.fangjd.presenter.ZhucePresenter;

public class ZhuceActivity extends BaseActivity implements View.OnClickListener, IZhuceListener {

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
     * 注册
     */
    private Button mBtZhuce;
    private ZhucePresenter zhucePresenter;
    private ImageView mIvExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        initView();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtZhuce = (Button) findViewById(R.id.bt_zhuce);
        mBtZhuce.setOnClickListener(this);
        mTvTitle.setText("京东注册");
        zhucePresenter = new ZhucePresenter(this);

        mIvExit = (ImageView) findViewById(R.id.iv_exit);
        mIvExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_zhuce:
                zhucePresenter.zhuce();
                break;
            case R.id.iv_exit:
                finish();
                break;
        }
    }

    @Override
    public String getName() {
        return mEtName.getText().toString().trim();
    }

    @Override
    public String getpwd() {
        return mEtPassword.getText().toString().trim();
    }

    @Override
    public void zhuce(RegBean regBean) {
        toast(regBean.getMsg());
        String code = regBean.getCode();
        if (code.equals("0")) {
            intent(ZhuceActivity.this, LoginActivity.class);
        }

    }
}
