package com.jesse.mj.mvvm_demo.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jesse.mj.mvvm_demo.base.BaseFragment;
import com.jesse.mj.mvvm_demo.databinding.FragmentTasksBinding;

/**
 * Created by mj on 17/3/24.
 */

public class TasksFragment extends BaseFragment {

    private TasksViewModel viewModel;
    private FragmentTasksBinding tasksBinding;

    /**
     * @return 返回一个本Fragment的实例
     */
    public static TasksFragment newInstance() {

        Bundle args = new Bundle();

        TasksFragment fragment = new TasksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        tasksBinding = FragmentTasksBinding.inflate(inflater, container, false);
        tasksBinding.setView(this);
        tasksBinding.setViewmodel(viewModel);

        setHasOptionsMenu(true);

        return tasksBinding.getRoot();
    }

    public void setViewModel(TasksViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
