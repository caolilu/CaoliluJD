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
import test.bawie.fangjd.model.bean.FenYouMainBean;



public class RvMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<FenYouMainBean.DataBean> list;

    private OnClicklistener onClicklistener;
    public interface OnClicklistener{
        public void OnItemClick(FenYouMainBean.DataBean bean);
    }
    public void setOnClicklistener(OnClicklistener onClicklistener){
        this.onClicklistener = onClicklistener;
    }
    public RvMainAdapter(Context context, List<FenYouMainBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_child_main, parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tv_main.setText(list.get(position).getTitle());
        viewHolder.tv_child_price.setText("￥"+list.get(position).getPrice());

        String images = list.get(position).getImages();
        String[] imgArr = images.split("!");
        Picasso.with(context).load(imgArr[0]).into(viewHolder.iv_child);

        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicklistener.OnItemClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_main;
        private final ImageView iv_child;
        private final TextView tv_child_price;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_main = itemView.findViewById(R.id.tv_chile_main);
            iv_child = itemView.findViewById(R.id.iv_child);
            tv_child_price = itemView.findViewById(R.id.tv_child_price);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
