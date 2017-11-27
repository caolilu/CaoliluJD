package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.ShouYeLunboBean;



public class MiaoShaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<ShouYeLunboBean.MiaoshaBean.ListBeanX> list;

    public MiaoShaAdapter(Context context, List<ShouYeLunboBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_miaosha, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.tv_miaosha.setText("￥"+list.get(position).getPrice());
        String images = list.get(position).getImages();
        String[] imgArr = images.split("!");
        Picasso.with(context).load(imgArr[0]).into(viewHolder.iv_miaosha);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_miaosha;
        private final ImageView iv_miaosha;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_miaosha = itemView.findViewById(R.id.tv_miaosha);
            iv_miaosha = itemView.findViewById(R.id.iv_miaosha);
        }
    }
}
