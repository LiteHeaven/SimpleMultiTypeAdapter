package com.nykj.simplemultiadapter.widget;

import android.view.View;

/**
 * Created by liangy on 2016/12/26.
 */
public class MultiHolder {
    public View itemView;

    public MultiHolder(View itemView) {
        if (itemView == null) {
            throw new NullPointerException();
        }
        this.itemView = itemView;
    }
}
