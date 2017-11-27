package test.bawie.fangjd.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.AddrsBean;
import test.bawie.fangjd.model.bean.DingDanBean;
import test.bawie.fangjd.model.bean.GetMMBean;
import test.bawie.fangjd.model.zhifu.PayDemoActivity;
import test.bawie.fangjd.presenter.AddrsPresenter;
import test.bawie.fangjd.presenter.DingDanPresenter;
import test.bawie.fangjd.presenter.GetMMPresenter;

public class DingDanActivity extends AppCompatActivity implements View.OnClickListener, IAddresListener, IDingDanListener {

    private ImageView mIvExit;
    /**
     * 京东登录
     */
    private TextView mTvTitle;
    private int uid;
    private String token;
    /**
     * 地址设置
     */
    private Button mBtMr;
    private TextView mTvWw;
    /**
     * 立即购买
     */
    private Button mBtBuy;
    private int er;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        token = sharedPreferences.getString("token", "");
        er = sharedPreferences.getInt("er",0);


        //
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String addrs = intent.getStringExtra("addrs");
        long phone = intent.getLongExtra("phone", 10);
        Log.e("xxx", name + ":" + addrs + phone);




        //查询常用地址

        AddrsPresenter addrsPresenter = new AddrsPresenter(this);
        addrsPresenter.lianjie();

        if(er!=0){
            GetMMPresenter getMMPresenter = new GetMMPresenter(new IGetMMListener() {
                @Override
                public String uid() {
                    return uid+"";
                }

                @Override
                public String token() {
                    return token;
                }

                @Override
                public void getMM(GetMMBean bean) {


                        mTvWw.setText("用户名:" + bean.getData().getName() + "  地址:" + bean.getData().getAddr() + "  手机号:" + bean.getData().getMobile());


                        SharedPreferences user = getSharedPreferences("User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = user.edit();
                        edit.remove("er");
                        edit.commit();

                }
            }, this);
            getMMPresenter.lianjie();
        }
    }

    private void initView() {
        mIvExit = (ImageView) findViewById(R.id.iv_exit);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("确认订单");
        mIvExit.setOnClickListener(this);
        mBtMr = (Button) findViewById(R.id.bt_mr);
        mBtMr.setOnClickListener(this);
        mTvWw = (TextView) findViewById(R.id.tv_ww);

        mBtBuy = (Button) findViewById(R.id.bt_buy);
        mBtBuy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exit:
                finish();
                break;
            case R.id.bt_mr:
                SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("er",1);
                editor.commit();
                Intent intent = new Intent(DingDanActivity.this, ShouHuoActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.bt_buy:
                Intent intent1 = new Intent(DingDanActivity.this, PayDemoActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public int getUid() {
        return uid;
    }

    @Override
    public String price() {
        return 100 + "";
    }

    @Override
    public String uid() {
        return uid + "";
    }



    @Override
    public String token() {
        return token;
    }

    @Override
    public void setDingDan(DingDanBean bean) {


    }

    @Override
    public void setData(AddrsBean bean) {
        int size = bean.getData().size();
        if (size == 0) {
            new AlertDialog.Builder(DingDanActivity.this)
                    .setMessage("您还没有收货地址哦，赶快去设置一个吧")
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(context, "点了", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DingDanActivity.this, ShouHuoActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
        } else {
            //开始显示订单

            DingDanPresenter dingDanPresenter = new DingDanPresenter(this);
            dingDanPresenter.lianjie();
        }
    }
}
