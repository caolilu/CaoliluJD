package test.bawie.fangjd.model.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.FenYouBean;
import test.bawie.fangjd.presenter.FYMainPresenter;
import test.bawie.fangjd.view.FenMainActivity;


public class RvGrupeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<FenYouBean.DataBean> list;
    private FYMainPresenter fyMainPresenter;
    private int pscid;
    public RvGrupeAdapter(Context context, List<FenYouBean.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_group, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.tv_group.setText(list.get(position).getName());
            viewHolder.rv_child.setLayoutManager(new GridLayoutManager(context,3));
            final RvChildAdapter adapter = new RvChildAdapter(context, list.get(position).getList());
            viewHolder.rv_child.setAdapter(adapter);
            adapter.setonItemListen(new RvChildAdapter.OnItemListener() {

                @Override
                public void onItemClick(FenYouBean.DataBean.ListBean bean) {
                    Toast.makeText(context,bean.getPscid()+"",Toast.LENGTH_SHORT).show();
                    pscid = bean.getPscid();

                    Intent intent = new Intent(context, FenMainActivity.class);
                    intent.putExtra("pscid",pscid);
                    ProgressDialog dialog = new ProgressDialog(context);
                    dialog.setMessage("正在加載...");
                    dialog.show();
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_group;
        private final RecyclerView rv_child;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_group = itemView.findViewById(R.id.tv_group);
            rv_child = itemView.findViewById(R.id.rv_child);
        }
    }
}
