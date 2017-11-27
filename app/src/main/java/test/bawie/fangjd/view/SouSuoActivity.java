package test.bawie.fangjd.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.SouSuoAdapter;
import test.bawie.fangjd.model.bean.SouSuoBean;
import test.bawie.fangjd.presenter.SouSuoPresenter;

public class SouSuoActivity extends AppCompatActivity implements View.OnClickListener, ISouSuoLitener {

    /**
     * 都市丽人领劵满200减100
     */
    private EditText mEtSousuo;
    private RecyclerView mRv;
    private SouSuoPresenter souSuoPresenter;
    private String trim;
    private int pid;
    private ImageView mIvSousuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();

    }

    private void initView() {
        mEtSousuo = (EditText) findViewById(R.id.et_sousuo);

        mRv = (RecyclerView) findViewById(R.id.rv);
        souSuoPresenter = new SouSuoPresenter(this);
        mIvSousuo = (ImageView) findViewById(R.id.iv_sousuo);
        mIvSousuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_sousuo:
//                trim = mEtSousuo.getText().toString().trim();
//                souSuoPresenter.lianjie();
//                break;
            case R.id.iv_sousuo:
                trim = mEtSousuo.getText().toString().trim();
                souSuoPresenter.lianjie();
                break;
        }
    }

    @Override
    public String getKeywords() {
        if (trim.equals("")) {
            Toast.makeText(this, "不能输入空", Toast.LENGTH_SHORT).show();
        }
        return trim;
    }

    @Override
    public void setData(SouSuoBean bean) {
        mRv.setLayoutManager(new LinearLayoutManager(this));
        SouSuoAdapter adapter = new SouSuoAdapter(this, bean.getData());
        mRv.setAdapter(adapter);
        adapter.setOnClicklistener(new SouSuoAdapter.OnClicklistener() {


            @Override
            public void onclick(SouSuoBean.DataBean bean) {
                pid = bean.getPid();
                Intent intent = new Intent(SouSuoActivity.this, GoodsActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
            }
        });
    }
}
