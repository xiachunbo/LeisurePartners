package com.drops.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.drops.fragment.FragmentA;
import com.drops.fragment.FragmentB;
import com.drops.fragment.FragmentC;
import com.drops.fragment.FragmentD;
import com.lovebobo.webview.R;

/**
 * 测试fragment
 * Created by Administrator on 2018/3/15 0015.
 */

public class TestTabActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private FragmentManager mFragmentManager;
    private FragmentA tabOneFragment = null;
    private FragmentB tabTwoFragment = null;
    private FragmentC tabThreeFragment = null;
    private FragmentD tabFourFragment = null;

    public static void launch(Context context) {
        Intent intent = new Intent(context, TestTabActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radio_bottom = (RadioGroup) findViewById(R.id.radioGroup);
        mFragmentManager = getFragmentManager();
        setTabSelection(0);
        radio_bottom.setOnCheckedChangeListener(this);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     * 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (tabOneFragment != null) {
            transaction.hide(tabOneFragment);
        }
        if (tabTwoFragment != null) {
            transaction.hide(tabTwoFragment);
        }
        if (tabThreeFragment != null) {
            transaction.hide(tabThreeFragment);
        }
        if (tabFourFragment != null) {
            transaction.hide(tabFourFragment);
        }
    }

    private void setTabSelection(int index) {
        // 开启Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (tabOneFragment == null) {
                    tabOneFragment = FragmentA.getFragmentA();
                    transaction.add(R.id.content, tabOneFragment);
                } else {
                    transaction.show(tabOneFragment);
                }
                break;
            case 1:
                if (tabTwoFragment == null) {
                    tabTwoFragment = FragmentB.getFragmentB();
                    transaction.add(R.id.content, tabTwoFragment);
                } else {
                    transaction.show(tabTwoFragment);
                }
                break;
            case 2:
                if (tabThreeFragment == null) {
                    tabThreeFragment = FragmentC.getFragmentC();
                    transaction.add(R.id.content, tabThreeFragment);
                } else {
                    transaction.show(tabThreeFragment);
                }
                break;
            case 3:
                if (tabFourFragment == null) {
                    tabFourFragment = FragmentD.getFragmentD();
                    transaction.add(R.id.content, tabFourFragment);
                } else {
                    transaction.show(tabFourFragment);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbHome:
                setTabSelection(0);
                break;
            case R.id.rbShop:
                setTabSelection(1);
                break;
            case R.id.rbMessage:
                setTabSelection(2);
                break;
            case R.id.rbMine:
                setTabSelection(3);
                break;
        }
    }
}
