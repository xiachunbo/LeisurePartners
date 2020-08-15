package com.drops.activity;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.drops.fragment.BaseSlidingFragmentActivity;
import com.drops.fragment.FragmentA;
import com.drops.fragment.FragmentB;
import com.drops.fragment.FragmentC;
import com.drops.fragment.FragmentD;
import com.drops.fragment.LeftFragment;
import com.drops.fragment.RightFragment;
import com.drops.fragment.SlidingMenu;
import com.lovebobo.webview.R;

public class MainActivity extends BaseSlidingFragmentActivity implements View.OnClickListener {
    private FragmentA f_a;
    private FragmentB f_b;
    private FragmentC f_c;
    private FragmentD f_d;
    private FragmentManager fm;
    private Fragment[] mFragments;
    private int mIndex;
    private RadioGroup radioGroup;
    protected SlidingMenu mSlidingMenu;
    private LeftFragment mLeftFragment;
    private RightFragment mRightFragment;
    private TextView mTitleName;
    ImageView mImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSlidingMenu();
        setContentView(R.layout.activity_main);
        initView();
        initFragment();//方法一，默认第一fragment
        initView(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明底部导航栏

        }
    }

    private void initView() {
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int arg1) {
                //遍历RadioGroup 里面所有的子控件。
                for (int index = 0; index < group.getChildCount(); index++) {
                    //获取到指定位置的RadioButton
                    RadioButton rb = (RadioButton)group.getChildAt(index);
                    //如果被选中
                    if (rb.isChecked()) {
                        setIndexSelected(index);
                        //setIndexSelectedTwo(index);  //方法二
                        break;
                    }
                }

            }
        });

    }
    //方法一，默认第一fragment
    private void initFragment() {
        f_a =new FragmentA();
        f_b =new FragmentB();
        f_c =new FragmentC();
        f_d =new FragmentD();
        //添加到数组
        mFragments = new Fragment[]{f_a,f_b,f_c,f_d};
        //开启事务
        FragmentManager    fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft= fragmentManager.beginTransaction();
        //添加首页
        ft.add(R.id.content,f_a).commit();
        //默认设置为第0个
        setIndexSelected(0);
    }
    //方法一，选中显示与隐藏
    private void setIndexSelected(int index) {

        if(mIndex==index){
            return;
        }
        FragmentManager    fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft= fragmentManager.beginTransaction();

        //隐藏
        ft.hide(mFragments[mIndex]);
        //判断是否添加
        if(!mFragments[index].isAdded()){
            ft.add(R.id.content,mFragments[index]).show(mFragments[index]);
        }else {
            ft.show(mFragments[index]);
        }

        ft.commit();
        //再次赋值
        mIndex=index;

    }
    //方法二，选中替换
    private void setIndexSelectedTwo(int index) {
        switch (index) {
            case 0:
                changeFragment(new FragmentA().getFragmentA());
                break;
            case 1:
                changeFragment(new FragmentB().getFragmentB());
                break;
            case 2:
                changeFragment(new FragmentC().getFragmentC());
                break;
            case 3:
                changeFragment(new FragmentD().getFragmentD());
                break;
            default:
                break;
        }
    }

    ////方法二，默认第一fragment
    private void changeFragment(Fragment fm) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.content, fm);
        transaction.commit();
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

    }

    private void initData() {

    }



    private void initView(Bundle savedInstanceState) {
        mSlidingMenu.setSecondaryMenu(R.layout.main_right_layout);
        FragmentTransaction mFragementTransaction = getSupportFragmentManager()
                .beginTransaction();
        Fragment mFrag = new RightFragment();
        mFragementTransaction.replace(R.id.main_right_fragment, mFrag);
        mFragementTransaction.commit();
    }

    private void initSlidingMenu() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int mScreenWidth = dm.widthPixels;// ªÒ»°∆¡ƒª∑÷±Ê¬ øÌ∂»
        // TODO Auto-generated method stub
        setBehindContentView(R.layout.main_left_layout);// …Ë÷√◊Û≤Àµ•
        FragmentTransaction mFragementTransaction = getSupportFragmentManager()
                .beginTransaction();
        Fragment mFrag = new LeftFragment();
        mFragementTransaction.replace(R.id.main_left_fragment, mFrag);
        mFragementTransaction.commit();
        // customize the SlidingMenu
        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setShadowWidth(mScreenWidth / 40);
        mSlidingMenu.setBehindOffset(mScreenWidth / 8);
        //mSlidingMenu.setFadeDegree(0.4f);
        //mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //mSlidingMenu.setShadowDrawable(R.drawable.slidingmenu_shadow);
        mSlidingMenu.setSecondaryShadowDrawable(R.drawable.right_shadow);
        mSlidingMenu.setOffsetFadeDegree(0.4f);
        //mSlidingMenu.setFadeEnabled(true);
        //mSlidingMenu.setBehindScrollScale(0.333f);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ahri_square_0:
                mSlidingMenu.showMenu(true);
                break;
            case R.id.ivTitleBtnRigh:
                mSlidingMenu.showSecondaryMenu(true);
                break;

            default:
                break;
        }
    }
}
