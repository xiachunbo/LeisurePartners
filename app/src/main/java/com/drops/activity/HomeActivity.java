package com.drops.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drops.util.DataTestUtils;
import com.lovebobo.webview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends Activity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    //总数据源
    private List<Object> mObjects;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        if (mObjects == null) {
            mObjects = new ArrayList<>();
        }

        //设置轮播图数据
        mObjects.add(DataTestUtils.getBannerData());
        //设置品牌
        mObjects.add(DataTestUtils.getBrandData());
        //设置榜单
        mObjects.add(DataTestUtils.getFocusData());
        //设置榜单
        mObjects.add(DataTestUtils.getSelectData());
        //设置底部数据
        DataTestUtils.getBottomData(mObjects);
        HomeAdapter adapter = new HomeAdapter(this, mObjects);
        recyclerView = (RecyclerView) this.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }
}