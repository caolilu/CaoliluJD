package test.bawie.fangjd.model.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import test.bawie.fangjd.view.fragment.FaXianFragment;
import test.bawie.fangjd.view.fragment.FenLeiFragment;
import test.bawie.fangjd.view.fragment.GouWuFragment;
import test.bawie.fangjd.view.fragment.ShouYeFragment;
import test.bawie.fangjd.view.fragment.WoDeFragment;



public class PagerAdapter extends FragmentPagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new ShouYeFragment();

                break;
            case 1:
                fragment = new FenLeiFragment();
                break;
            case 2:
                fragment = new FaXianFragment();
                break;
            case 3:
                fragment = new GouWuFragment();
                break;
            case 4:
                fragment = new WoDeFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
