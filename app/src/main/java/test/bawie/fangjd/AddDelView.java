package test.bawie.fangjd;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class AddDelView extends LinearLayout{

    private int count = 1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        count =1;
        num.setText(count+"");
    }

    private OnItemClick onItemClick;
    private TextView num;

    public interface OnItemClick{
        void onItemAddClick(int count);
        void onItemDelClick(int count);
    }
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    public AddDelView(Context context) {
        this(context,null);
    }

    public AddDelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.zidingyi_jj, this);
        TextView add = findViewById(R.id.add);
        TextView del = findViewById(R.id.del);
        num = findViewById(R.id.num);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num.setText(++count+"");
                onItemClick.onItemAddClick(1);
            }
        });

        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count!=1){
                    count = --count;
                    onItemClick.onItemDelClick(-1);
                }
                num.setText(count>=1 ? count+"" : 1+"");
            }
        });
    }


}
