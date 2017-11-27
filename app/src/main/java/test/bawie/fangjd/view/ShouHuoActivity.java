package test.bawie.fangjd.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.DiZhiAdapter;
import test.bawie.fangjd.model.bean.AddrsBean;
import test.bawie.fangjd.model.bean.TianJiaBean;
import test.bawie.fangjd.presenter.AddrsPresenter;
import test.bawie.fangjd.presenter.TianJiaPresentr;

public class ShouHuoActivity extends AppCompatActivity implements View.OnClickListener, IAddresListener, TianJiaListener {

    private ImageView mIvExit;
    /**
     * 京东登录
     */
    private TextView mTvTitle;
    private int uid;
    private String token;
    private EditText mEtPeople;
    private EditText mEtPhone;
    private EditText mEtAddrs;
    /**
     * 添加
     */
    private Button mBtAdd;
    private RecyclerView mRv;
    private DiZhiAdapter adapter;
    private String phone;
    private String name;

    private int er2;

    /**
     * 添加为默认地址
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_huo);
        initView();
    }

    private void initView() {
        mIvExit = (ImageView) findViewById(R.id.iv_exit);
        mIvExit.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("常用收货地址");
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        token = sharedPreferences.getString("token", "");
        phone = sharedPreferences.getString("phone", "");
        name = sharedPreferences.getString("name", "");
        er2 = sharedPreferences.getInt("er2", 0);
        AddrsPresenter addrsPresenter = new AddrsPresenter(this);
        addrsPresenter.lianjie();
        mEtPeople = (EditText) findViewById(R.id.et_people);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtAddrs = (EditText) findViewById(R.id.et_addrs);
        mBtAdd = (Button) findViewById(R.id.bt_add);
        mBtAdd.setOnClickListener(this);
        mRv = (RecyclerView) findViewById(R.id.rv);

//        if(er2!=0){
//            GetMMPresenter getMMPresenter = new GetMMPresenter(new IGetMMListener() {
//                @Override
//                public String uid() {
//                    return uid+"";
//                }
//
//                @Override
//                public String token() {
//                    return token;
//                }
//
//                @Override
//                public void getMM(GetMMBean bean) {
//
//                    mMmphone.setText(bean.getData().getMobile()+"");
//                    mMmpeople.setText(bean.getData().getName());
//                    mMmaddr.setText(bean.getData().getAddr());
//
//                    SharedPreferences user = getSharedPreferences("User", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor edit = user.edit();
//                    edit.remove("er2");
//                    edit.commit();
//                }
//            }, this);
//            getMMPresenter.lianjie();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_exit:
                finish();
                break;
            case R.id.bt_add:
                TianJiaPresentr tianJiaPresentr = new TianJiaPresentr(this);
                tianJiaPresentr.lianji();
                break;
        }
    }

    @Override
    public int getUid() {
        return uid;
    }

    @Override
    public int uid() {
        return uid;
    }


    @Override
    public String addr() {
        return mEtAddrs.getText().toString().trim();
    }

    @Override
    public String phone() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public String name() {
        return mEtPeople.getText().toString().trim();
    }

    @Override
    public String token() {
        return token;
    }


    @Override
    public void getData(TianJiaBean bean) {

        Toast.makeText(ShouHuoActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();

        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("er2",1);
        editor.commit();



    }

    @Override
    public void setData(AddrsBean bean) {
        mRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiZhiAdapter(this, bean.getData());
        mRv.setAdapter(adapter);


//        adapter.setonItemListen(new DiZhiAdapter.OnItemListener() {
//            @Override
//            public void onItemClick(AddrsBean.DataBean bean) {
//                Toast.makeText(ShouHuoActivity.this, "dia", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ShouHuoActivity.this, DingDanActivity.class);
//                intent.putExtra("name", bean.getName());
//                intent.putExtra("phone", bean.getMobile());
//                intent.putExtra("addrs", bean.getAddr());
//                startActivity(intent);
//                finish();
//            }
//        });
    }
}
