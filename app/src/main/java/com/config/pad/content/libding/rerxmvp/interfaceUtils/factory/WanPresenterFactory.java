package com.config.pad.content.libding.rerxmvp.interfaceUtils.factory;

import com.config.pad.content.libding.rerxmvp.base.AWanBasePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.CreatePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IWanBaseView;

/**
 * Function: WanPresenterFactory的实现类
 */

public class WanPresenterFactory<V extends IWanBaseView,P extends AWanBasePresenter<V>>
        implements IWanPresenterFactory<V,P> {

    /**
     * 需要创建的Presenter的类型
     */
    private final Class<P> mPresenterClass;

    public WanPresenterFactory(Class<P> mPresenterClass) {
        this.mPresenterClass = mPresenterClass;
    }

    /**
     * 根据注解创建Presenter的工厂实现类
     * @param viewClazz 需要创建Presenter的V层实现类
     * @param <V> 当前View实现的接口类型
     * @param <P> 当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IWanBaseView,P extends AWanBasePresenter<V>> WanPresenterFactory<V,P> createFactory(Class<?> viewClazz){
        CreatePresenter annotation = viewClazz.getAnnotation(CreatePresenter.class);
        Class<P> aClass = null;
        if(annotation != null) {
            aClass = (Class<P>) annotation.value();
        }
        return aClass == null ? null : new WanPresenterFactory<V,P>(aClass);
    }

    @Override
    public P createPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Presenter create failed!，please check if declaration @CreatePresenter(xxx.class) anotation or not");
        }
    }
}
