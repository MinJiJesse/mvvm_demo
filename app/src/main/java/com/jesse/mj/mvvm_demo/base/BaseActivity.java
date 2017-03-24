package com.jesse.mj.mvvm_demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mj on 17/3/24.
 * activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }
}
