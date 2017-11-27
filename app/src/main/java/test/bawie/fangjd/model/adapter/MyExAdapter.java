package test.bawie.fangjd.model.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import test.bawie.fangjd.AddDelView;
import test.bawie.fangjd.R;
import test.bawie.fangjd.model.bean.DeleteGoods;
import test.bawie.fangjd.model.bean.GoodsCarBean;
import test.bawie.fangjd.model.bean.ShanchuBean;
import test.bawie.fangjd.model.eventbus.MessageCounEvent;
import test.bawie.fangjd.model.eventbus.MessageEvent;
import test.bawie.fangjd.presenter.DeletePresenter;
import test.bawie.fangjd.presenter.ShanchuPresenter;
import test.bawie.fangjd.view.IDeleteListener;
import test.bawie.fangjd.view.IShanchuView;


public class MyExAdapter extends BaseExpandableListAdapter implements IShanchuView{
    private Context context;
    private List<GoodsCarBean.DataBean> groupList;
    private List<List<GoodsCarBean.DataBean.ListBean>> childList;
    private int count;
    private double price;
    private int numm;
    private int pid;
    private ShanchuPresenter shanchuPresenter;

    public MyExAdapter(Context context, List<GoodsCarBean.DataBean> grouplist, List<List<GoodsCarBean.DataBean.ListBean>> childlist) {
        this.context = context;
        this.groupList = grouplist;
        this.childList = childlist;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        GroupViewHolder holder;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = View.inflate(context, R.layout.adapter_addcar_group, null);
            holder.cb_group = view.findViewById(R.id.cb_group);
            holder.tv_shangjia = view.findViewById(R.id.tv_shangjia);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        //赋值
        GoodsCarBean.DataBean dataBean = groupList.get(groupPosition);
        holder.cb_group.setChecked(dataBean.isChecked());
        holder.tv_shangjia.setText(dataBean.getSellerName());
        //给group的checkbox设置点击事件
        holder.cb_group.setOnClickListener(new GroupCbOnClickListener(groupPosition));
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = View.inflate(context, R.layout.adapter_addcar_child, null);
            holder.cb_child = view.findViewById(R.id.cb_child);
            holder.iv_child = view.findViewById(R.id.iv_child);
            holder.shan=view.findViewById(R.id.shanshan);
            holder.tv_child_name = view.findViewById(R.id.tv_child_name);
            holder.tv_child_price = view.findViewById(R.id.tv_child_price);
            holder.myadd = view.findViewById(R.id.myadd);
            holder.add = view.findViewById(R.id.add);
            holder.del = view.findViewById(R.id.del);
            holder.num = view.findViewById(R.id.num);
            holder.lldel = view.findViewById(R.id.lldel);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        shanchuPresenter = new ShanchuPresenter(context, this);

        //赋值
        final GoodsCarBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        holder.cb_child.setChecked(listBean.isChecked());
        holder.tv_child_name.setText(listBean.getTitle());
        pid = listBean.getPid();
        holder.tv_child_price.setText("￥"+listBean.getPrice());
        String images = listBean.getImages();
        String[] imgArr = images.split("\\|");
        Picasso.with(context).load(imgArr[0]).into(holder.iv_child);
        holder.cb_child.setOnClickListener(new ChildCbOnClickListener(groupPosition, childPosition));
        numm = Integer.parseInt(holder.num.getText().toString().trim());
        holder.shan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shanchuPresenter.getWuxiian();
            }
        });
        holder.lldel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle(listBean.getTitle())
                        .setMessage("确定删除吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                Toast.makeText(context, "点了", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
                                final int uid = sharedPreferences.getInt("uid", 0);
                                final String token = sharedPreferences.getString("token", "");
                                DeletePresenter deletePresenter = new DeletePresenter(new IDeleteListener() {
                                    @Override
                                    public int getUid() {
                                        return uid;
                                    }

                                    @Override
                                    public int getPid() {
                                        return listBean.getPid();
                                    }

                                    @Override
                                    public String getToken() {
                                        return token;
                                    }

                                    @Override
                                    public void getData(DeleteGoods bean) {
                                        Toast.makeText(context, bean.getMsg(), Toast.LENGTH_SHORT).show();

                                        notifyDataSetChanged();
                                    }
                                });
                                deletePresenter.lianjie();

                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();

                return true;
            }
        });
        holder.myadd.setOnItemClick(new AddDelView.OnItemClick() {
            @Override
            public void onItemAddClick(int count) {
                if (listBean.isChecked()) {
                    MessageCounEvent messageCounEvent = new MessageCounEvent();
                    messageCounEvent.setPrice(listBean.getPrice());
                    messageCounEvent.setCount(1);
                    EventBus.getDefault().post(messageCounEvent);
                } else {
                    Toast.makeText(context, "请勾选", Toast.LENGTH_SHORT).show();
                    holder.myadd.setCount(1);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onItemDelClick(int count) {
                if (listBean.isChecked()) {
                    MessageCounEvent messageCounEvent = new MessageCounEvent();
                    double price = listBean.getPrice();
                    messageCounEvent.setPrice(-price);
                    messageCounEvent.setCount(-1);
                    EventBus.getDefault().post(messageCounEvent);
                } else{
                    Toast.makeText(context, "请勾选", Toast.LENGTH_SHORT).show();
                    holder.myadd.setCount(1);
                }
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int uid() {
        return 509;
    }

    @Override
    public int pscid() {
        return pid;
    }

    @Override
    public void getBean(ShanchuBean shanchuBean) {
        String msg = shanchuBean.getMsg();
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    class GroupViewHolder {
        CheckBox cb_group;
        TextView tv_shangjia;
    }

    class ChildViewHolder {
        Button shan;
        CheckBox cb_child;
        ImageView iv_child;
        TextView tv_child_name;
        TextView tv_child_price;
        AddDelView myadd;
        TextView add;
        TextView del;
        TextView num;
        LinearLayout lldel;
    }

    class ChildCbOnClickListener implements View.OnClickListener {
        private int groupPosition;
        private int childPosition;

        public ChildCbOnClickListener(int groupPosition, int childPosition) {
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            List<GoodsCarBean.DataBean.ListBean> listBeen = childList.get(groupPosition);
            GoodsCarBean.DataBean.ListBean childBean = listBeen.get(childPosition);
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                childBean.setChecked(cb.isChecked());
                //计算选中的商品数，并发送到主界面进行显示

                //判断该商家的所有商品的checkbox是否都选中
                if (isChildChecked(listBeen)) {
                    groupList.get(groupPosition).setChecked(true);
                    MessageCounEvent msgCount = new MessageCounEvent();
                    msgCount.setCount(1*numm);
                    msgCount.setPrice(childBean.getPrice()*numm);
                    EventBus.getDefault().post(msgCount);
                    MessageEvent msg = new MessageEvent();
                    msg.setFlag(isGroupChecked());
                    EventBus.getDefault().post(msg);
                    notifyDataSetChanged();
                } else {
                    groupList.get(groupPosition).setChecked(false);
                    MessageCounEvent messageCounEvent = new MessageCounEvent();
                    double price = childBean.getPrice();
                    messageCounEvent.setPrice(-childBean.getPrice()*numm);
                    messageCounEvent.setCount(-1*numm);
                    EventBus.getDefault().post(messageCounEvent);
                    MessageEvent msg = new MessageEvent();
                    msg.setFlag(false);
                    EventBus.getDefault().post(msg);
                    notifyDataSetChanged();
                    notifyDataSetChanged();
                }
            }

        }
    }

    /**
     * 判断该商家的所有商品的checkbox是否都选中
     *
     * @return
     */
    private boolean isChildChecked(List<GoodsCarBean.DataBean.ListBean> childBeen) {
        for (int i = 0; i < childBeen.size(); i++) {
            GoodsCarBean.DataBean.ListBean listBean = childBeen.get(i);
            if (!listBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    class GroupCbOnClickListener implements View.OnClickListener {
        private int groupPostion;

        public GroupCbOnClickListener(int groupPostion) {
            this.groupPostion = groupPostion;
        }

        @Override
        public void onClick(View v) {
            if (v instanceof CheckBox) {

                //多态，因为我是给checkbox设置的点击事件，所以可以强转成checkbox
                CheckBox cb = (CheckBox) v;
                //根据cb.isChecked()是否选中，给一级列的checkbox改变状态
                groupList.get(groupPostion).setChecked(cb.isChecked());
                List<GoodsCarBean.DataBean.ListBean> listBeen = childList.get(groupPostion);
                for (GoodsCarBean.DataBean.ListBean childBean : listBeen) {
                    childBean.setChecked(cb.isChecked());
                }
                //计算选中的商品数，并发送到主界面进行显示
                if(cb.isChecked()){
                    MessageCounEvent msgCount = new MessageCounEvent();

                    msgCount.setCount(listBeen.size());
                    for (int i = 0; i <listBeen.size() ; i++) {
                        msgCount.setPrice(listBeen.get(i).getPrice());
                    }



                    EventBus.getDefault().post(msgCount);
                }else {
                    MessageCounEvent msgCount = new MessageCounEvent();
                    msgCount.setCount(-listBeen.size());
                    for (int i = 0; i <listBeen.size() ; i++) {
                        msgCount.setPrice(-listBeen.get(i).getPrice());
                    }
                    EventBus.getDefault().post(msgCount);
                }
                MessageEvent msg = new MessageEvent();
                msg.setFlag(isGroupChecked());
                EventBus.getDefault().post(msg);
                notifyDataSetChanged();
            }
        }
    }

    /**
     * 判断其它的商家是否选中
     *
     * @return
     */
    private boolean isGroupChecked() {
        for (GoodsCarBean.DataBean groupBean : groupList) {
            if (!groupBean.isChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 主界面全选按钮的操作
     *
     * @param bool
     */
    public void allChecked(boolean bool) {
        for (int i = 0; i < groupList.size(); i++) {
            groupList.get(i).setChecked(bool);
            for (int j = 0; j < childList.get(i).size(); j++) {
                childList.get(i).get(j).setChecked(bool);
                childList.get(i).get(j).getPrice();
            }
        }
        if(bool){
            //计算选中的商品数，并发送到主界面进行显示
            MessageCounEvent msgCount = new MessageCounEvent();
            int totalCount = totalCount();
            msgCount.setCount(totalCount);
            msgCount.setPrice(totalprice());
            EventBus.getDefault().post(msgCount);
            notifyDataSetChanged();
        }else {
            MessageCounEvent msgCount = new MessageCounEvent();
            int i = totalCount();
            msgCount.setCount(0);
            double totalprice = totalprice();
            msgCount.setPrice(0);
            EventBus.getDefault().post(msgCount);
            notifyDataSetChanged();
        }


    }

    private int totalCount() {
        count = 0;
        for (int i = 0; i < groupList.size(); i++) {
            for (int j = 0; j < childList.get(i).size(); j++) {
                if (childList.get(i).get(j).isChecked()) {
                    //遍历所有的商品，只要是选中状态的，就加1
                    count++;
                }else {
                    count--;
                }
            }
        }
        return count;
    }


    private double totalprice() {
        price = 0.0;
        for (int i = 0; i < groupList.size(); i++) {
            for (int j = 0; j < childList.get(i).size(); j++) {
                if (childList.get(i).get(j).isChecked()) {
                    price+=childList.get(i).get(j).getPrice();
                }else {
                    price-=childList.get(i).get(j).getPrice();
                }
            }
        }
        return price;
    }

}
