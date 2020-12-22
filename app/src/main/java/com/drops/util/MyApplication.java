package com.drops.util;
import android.app.Activity;
import android.app.Application;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * MyApplication应用 基本
 * Created by 刘楠 on 2016/7/28 0028.21:45
 */
public class MyApplication extends Application {

    private static MyApplication INSTANCE;
    /**
     * 任务栈
     */
    Stack<WeakReference<Activity>> mTasks = new Stack<WeakReference<Activity>>();


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

    }

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    /**
     * 添加一个Activity
     *
     * @param task
     */
    public void pushTask(WeakReference<Activity> task) {
        mTasks.push(task);
    }

    /**
     * 移除指定的Activity
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        mTasks.remove(task);
    }


    /**
     * 移动指定位置的TAskActivity
     *
     * @param poistion
     */
    public void removeIndexTask(int poistion) {
        if (mTasks.size() > poistion) {
            mTasks.remove(poistion);
        }
    }

    /**
     * 移除全部的Activity
     */
    public void removeAllTask() {


        for (WeakReference<Activity> task : mTasks) {
            /**
             * 判断有没有结束没结果就结束
             */
            if (!task.get().isFinishing()) {
                //结束
                task.get().finish();
            }
        }
    }

    /**
     * 从顶部开始移除
     */
    public void removeTop() {
        int start = 1;
        int end   = mTasks.size();

        for (int i = end - 1; i >= start; i--) {
            /**
             * 判断有没有结束没结果就结束
             */
            if (!mTasks.get(i).get().isFinishing()) {

                mTasks.get(i).get().finish();

            }
        }
    }
}
