package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.AddMMBean;
import test.bawie.fangjd.model.bean.AddrsBean;
import test.bawie.fangjd.presenter.AddMMPresenter;
import test.bawie.fangjd.view.DingDanActivity;
import test.bawie.fangjd.view.IAddMMListener;


public class DiZhiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<AddrsBean.DataBean> list;
    private OnItemListener onItemListener;
    private int uid;
    private String token;


    public interface OnItemListener{
        public void  onItemClick(AddrsBean.DataBean bean);
    }
    public void setonItemListen(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }
    public DiZhiAdapter(Context context, List<AddrsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_dizhi, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        final AddrsBean.DataBean dataBean = list.get(position);
        viewHolder.tv_name.setText(dataBean.getName());
        viewHolder.tv_addrs.setText(dataBean.getAddr());
        viewHolder.tv_phone.setText(dataBean.getMobile()+"");
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        uid = sharedPreferences.getInt("uid", 0);
        token = sharedPreferences.getString("token", "");
        viewHolder.bt_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMMPresenter addMMPresenter = new AddMMPresenter(new IAddMMListener() {
                    @Override
                    public int uid() {
                        return uid;
                    }

                    @Override
                    public String addrid() {
                        return dataBean.getAddrid()+"";
                    }

                    @Override
                    public String status() {
                        return 1+"";
                    }

                    @Override
                    public String token() {
                        return token;
                    }

                    @Override
                    public void getMMdata(AddMMBean bean) {
                        Toast.makeText(context,bean.getMsg(),Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, DingDanActivity.class);
                        context.startActivity(intent);

                    }
                },context);
                addMMPresenter.lianjie();
            }
        });


        viewHolder.muoren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"等待开发",Toast.LENGTH_SHORT).show();

            }
        });

        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemListener!=null){
                    onItemListener.onItemClick(dataBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final TextView tv_phone;
        private final TextView tv_addrs;
        private final LinearLayout ll;
        private final Button bt_xiugai;
        private final Button muoren;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_addrs = itemView.findViewById(R.id.tv_addrs);
            ll = itemView.findViewById(R.id.ll);
            bt_xiugai = itemView.findViewById(R.id.xiugai);
            muoren = itemView.findViewById(R.id.muoren);
        }
    }
    public void shuaxin(int postion){
        notifyItemInserted(postion);
    }
}
