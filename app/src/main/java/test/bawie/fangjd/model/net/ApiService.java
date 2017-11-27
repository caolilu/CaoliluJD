package test.bawie.fangjd.model.net;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;
import test.bawie.fangjd.model.bean.AddGoodsBean;
import test.bawie.fangjd.model.bean.AddrsBean;
import test.bawie.fangjd.model.bean.DeleteGoods;
import test.bawie.fangjd.model.bean.FenYouBean;
import test.bawie.fangjd.model.bean.FenYouMainBean;
import test.bawie.fangjd.model.bean.GoodsBean;
import test.bawie.fangjd.model.bean.GoodsCarBean;
import test.bawie.fangjd.model.bean.ShangChuanBean;
import test.bawie.fangjd.model.bean.SouSuoBean;
import test.bawie.fangjd.model.bean.TianJiaBean;
import test.bawie.fangjd.model.bean.UserinfoBean;



public interface ApiService {
    /**
     * 结合Retrofit+RxJava
     * https://www.zhaoapi.cn/product/getProductCatagory?cid=1
     * @param
     * @return
     */
    @GET("getProductCatagory")
    Observable<FenYouBean> getNoParams(@Query("cid")int cid);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/product/getProducts?pscid=1
     * @param
     * @return
     */
    @GET("getProducts")
    Observable<FenYouMainBean> getNoParams2(@Query("pscid")int pscid);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/product/getProductDetail?pid=1
     * @param
     * @return
     */
    @GET("getProductDetail")
    Observable<GoodsBean> getNoParams3(@Query("pid")int pid);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1&source=android
     * @param
     * @return
     */
    @GET("searchProducts")
    Observable<SouSuoBean> getsousuo(@Query("keywords")String keywords,@Query("page")int page,@Query("source")String source);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/user/getUserInfo?uid=552&token=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @GET("getUserInfo")
    Observable<UserinfoBean> getuserinfo(@Query("uid")int uid, @Query("token")String token);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/product/addCart?uid=552&pid=965&token=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @GET("addCart")
    Observable<AddGoodsBean> addgoods(@Query("uid")int uid,@Query("pid")int pid, @Query("token")String token);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/product/getCarts?uid=552&token=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @GET("getCarts")
    Observable<GoodsCarBean> gouwucar(@Query("uid")int uid, @Query("token")String token);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/product/deleteCart?uid=72&pid=1&token=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @GET("deleteCart")
    Observable<DeleteGoods> deletegoods(@Query("uid")int uid, @Query("pid")int pid, @Query("token")String token);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/user/getAddrs?uid=552&uid=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @GET("")
    Observable<AddrsBean> getAddres(@Query("uid")String uid,@Query("token")String token);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/user/getAddrs?uid=552&uid=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @GET("addAddr")

    Observable<TianJiaBean> tianjia(@Query("uid")int uid, @Query("addr")String addr,@Query("mobile")String mobile,@Query("name")String name,@Query("token") String token);
    /**
     * 结合Retrofit+RxJava
     *https://www.zhaoapi.cn/user/getAddrs?uid=552&uid=4B5DAF274221936555E01E5F7BC271F4
     * @param
     * @return
     */
    @Multipart
    @POST("file/upload")
    Observable<ShangChuanBean> uploadPic(@Part("uid") RequestBody uid, @Part MultipartBody.Part file,@Part("token") RequestBody token);

}

