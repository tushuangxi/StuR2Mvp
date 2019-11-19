package com.config.pad.content.libding.rerxmvp.interfaceUtils.factory;

import com.config.pad.content.libding.rerxmvp.base.AWanBasePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IWanBaseView;

/**
 * Created by JokerWan on 2017/12/11.
 * WeChat: wjc398556712
 * Function: 工厂接口，提供了创建Presenter的接口方法
 */

public interface IWanPresenterFactory<V extends IWanBaseView, P extends AWanBasePresenter<V>> {

    P createPresenter();
}
