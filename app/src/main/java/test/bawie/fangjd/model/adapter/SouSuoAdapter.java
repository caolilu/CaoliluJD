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
import test.bawie.fangjd.model.bean.SouSuoBean;



public class SouSuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<SouSuoBean.DataBean> list;
    private OnClicklistener onClicklistener;
    public interface OnClicklistener{
        public void onclick(SouSuoBean.DataBean bean);
    }
    public void setOnClicklistener(OnClicklistener onClicklistener){
        this.onClicklistener = onClicklistener;
    }
    public SouSuoAdapter(Context context, List<SouSuoBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_sousuo, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String images = list.get(position).getImages();
        String[] imgArr = images.split("\\|");
        Picasso.with(context).load(imgArr[0]).into(myViewHolder.iv_ss);
        myViewHolder.tv_ss.setText(list.get(position).getTitle());
        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClicklistener.onclick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_ss;
        private final TextView tv_ss;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_ss = itemView.findViewById(R.id.iv_ss);
            tv_ss = itemView.findViewById(R.id.tv_ss);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
