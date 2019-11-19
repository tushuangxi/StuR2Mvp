package com.config.pad.content.libding.rerxmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.config.pad.content.libding.rerxmvp.interfaceUtils.factory.IWanPresenterFactory;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.factory.WanPresenterFactory;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.proxy.IPresenterProxy;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.proxy.PresenterProxy;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IWanBaseView;

import butterknife.ButterKnife;

/**
 * Created by tushuangxi on 2017/12/11.
 * WeChat:
 * Function: 所有Activity的基类
 */

public abstract class AWanBaseActivity<V extends IWanBaseView, P extends AWanBasePresenter<V>>
        extends AppCompatActivity implements IPresenterProxy<V,P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    //创建被代理对象,传入默认Presenter的工厂
    private PresenterProxy<V,P> mProxy = new PresenterProxy<>(WanPresenterFactory.<V,P>createFactory(getClass()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }

        setContentView(getViewLayoutId());
        ButterKnife.bind(this);
        mProxy.onCreate((V) this);
        initData(savedInstanceState);
    }

    public abstract int getViewLayoutId();

    public abstract void initData(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    /**
     * 子类可以重新该方法实现自己的presenter工厂
     * @param presenterFactory
     */
    @Override
    public void setPresenterFactory(IWanPresenterFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    /**
     * 子类可以重新该方法获取自己的presenter工厂
     * @return
     */
    @Override
    public IWanPresenterFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /**
     * 子类可以重新该方法获取presenter
     * @return
     */
    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }
}
