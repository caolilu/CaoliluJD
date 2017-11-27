package test.bawie.fangjd.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import test.bawie.fangjd.R;
import test.bawie.fangjd.view.fragment.GouWuFragment;

public class KongActivity extends AppCompatActivity {

    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kong);
        initView();
    }

    private void initView() {
        mLl = (LinearLayout) findViewById(R.id.ll);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.ll,new GouWuFragment());
        fragmentTransaction.commit();
    }
}
