package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.GoodsCarBean;

public class AddCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<GoodsCarBean.DataBean> list;
    private AddCarChildAdapter addCarChildAdapter;

    public AddCarAdapter(Context context, List<GoodsCarBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_addcar_group, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        //得到
        viewHolder.cb_group.setChecked(list.get(position).isChecked());
        viewHolder.tv_sahgjia.setText(list.get(position).getSellerName());

        viewHolder.rv_child.setLayoutManager(new LinearLayoutManager(context));



        //子布局
        addCarChildAdapter = new AddCarChildAdapter(context, list.get(position).getList());
        viewHolder.rv_child.setAdapter(addCarChildAdapter);

        viewHolder.cb_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                boolean checked = viewHolder.cb_group.isChecked();
                addCarChildAdapter.selectAll(checked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb_group;
        private final TextView tv_sahgjia;
        private final RecyclerView rv_child;

        public MyViewHolder(View itemView) {
            super(itemView);
            cb_group = itemView.findViewById(R.id.cb_group);
            tv_sahgjia = itemView.findViewById(R.id.tv_shangjia);
            rv_child = itemView.findViewById(R.id.rv_child);
        }
    }

    //点击全选,取消全选
    public void selectAll(boolean flag){
        for (int i = 0; i <list.size() ; i++) {
            list.get(i).setChecked(flag);
        }
        notifyDataSetChanged();
    }

}
