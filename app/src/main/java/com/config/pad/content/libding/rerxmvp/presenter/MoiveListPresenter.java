package com.config.pad.content.libding.rerxmvp.presenter;

import com.config.pad.content.libding.entry.MoiveListRsp;
import com.config.pad.content.libding.http.ApiManager.ApiSubscriber;
import com.config.pad.content.libding.http.ApiManager.ApiUtils;
import com.config.pad.content.libding.rerxmvp.base.AWanBasePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IMoiveListView;

/**
 * Created by JokerWan on 2017/12/11.
 * WeChat: wjc398556712
 * Function:
 */

public class MoiveListPresenter extends AWanBasePresenter<IMoiveListView> {

    public void getMoiveList(){
        getView().onLoading();
        apiService.getMoiveList()
                .compose(ApiUtils.getScheduler())
                .subscribe(new ApiSubscriber<MoiveListRsp>() {
                    @Override
                    public void onNext(MoiveListRsp moiveListRsp) {
                        if(moiveListRsp != null) {
                            getView().onLoadSucess(moiveListRsp);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        getView().onLoadFail(t.getMessage());
                    }
                });
    }
}
