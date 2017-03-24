package com.jesse.mj.mvvm_demo.tasks;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.PropertyChangeRegistry;
import android.widget.Toast;

import com.jesse.mj.mvvm_demo.base.TasksNavigator;
import com.jesse.mj.mvvm_demo.base.ViewModel;

/**
 * Created by mj on 17/3/24.
 */

public class TasksViewModel implements ViewModel {

    private final TasksNavigator navigator;
    private Context context;
    private PropertyChangeRegistry callbacks;

    public final ObservableBoolean dataLoading = new ObservableBoolean(false);
    public final ObservableField<String> data = new ObservableField<>();

    /**
     *
     * @param context 需要时ApplicationContext，不然容易造成内存泄漏
     */
    public TasksViewModel(Context context, TasksNavigator navigator) {
        this.context = context;
        this.navigator = navigator;
        data.set("12345");
    }

    /**
     * 底部Navigationbar点击回调
     */
    public void addNewTask() {
        if (navigator != null) {
            navigator.addNewTask();
        }
        // TODO: 17/3/24 add new task
        Toast.makeText(context, "navigationbar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        if (callbacks == null) {
            callbacks = new PropertyChangeRegistry();
        }
        callbacks.add(onPropertyChangedCallback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback onPropertyChangedCallback) {
        if (callbacks != null) {
            callbacks.remove(onPropertyChangedCallback);
        }
    }
}
