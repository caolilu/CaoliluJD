package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.ShouYeLunboBean;

public class TuiJianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<ShouYeLunboBean.TuijianBean.ListBean> list;

    private OnClickListener onClickListener;
    public interface OnClickListener{
            public void onClick(ShouYeLunboBean.TuijianBean.ListBean bean);
    }
    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public TuiJianAdapter(Context context, List<ShouYeLunboBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_tuijian, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.tv_tuijian_price.setText("￥"+list.get(position).getPrice());
        viewHolder.tv_tuijian_name.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String[] imgArr = images.split("!");
        Picasso.with(context).load(imgArr[0]).into(viewHolder.iv_tuijian);
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_tuijian_name;
        private final TextView tv_tuijian_price;
        private final ImageView iv_tuijian;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_tuijian_name = itemView.findViewById(R.id.tv_tuijian_name);
            tv_tuijian_price = itemView.findViewById(R.id.tv_tuijian_price);
            iv_tuijian = itemView.findViewById(R.id.iv_tuijian);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
