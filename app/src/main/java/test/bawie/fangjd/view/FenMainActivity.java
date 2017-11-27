package test.bawie.fangjd.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.RvMainAdapter;
import test.bawie.fangjd.model.bean.FenYouMainBean;
import test.bawie.fangjd.presenter.FYMainPresenter;
import test.bawie.fangjd.view.fragment.IFenYouMainListener;

public class FenMainActivity extends BaseActivity implements IFenYouMainListener {

    private XRecyclerView mRv;
    private FYMainPresenter fyMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fen_main);
        initView();
    }

    @Override
    public int getuid() {
        Intent intent = getIntent();
        int pscid = intent.getIntExtra("pscid", 1);
        return pscid;
    }

    @Override
    public void setData(FenYouMainBean bean) {
        RvMainAdapter adapter = new RvMainAdapter(FenMainActivity.this,bean.getData());
        mRv.setAdapter(adapter);
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                toast("刷新成功");
                mRv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                toast("加载更多");
                mRv.loadMoreComplete();
            }
        });
        adapter.setOnClicklistener(new RvMainAdapter.OnClicklistener() {
            @Override
            public void OnItemClick(FenYouMainBean.DataBean bean) {
                toast(bean.getPid()+"");
                //跳转商品详情页面
                Intent intent = new Intent(FenMainActivity.this,GoodsActivity.class);
                intent.putExtra("pid",bean.getPid());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mRv = (XRecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        fyMainPresenter = new FYMainPresenter(this, this);
        fyMainPresenter.lianjie();
    }
}
