package com.jesse.mj.mvvm_demo.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.UUID;

/**
 * Created by mj on 17/3/24.
 * 封装task的对象
 */

public final class Task {

    /**
     * id是必须的，而且唯一不可更改
     */
    @NonNull
    private final String id;

    /**
     * title可以为空
     */
    @Nullable
    private String title;

    /**
     * description可以为空
     */
    @Nullable
    private String description;

    /**
     * task是否完成的标志
     */
    private boolean completed;

    /**
     * 用来构造一个新的Task对象
     * @param title 标题
     * @param description 内容
     */
    public Task(@Nullable String title, @Nullable String description) {
        this.title = title;
        this.description = description;
        // 产生单一的id
        this.id = String.valueOf(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Task task = (Task) o;

        return id.equals(task.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Task with title " + title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * @return true，task为激活状态；false，task未激活状态
     */
    public boolean isActive() {
        return !completed;
    }

    /**
     * @return true为完成，false为未完成
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @return 返回在列表上展示的标题
     */
    public String getTitleForList() {
        if (!TextUtils.isEmpty(title)) {
            return title;
        }
        return TextUtils.isEmpty(title) ? description : title;
    }

    //    public Task(@NonNull String id, String title, String description) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//    }
//
//    /**
//     * 用来完成一个已经存在的task
//     * @param title 标题
//     * @param description 内容
//     * @param id id
//     * @param completed true，已完成；false，未完成
//     */
//    public Task(String title, String description, String id, boolean completed) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.completed = completed;
//    }


}
