package com.config.pad.content.libding.rerxmvp.interfaceUtils;

import com.config.pad.content.libding.entry.GetListRsp;
import com.config.pad.content.libding.entry.MoiveListRsp;

public class interfaceUtilsAll {

    public interface IGetListRspView extends IWanBaseView{

        void onLoading();

        void onLoadSucess(GetListRsp getListRsp);

        void onLoadFail(String msg);
    }

}
