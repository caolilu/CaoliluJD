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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.UserinfoBean;
import test.bawie.fangjd.presenter.GetInfoPresenter;
import test.bawie.fangjd.view.DingDanLiBiao;
import test.bawie.fangjd.view.LoginActivity;
import test.bawie.fangjd.view.UserActivity;


public class WoDeFragment extends Fragment implements View.OnClickListener, IWodeListener {
    private View view;
    private LinearLayout mLl;
    /**
     * 登录/注册 >
     */
    private TextView mTvName;
    private GetInfoPresenter getInfoPresenter;
    private int uid;
    /**
     * 我的订单
     */
    private TextView mTvMyOrder;

    private ImageView mIvHhh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wode, container, false);

        initView(view);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        if (uid != 0) {
            getInfoPresenter = new GetInfoPresenter(this, getActivity());
            getInfoPresenter.getinfo();
        }

        return view;
    }

    private void initView(View view) {
        mLl = (LinearLayout) view.findViewById(R.id.ll);
        mLl.setOnClickListener(this);
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mTvMyOrder = (TextView) view.findViewById(R.id.tv_myOrder);
        mTvMyOrder.setOnClickListener(this);
        mIvHhh = (ImageView) view.findViewById(R.id.iv_hhh);
        String trim = mTvName.getText().toString().trim();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll:
                String trim = mTvName.getText().toString().trim();
                if (trim.equals("登录/注册 >")) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {


                    Intent intent = new Intent(getActivity(), UserActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_myOrder:

                Intent intent = new Intent(getActivity(), DingDanLiBiao.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public int getuid() {
        return uid;
    }

    @Override
    public void setdata(UserinfoBean bean) {
        mTvName.setText("曹利璐的京东 >");
    }


}
