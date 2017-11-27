package test.bawie.fangjd.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.LinkedList;



public abstract class BaseActivity extends AppCompatActivity{
    private LinkedList<Activity> list = new LinkedList<>();
    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        list.add(this);

    }


    protected void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    //关闭activity
    public void finshAll(){
        for (Activity ac:list) {
            if(!ac.isFinishing()){
                ac.finish();
            }
        }
    }
    //显示进度条
    protected void showPd() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在加載...");
        dialog.show();
    }
    //关闭进度条
    protected void dismisspd(){
        if(dialog!=null&&this.isFinishing()){
            dialog.dismiss();
        }
    }
    //跳转
    protected void intent(Activity ac,Class clazz){
        Intent intent = new Intent(ac,clazz);
        startActivity(intent);
        ac.finish();
    }
}
