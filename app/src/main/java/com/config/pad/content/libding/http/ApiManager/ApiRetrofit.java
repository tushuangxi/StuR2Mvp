package com.config.pad.content.libding.http.ApiManager;

import com.config.pad.content.libding.application.MyApp;
import com.config.pad.content.libding.http.ApiService;
import com.config.pad.content.libding.utils.NetUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Function: Retrofit配置类
 */
public class ApiRetrofit {
    private static ApiRetrofit apiRetrofit;
    public static ApiService apiService;

    public static final String T_BASE_URL = "http://api.m.mtime.cn";

//    public ApiService getApiService() {
//        return apiService;
//    }

    protected static final Object monitor = new Object();
    public static ApiService getApiService() {
        synchronized (monitor) {
            if (apiService == null) {
                apiRetrofit = new ApiRetrofit();
            }
            return apiService;
        }
    }

    ApiRetrofit() {
        //cache url
        File httpCacheDirectory = new File(MyApp.mContext.getCacheDir(), "/httpCache");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(new LoggingInterceptor())
                .cache(cache)
                .build();

        Retrofit retrofit_t = new Retrofit.Builder()
                .baseUrl(ApiService.SERVER_ADDRESS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//不能是RxJavaCallAdapterFactory，因为我们这里用的rxjava2
                .build();


        apiService = retrofit_t.create(ApiService.class);

    }


    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {

        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365, TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();

        Request request = chain.request();
        if (!NetUtils.checkNetWorkIsAvailable(MyApp.mContext)) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();

        }
        Response originalResponse = chain.proceed(request);
        if (NetUtils.checkNetWorkIsAvailable(MyApp.mContext)) {
            int maxAge = 0; // read from cache
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        } else {
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    };
}
