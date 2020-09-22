package com.nykj.simplemultiadapter;

import androidx.annotation.Keep;

/**
 * Create by liangy on 2020/9/22
 */
@Keep
public class Entity {

    private int type;

    public Entity(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
