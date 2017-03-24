package com.jesse.mj.mvvm_demo.addtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jesse.mj.mvvm_demo.R;

public class AddEditTaskActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
    }
}
