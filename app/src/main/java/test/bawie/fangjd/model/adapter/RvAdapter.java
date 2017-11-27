package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.FenZuoBean;



public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<FenZuoBean.DataBean> list;

    private OnClicklistener onClicklistener;
    public interface OnClicklistener{
        public void OnItemClick(FenZuoBean.DataBean bean);
    }
    public void setOnClicklistener(OnClicklistener onClicklistener){
        this.onClicklistener = onClicklistener;
    }
    public RvAdapter(Context context, List<FenZuoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_fenlei_fenzuo, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tv_fz.setText(list.get(position).getName());
    //    Picasso.with(context).load(list.get(position).getIcon()).placeholder(R.mipmap.ic_launcher).into(viewHolder.iv_pic);
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClicklistener!=null){
                    onClicklistener.OnItemClick(list.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_fz;
        private final ImageView iv_pic;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_fz = itemView.findViewById(R.id.tv_fz);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
