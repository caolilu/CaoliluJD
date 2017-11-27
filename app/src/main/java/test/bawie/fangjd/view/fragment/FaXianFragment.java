package test.bawie.fangjd.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import test.bawie.fangjd.R;



public class FaXianFragment extends Fragment {
    private View view;
    private WebView mWeb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faxian, container, false);

        initView(view);
      //  mWeb.loadUrl("https://user.qzone.qq.com/2579090273/infocenter");
        return view;
    }

    private void initView(View view) {
        mWeb = (WebView) view.findViewById(R.id.web);
    }
}
