package test.bawie.fangjd.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.MyExAdapter;
import test.bawie.fangjd.model.bean.GoodsCarBean;
import test.bawie.fangjd.model.eventbus.MessageCounEvent;
import test.bawie.fangjd.model.eventbus.MessageEvent;
import test.bawie.fangjd.presenter.GWCarPresenter;
import test.bawie.fangjd.view.DingDanActivity;
import test.bawie.fangjd.view.LoginActivity;


public class GouWuFragment extends Fragment implements View.OnClickListener, IGouWuListener {
    private View view;
    /**
     * 请先登录
     */
    private TextView mTvGouwu;
    private LinearLayout mLl;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 总价
     */
    private TextView mTvNum;
    /**
     * 去结算
     */
    private Button mBtBuy;
    private GWCarPresenter gwCarPresenter;
    private int uid;
    private String token;
    private ExpandableListView mEx;
    private List<GoodsCarBean.DataBean> grouplist = new ArrayList<>();
    private List<List<GoodsCarBean.DataBean.ListBean>> childlist = new ArrayList<>();
    private int count;
    private double num;
    private MyExAdapter myExAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gouwu, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        //获取token
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        token = sharedPreferences.getString("token", "");

        if (token.equals("")) {
            mTvGouwu.setVisibility(View.VISIBLE);
            mLl.setVisibility(View.GONE);
        } else {
            mTvGouwu.setVisibility(View.GONE);
            mLl.setVisibility(View.VISIBLE);
            gwCarPresenter = new GWCarPresenter(this);
            gwCarPresenter.lianjie();
        }
        return view;
    }

    private void initView(View view) {
        mTvGouwu = (TextView) view.findViewById(R.id.tv_gouwu);
        mTvGouwu.setOnClickListener(this);
        mLl = (LinearLayout) view.findViewById(R.id.ll);
        mCbAll = (CheckBox) view.findViewById(R.id.cb_all);
        mCbAll.setOnClickListener(this);
        mTvNum = (TextView) view.findViewById(R.id.tv_num);
        mBtBuy = (Button) view.findViewById(R.id.bt_buy);
        mBtBuy.setOnClickListener(this);

        mEx = (ExpandableListView) view.findViewById(R.id.ex);
        mLl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gouwu:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.cb_all:
                count=0;
                num=0;
                myExAdapter.allChecked(mCbAll.isChecked());

                break;
            case R.id.bt_buy:
                Intent intent1 = new Intent(getActivity(), DingDanActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll:
                break;
        }
    }

    @Override
    public int uid() {
        return uid;
    }

    @Override
    public String token() {
        return token;
    }

    @Override
    public void getData(GoodsCarBean bean) {
        grouplist = bean.getData();
        for (int i = 0; i <grouplist.size() ; i++) {
            List<GoodsCarBean.DataBean.ListBean> list = grouplist.get(i).getList();
            childlist.add(list);
        }
        myExAdapter = new MyExAdapter(getActivity(), grouplist, childlist);
        mEx.setGroupIndicator(null);
        mEx.setAdapter(myExAdapter);

        //全部展开
        for (int i = 0; i < grouplist.size(); i++) {
            mEx.expandGroup(i);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void messageCountEvent(MessageCounEvent msg) {
        count+=msg.getCount();
        num += msg.getPrice();
        mBtBuy.setText("去结算(" + count + "个)");
        mTvNum.setText("总价:"+num+"元");
    }
    @Subscribe
    public void messageEvent(MessageEvent msg) {
        mCbAll.setChecked(msg.isFlag());
    }

}
