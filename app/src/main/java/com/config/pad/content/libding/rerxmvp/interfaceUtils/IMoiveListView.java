package com.config.pad.content.libding.rerxmvp.interfaceUtils;

import com.config.pad.content.libding.entry.MoiveListRsp;

/**
 * Function:
 */

public interface IMoiveListView extends IWanBaseView{

    void onLoading();

    void onLoadSucess(MoiveListRsp moiveListRsp);

    void onLoadFail(String msg);
}
