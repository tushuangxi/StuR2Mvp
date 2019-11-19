package com.config.pad.content.libding.http.ApiManager;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Function：线程调度
 */

public class ApiUtils {

    /**
     * 线程切换
     * @return
     */
    public static <T> FlowableTransformer<T, T> getScheduler() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
