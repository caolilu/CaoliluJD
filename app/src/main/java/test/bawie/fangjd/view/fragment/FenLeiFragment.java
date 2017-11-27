package test.bawie.fangjd.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.RvAdapter;
import test.bawie.fangjd.model.adapter.RvGrupeAdapter;
import test.bawie.fangjd.model.bean.FenYouBean;
import test.bawie.fangjd.model.bean.FenZuoBean;
import test.bawie.fangjd.presenter.FYPresenter;
import test.bawie.fangjd.presenter.FZPresenter;



public class FenLeiFragment extends Fragment implements IFenLeiListener, IFenYouListener {
    private View view;
    private RecyclerView mRv;
    private FZPresenter fzPresenter;
    private RvAdapter adapter;
    private int cid;
    private FYPresenter fyPresenter;
    private RecyclerView mRvGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fenlei, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        fzPresenter = new FZPresenter(this, getActivity());
        fzPresenter.lianjie();
        fyPresenter = new FYPresenter(getActivity(), this);
        cid=1;
        fyPresenter.lianjie();
        mRvGroup = (RecyclerView) view.findViewById(R.id.rv_group);
    }

    @Override
    public void getdata(FenZuoBean bean) {
        adapter = new RvAdapter(getActivity(), bean.getData());
        mRv.setAdapter(adapter);
        adapter.setOnClicklistener(new RvAdapter.OnClicklistener() {
            @Override
            public void OnItemClick(FenZuoBean.DataBean bean) {
                Toast.makeText(getActivity(), bean.getCid() + "", Toast.LENGTH_SHORT).show();
                cid = bean.getCid();
                fyPresenter.lianjie();
            }
        });
    }

    @Override
    public int getuid() {
        return cid;
    }

    @Override
    public void setData(FenYouBean bean) {
        mRvGroup.setLayoutManager(new LinearLayoutManager(getActivity()));
        RvGrupeAdapter adapter = new RvGrupeAdapter(getActivity(), bean.getData());
        mRvGroup.setAdapter(adapter);

    }
}
