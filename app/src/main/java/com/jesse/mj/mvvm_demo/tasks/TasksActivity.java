package com.jesse.mj.mvvm_demo.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jesse.mj.mvvm_demo.R;
import com.jesse.mj.mvvm_demo.Utils.ActivityUtils;
import com.jesse.mj.mvvm_demo.addtask.AddEditTaskActivity;
import com.jesse.mj.mvvm_demo.base.BaseActivity;
import com.jesse.mj.mvvm_demo.base.TasksNavigator;
import com.jesse.mj.mvvm_demo.base.ViewModelHolder;
import com.jesse.mj.mvvm_demo.statistics.StatisticsActivity;

/**
 * Task列表
 */
public class TasksActivity extends BaseActivity {

    public static final String TASKS_VIEWMODEL_TAG = "TASKS_VIEWMODEL_TAG";
    private DrawerLayout drawerLayout;
    private TasksViewModel viewModel;
    private TasksNavigator navigator = new TasksNavigator() {
        @Override
        public void addNewTask() {
            TasksActivity.this.addNewTask();
        }
    };

    /**
     * 添加新的Task
     */
    private void addNewTask() {
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        startActivityForResult(intent, AddEditTaskActivity.REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        // 设置toolbar
        setToolbar();

        // 设置侧拉菜单
        setNavigationDrawer();

        // FloatingActionButton
        findViewById(R.id.fab_add_task).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addNewTask();
            }
        });

        TasksFragment tasksFragment = findOrCreateViewFragment();

        viewModel = findOrCreateViewModel();

        tasksFragment.setViewModel(viewModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // 点击左边Home按钮
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AddEditTaskActivity.REQUEST_CODE:
                break;
        }
    }

    /**
     * 创建Bar上的菜单栏
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_actions, menu);
        return true;
    }


    // TODO: 17/3/24 创建列表Fragment
    private TasksFragment findOrCreateViewFragment() {
        TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);
        if (tasksFragment == null) {
            // 新建Fragment
            tasksFragment = TasksFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), tasksFragment, R.id
                    .content_frame);
        }
        return tasksFragment;
    }

    /**
     * 创建或者获取已经存在的ViewModel
     * 相当于用一个无效的Fragment来保存viewmodel
     * @return 返回一个相应的ViewModel
     */
    protected TasksViewModel findOrCreateViewModel() {
        @SuppressWarnings("unchecked")
        ViewModelHolder<TasksViewModel> retainedViewModel = (ViewModelHolder<TasksViewModel>)
                getSupportFragmentManager().findFragmentByTag(TASKS_VIEWMODEL_TAG);

        // 如果能拿到保存的viewmodel则直接返回以前保存的viewmodel
        if (retainedViewModel != null && retainedViewModel.getViewModel() != null) {
            return retainedViewModel.getViewModel();
        }

        // 拿不到创建一个
        TasksViewModel viewModel = new TasksViewModel(this.getApplicationContext(), navigator);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createContainer(viewModel), TASKS_VIEWMODEL_TAG);
        return viewModel;
    }

    /**
     * 设置侧滑菜单
     */
    private void setNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            // 设置抽屉内容
            setDrawerContent(navigationView);
        }
    }

    /**
     * 将NavigationView设置为抽屉内容
     *
     * @param navigationView 作为内容的View
     */
    private void setDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_navigation_menu_item:
                        break;
                    case R.id.statistics_navigation_menu_item:
                        // 进入统计页面
                        Intent intent = new Intent(TasksActivity.this, StatisticsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                                .FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                // 选择后关闭侧边菜单栏
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * 设置ToolBar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
