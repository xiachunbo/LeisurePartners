package com.drops.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.drops.activity.HomeAdapter;
import com.drops.util.DataTestUtils;
import com.drops.util.MyApplication;
import com.lovebobo.webview.BuildConfig;
import com.lovebobo.webview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentA.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentA#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentA extends Fragment {


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    //总数据源
    private List<Object> mObjects;
    // 单例(方法二)
    private static FragmentA fa;
    private Activity activity;

    public static FragmentA getFragmentA() {
        if (fa == null) {
            fa = new FragmentA();
        }
        return fa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home2, container, false);
        ButterKnife.bind(view);
        initData();
        return view;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
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
        HomeAdapter adapter = new HomeAdapter(this.getActivity(), mObjects);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = MyApplication.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(),LinearLayoutManager.VERTICAL,false));

    }
}
