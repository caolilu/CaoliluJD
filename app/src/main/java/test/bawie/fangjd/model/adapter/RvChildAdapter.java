package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.FenYouBean;


public class RvChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<FenYouBean.DataBean.ListBean> list;
    private OnItemListener onItemListener;


    public interface OnItemListener{
        public void  onItemClick(FenYouBean.DataBean.ListBean bean);
    }
    public void setonItemListen(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }
    public RvChildAdapter(Context context, List<FenYouBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_child, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.sim.setImageURI(Uri.parse(list.get(position).getIcon()));
        viewHolder.tv_child.setText(list.get(position).getName());
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemListener.onItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_child;
        private final SimpleDraweeView sim;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_child = itemView.findViewById(R.id.tv_child);
            sim = itemView.findViewById(R.id.sim);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
