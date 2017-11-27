package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.DanBean;


public class DanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<DanBean.DataBean> list;

    public DanAdapter(Context context, List<DanBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_dan, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tv_ddname.setText(list.get(position).getOrderid()+"");
        viewHolder.tv_ddprice.setText("￥"+list.get(position).getPrice());
        viewHolder.tv_ddtime.setText(list.get(position).getCreatetime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_ddname;
        private final TextView tv_ddprice;
        private final TextView tv_ddtime;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_ddname = itemView.findViewById(R.id.tv_ddname);
            tv_ddprice = itemView.findViewById(R.id.tv_ddprice);
            tv_ddtime = itemView.findViewById(R.id.tv_ddtime);
        }
    }
}
