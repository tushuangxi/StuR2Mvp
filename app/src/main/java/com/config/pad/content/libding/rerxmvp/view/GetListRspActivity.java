package com.config.pad.content.libding.rerxmvp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.config.pad.content.R;
import com.config.pad.content.libding.entry.GetListRsp;
import com.config.pad.content.libding.entry.MoiveListRsp;
import com.config.pad.content.libding.rerxmvp.base.AWanBaseActivity;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.CreatePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IMoiveListView;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.interfaceUtilsAll;
import com.config.pad.content.libding.rerxmvp.presenter.GetListRspPresenter;
import com.config.pad.content.libding.rerxmvp.presenter.MoiveListPresenter;

import butterknife.BindView;

@CreatePresenter(GetListRspPresenter.class)
public class GetListRspActivity extends AWanBaseActivity<interfaceUtilsAll.IGetListRspView, GetListRspPresenter>
        implements interfaceUtilsAll.IGetListRspView {

    @BindView(R.id.tv_content)
    TextView tv_content;


    @Override
    public int getViewLayoutId() {
        return R.layout.activity_getlistrsp;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            // TODO: 2017/12/11 数据恢复
        }
        getPresenter().requestGetListRspList();

    }

    @Override
    public void onLoading() {
        tv_content.setText("数据加载中，请稍后...");
    }

    @Override
    public void onLoadSucess(GetListRsp getListRsp) {

        tv_content.setText(getListRsp.getFemale().get(0).getName());

    }

    @Override
    public void onLoadFail(String msg) {
        Toast.makeText(GetListRspActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
