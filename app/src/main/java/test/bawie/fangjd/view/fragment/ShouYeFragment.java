package test.bawie.fangjd.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.adapter.MiaoShaAdapter;
import test.bawie.fangjd.model.adapter.TuiJianAdapter;
import test.bawie.fangjd.model.app.GlideImageLoder;
import test.bawie.fangjd.model.bean.FenZuoBean;
import test.bawie.fangjd.model.bean.ShouYeLunboBean;
import test.bawie.fangjd.presenter.FZPresenter;
import test.bawie.fangjd.presenter.ShowYePresenter;
import test.bawie.fangjd.view.GoodsActivity;
import test.bawie.fangjd.view.SouSuoActivity;



public class ShouYeFragment extends Fragment implements IShowyeListener, IFenLeiListener, View.OnClickListener {
    private View view;
    private Banner mBanner;
    private List<String> list;
    private ShowYePresenter showYePresenter;
    private List<String> list2;
    private String icon;
    private String title;
    private RecyclerView mRv;
    private FZPresenter fzPresenter;
    private RecyclerView mRv2;
    private RecyclerView mRv3;
    /**
     * 都市丽人领劵满200减100
     */
    private EditText mEtSousuo;
    private ImageView mIvSao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        showYePresenter = new ShowYePresenter(this, getActivity());
        showYePresenter.getlunbo();
        fzPresenter = new FZPresenter(this, getActivity());
        fzPresenter.lianjie();
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        mRv2 = (RecyclerView) view.findViewById(R.id.rv2);
        mRv3 = (RecyclerView) view.findViewById(R.id.rv3);
        mEtSousuo = (EditText) view.findViewById(R.id.et_sousuo);

        mEtSousuo.setOnClickListener(this);
        mIvSao = (ImageView) view.findViewById(R.id.iv_sao);
        mIvSao.setOnClickListener(this);
    }

    @Override
    public void setlunbo(ShouYeLunboBean bean) {
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        List<ShouYeLunboBean.DataBean> data = bean.getData();
        for (int i = 0; i < data.size(); i++) {
            icon = data.get(i).getIcon();
            title = data.get(i).getTitle();
            list.add(icon);
            list2.add(title);
        }
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoder());
        //设置图片集合
        mBanner.setImages(list);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.CubeIn);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(list2);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();

        mRv2.setLayoutManager(new GridLayoutManager(getActivity(), bean.getMiaosha().getList().size()));
        MiaoShaAdapter miaoShaAdapter = new MiaoShaAdapter(getActivity(), bean.getMiaosha().getList());
        mRv2.setAdapter(miaoShaAdapter);

        mRv3.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        TuiJianAdapter tuiJianAdapter = new TuiJianAdapter(getActivity(), bean.getTuijian().getList());
        mRv3.setAdapter(tuiJianAdapter);
        tuiJianAdapter.setOnClickListener(new TuiJianAdapter.OnClickListener() {


            @Override
            public void onClick(ShouYeLunboBean.TuijianBean.ListBean bean) {
                int pid = bean.getPid();
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("pid", pid);
                getActivity().startActivity(intent);
                Toast.makeText(getActivity(), bean.getPid() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void getdata(FenZuoBean bean) {
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), bean.getData().size()));
        //RvAdapter adapter = new RvAdapter(getActivity(), bean.getData());
     //   mRv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_sousuo:
                Intent intent = new Intent(getActivity(), SouSuoActivity.class);
                startActivity(intent);
                break;
//            case R.id.iv_sao:
//
//                break;
            case R.id.iv_sao:
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);
                break;
        }
    }
}
