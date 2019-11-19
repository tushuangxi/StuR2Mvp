package com.config.pad.content.libding.rerxmvp.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.config.pad.content.R;
import com.config.pad.content.libding.entry.MoiveListRsp;
import com.config.pad.content.libding.rerxmvp.base.AWanBaseActivity;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.CreatePresenter;
import com.config.pad.content.libding.rerxmvp.interfaceUtils.IMoiveListView;
import com.config.pad.content.libding.rerxmvp.presenter.MoiveListPresenter;
import butterknife.BindView;

@CreatePresenter(MoiveListPresenter.class)
public class MainActivity extends AWanBaseActivity<IMoiveListView,MoiveListPresenter>
        implements IMoiveListView {

    @BindView(R.id.tv_moive_name)
    TextView tvMoiveName;
    @BindView(R.id.img_moive)
    ImageView imgMoive;

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            // TODO: 2017/12/11 数据恢复
        }
        getPresenter().getMoiveList();

    }

    @Override
    public void onLoading() {
        tvMoiveName.setText("数据加载中，请稍后...");
    }

    @Override
    public void onLoadSucess(MoiveListRsp moiveListRsp) {
        MoiveListRsp.TrailersBean trailersBean = moiveListRsp.getTrailers().get(1);
        tvMoiveName.setText(trailersBean.getMovieName());
        Glide.with(this).load(trailersBean.getCoverImg()).into(imgMoive);
    }

    @Override
    public void onLoadFail(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
