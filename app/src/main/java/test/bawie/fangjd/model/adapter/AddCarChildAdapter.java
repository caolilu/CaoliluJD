package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.GoodsCarBean;



public class AddCarChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<GoodsCarBean.DataBean.ListBean> listBeen;
    private MyViewHolder viewHolder;

    public AddCarChildAdapter(Context context, List<GoodsCarBean.DataBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_addcar_child, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewHolder = (MyViewHolder) holder;
        String images = listBeen.get(position).getImages();
        String[] imgArr = images.split("\\|");
        Picasso.with(context).load(imgArr[0]).into(viewHolder.iv_child);

        viewHolder.tv_child_name.setText(listBeen.get(position).getTitle());
        viewHolder.tv_child_price.setText("￥"+listBeen.get(position).getPrice());
        boolean checked = listBeen.get(position).isChecked();
        viewHolder.cb_child.setChecked(checked);
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb_child;
        private final ImageView iv_child;
        private final TextView tv_child_name;
        private final TextView tv_child_price;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb_child = itemView.findViewById(R.id.cb_child);
            iv_child = itemView.findViewById(R.id.iv_child);
            tv_child_name = itemView.findViewById(R.id.tv_child_name);
            tv_child_price = itemView.findViewById(R.id.tv_child_price);
        }
    }

    //点击子条目全选
    public void selectAll(boolean flag){
        for (int i = 0; i <listBeen.size() ; i++) {
            listBeen.get(i).setChecked(flag);
        }

        notifyDataSetChanged();
    }
}
