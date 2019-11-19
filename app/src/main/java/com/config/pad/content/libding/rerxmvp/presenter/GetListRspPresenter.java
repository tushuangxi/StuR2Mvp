package com.config.pad.content.libding.rerxmvp.presenter;

import android.util.Log;

import com.config.pad.content.libding.entry.GetListRsp;
import com.config.pad.content.libding.entry.MoiveListRsp;
import com.config.pad.content.libding.http.ApiManager.ApiSubscriber;
import com.config.pad.content.libding.http.ApiManager.ApiUtils;
import com.config.pad.content.libding.http.ServiceMapParams;
import com.config.pad.content.libding.rerxmvp.base.AWanBasePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IMoiveListView;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;

/**
 * Created by JokerWan on 2017/12/11.
 * WeChat: wjc398556712
 * Function:
 */

public class GetListRspPresenter extends AWanBasePresenter<interfaceUtilsAll.IGetListRspView> {

    public void requestGetListRspList(){
        getView().onLoading();
        apiService.requestGetListRspList(ServiceMapParams.getGetListRspMapParams())
                .compose(ApiUtils.getScheduler())
                .subscribe(new ApiSubscriber<GetListRsp>() {
                    @Override
                    public void onNext(GetListRsp getListRsp) {
                        if(getListRsp != null) {
                            getView().onLoadSucess(getListRsp);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("tag",Log.getStackTraceString(t));
                        getView().onLoadFail(t.getMessage());
                    }
                });
    }
}
