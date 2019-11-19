package com.config.pad.content.libding.http;


import com.config.pad.content.libding.entry.GetListRsp;
import com.config.pad.content.libding.entry.MoiveListRsp;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import static com.config.pad.content.libding.http.AppConfig.isDebug;

/**
 * Function：
 */

public interface ApiService {

    /** 正式服务器地址 */
    String SERVER_ADDRESS_RELEASE = "http://api.zhuishushenqi.com";

    /** 测试服务器地址 */
    String SERVER_ADDRESS_DEBUG = "http://api.zhuishushenqi.com";

    /** 服务器域名 */
    String SERVER_ADDRESS = isDebug ? SERVER_ADDRESS_DEBUG : SERVER_ADDRESS_RELEASE;


    //获取电影列表
    @POST("/PageSubArea/TrailerList.api")  //http://api.m.mtime.cn
    Flowable<MoiveListRsp> getMoiveList();

    @GET("/cats/lv2/statistics")  //http://api.zhuishushenqi.com
    Flowable<GetListRsp> requestGetListRspList(@QueryMap Map<String, String> params);

}

