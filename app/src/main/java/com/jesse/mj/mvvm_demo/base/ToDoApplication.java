package com.jesse.mj.mvvm_demo.base;

import android.content.Context;

/**
 * Created by mj on 17/3/24.
 */

public class ToDoApplication extends android.app.Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
