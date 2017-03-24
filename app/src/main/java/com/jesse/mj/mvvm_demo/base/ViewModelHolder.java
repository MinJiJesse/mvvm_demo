package com.jesse.mj.mvvm_demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.annotations.NonNull;

/**
 * Created by mj on 17/3/24.
 */

public class ViewModelHolder<VM> extends BaseFragment {

    private VM viewModel;

    public static <M> ViewModelHolder createContainer(@NonNull M viewModel) {
        ViewModelHolder<M> viewModelHolder = new ViewModelHolder<>();
        viewModelHolder.setViewModel(viewModel);
        return viewModelHolder;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    private void setViewModel(@NonNull VM viewModel) {
        this.viewModel = viewModel;
    }

    public VM getViewModel() {
        return viewModel;
    }
}
