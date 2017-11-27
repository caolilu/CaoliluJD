package test.bawie.fangjd.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.DanAdapter;
import test.bawie.fangjd.model.bean.DanBean;
import test.bawie.fangjd.presenter.DanPresenter;

public class DingDanLiBiao extends AppCompatActivity implements IDanListener{

    private RecyclerView mRv;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan_li_biao);
        initView();
    }

    private void initView() {
        mRv = (RecyclerView) findViewById(R.id.rv);

        DanPresenter danPresenter = new DanPresenter(this);
        danPresenter.lianjie();
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        String token = sharedPreferences.getString("token", "");
    }

    @Override
    public String uid() {
        return uid+"";
    }

    @Override
    public void setDan(DanBean bean) {
            mRv.setLayoutManager(new LinearLayoutManager(this));
            DanAdapter danAdapter = new DanAdapter(DingDanLiBiao.this,bean.getData());
            mRv.setAdapter(danAdapter);

    }
}
