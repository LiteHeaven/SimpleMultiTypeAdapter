package com.nykj.simplemultiadapter.widget;

import android.view.ViewGroup;

/**
 * Create by liangy on 2020/9/22
 */
public interface ITypeBehavior<D> {
    MultiHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(D data, MultiHolder holder);

    boolean isThisType(D data);
}