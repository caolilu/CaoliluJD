package test.bawie.fangjd.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import test.bawie.fangjd.R;
import test.bawie.fangjd.model.app.GlideImageLoder;
import test.bawie.fangjd.model.bean.AddGoodsBean;
import test.bawie.fangjd.model.bean.GoodsBean;
import test.bawie.fangjd.presenter.AddGoodsPrenter;
import test.bawie.fangjd.presenter.GoodsPresenter;

public class GoodsActivity extends BaseActivity implements View.OnClickListener, IGoodsListener, IAddGoodsListener {

    /**
     * 京东登录
     */
    private TextView mTvGoodsName;
    private TextView mTvGoodsPrice;
    /**
     * 加入购物车
     */
    private Button mButAddcar;
    private int pid;
    private GoodsPresenter goodsPresenter;
    private Banner mBannerLun;
    private List<String> list;
    private AddGoodsPrenter addGoodsPrenter;
    private SharedPreferences sharedPreferences;
    /**
     * 购物车
     */
    private TextView mTvGouwu;
    private ImageView mFenxiang;
    private ImageView mIvEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        initView();
    }

    private void initView() {
        mTvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
        mTvGoodsPrice = (TextView) findViewById(R.id.tv_goods_price);
        mButAddcar = (Button) findViewById(R.id.but_addcar);
        mButAddcar.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        pid = intent.getIntExtra("pid", 10);
        goodsPresenter = new GoodsPresenter(this);
        goodsPresenter.lianjie();
        addGoodsPrenter = new AddGoodsPrenter(this);

        mTvGouwu = (TextView) findViewById(R.id.tv_gouwu);
        mTvGouwu.setOnClickListener(this);
        mFenxiang = (ImageView) findViewById(R.id.fenxiang);
        mFenxiang.setOnClickListener(this);
        mIvEdit = (ImageView) findViewById(R.id.iv_edit);
        mIvEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_addcar:

                //点击按钮，判断是否登录过
                int uid = sharedPreferences.getInt("uid", 0);
                if (uid == 0) {
                    toast("请先登录");
                } else {
                    addGoodsPrenter.lianjie();
                }
                break;
            case R.id.tv_gouwu:
                intent(GoodsActivity.this, KongActivity.class);
                break;
            case R.id.fenxiang:
                ShareWeb(R.mipmap.jd_buy_icon);
                break;
            case R.id.iv_edit:
                finish();
                break;
        }
    }

    @Override
    public int getPid() {
        return pid;
    }

    @Override
    public void getdata(GoodsBean bean) {
        mTvGoodsName.setText(bean.getData().getTitle());
        mTvGoodsPrice.setText("￥" + bean.getData().getPrice());
        mBannerLun = (Banner) findViewById(R.id.banner_lun);
        //设置图片加载器
        mBannerLun.setImageLoader(new GlideImageLoder());
        //设置图片集合
        String images = bean.getData().getImages();
        String[] imgArr = images.split("\\|");
//        Picasso.with(this).load(imgArr[0]).into(myListViewHolder.list_iv);
        list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(imgArr[i]);
        }

        mBannerLun.setImages(list);
        mBannerLun.setBannerAnimation(Transformer.CubeIn);
        //banner设置方法全部调用完毕时最后调用
        mBannerLun.start();

    }

    @Override
    public int getuid() {
        return sharedPreferences.getInt("uid", 1);
    }

    @Override
    public int getpid() {
        return pid;
    }

    @Override
    public String gettoken() {
        return sharedPreferences.getString("token", "");
    }

    @Override
    public void getdata(AddGoodsBean bean) {
        toast(bean.getMsg());
    }

    //qq分享
    private void ShareWeb(int thumb_img) {
        UMImage thumb = new UMImage(this, thumb_img);
        UMWeb web = new UMWeb("https://user.qzone.qq.com/1132437156/infocenter");
        web.setThumb(thumb);
        web.setDescription("给你们的福利呦");
        web.setTitle("分享");
        new ShareAction(this).withMedia(web).setPlatform(SHARE_MEDIA.QZONE).setCallback(shareListener).share();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(GoodsActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(GoodsActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(GoodsActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };
}
