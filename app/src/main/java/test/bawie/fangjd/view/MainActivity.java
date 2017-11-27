package test.bawie.fangjd.view;

import android.graphics.Color;
import android.os.Bundle;

import com.hjm.bottomtabbar.BottomTabBar;

import test.bawie.fangjd.R;
import test.bawie.fangjd.view.fragment.FaXianFragment;
import test.bawie.fangjd.view.fragment.FenLeiFragment;
import test.bawie.fangjd.view.fragment.GouWuFragment;
import test.bawie.fangjd.view.fragment.ShouYeFragment;
import test.bawie.fangjd.view.fragment.WoDeFragment;

public class MainActivity extends BaseActivity {

    private BottomTabBar mBott;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mBott = (BottomTabBar) findViewById(R.id.bott);
        mBott.init(getSupportFragmentManager())
                .setImgSize(150,150)
                .setFontSize(0)
                .setTabPadding(4,6,3)
                .setChangeColor(Color.RED,Color.DKGRAY)
                .addTabItem(null,R.mipmap.ac1, ShouYeFragment.class)
                .addTabItem(null,R.mipmap.abx, FenLeiFragment.class)
                .addTabItem(null,R.mipmap.abz, FaXianFragment.class)
                .addTabItem(null,R.mipmap.abv, GouWuFragment.class)
                .addTabItem(null,R.mipmap.ac3, WoDeFragment.class)
                .isShowDivider(true)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }

}
