package com.config.pad.content.libding.rerxmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.config.pad.content.libding.http.ApiManager.ApiRetrofit;
import com.config.pad.content.libding.http.ApiService;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IWanBaseView;

/**
 * Function: 带有生命周期的所有presenter的基类 view 和 presenter 绑定和解绑，
 *           避免当请求网络数据未完成时activity退出所造成的内存泄露
 */

public abstract class AWanBasePresenter<V extends IWanBaseView>{

    private V wanView;
    private static final String TAG = "JokerWan";
    protected ApiService apiService;

    public AWanBasePresenter() {
        apiService = ApiRetrofit.getApiService();
    }

    /**
     * Presenter被创建后调用
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
        Log.e(TAG,"-------> onCreatePersenter");
    }

    /**
     * 绑定View
     * @param view
     */
    public void onBindView(V view){
        this.wanView = view;
        Log.e(TAG,"-------> onBindView");
    }

    /**
     * 解绑View
     */
    public void onUnbindView(){
        this.wanView = null;
        Log.e(TAG,"-------> onUnbindView");
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        Log.e(TAG,"-------> onDestroyPersenter");
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG,"-------> onSaveInstanceState");
    }

    public V getView(){
        return wanView;
    }
}
