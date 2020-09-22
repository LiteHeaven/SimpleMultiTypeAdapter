package com.nykj.simplemultiadapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nykj.simplemultiadapter.widget.ITypeBehavior;
import com.nykj.simplemultiadapter.widget.SimpleMultiAdapter;
import com.nykj.simplemultiadapter.widget.MultiHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by liangy on 2020/9/22
 */
public class MyAdapter extends SimpleMultiAdapter<Entity> {

    @NonNull
    @Override
    protected List<ITypeBehavior<Entity>> onCreateCapacity() {
        List<ITypeBehavior<Entity>> result = new ArrayList<>();
        result.add(new ITypeBehavior<Entity>() {
            @Override
            public MultiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView tv = new TextView(parent.getContext());
                tv.setLayoutParams(new AdapterView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new TextHolder(tv);
            }

            @Override
            public void onBindViewHolder(Entity data, MultiHolder holder) {
                TextHolder h = (TextHolder) holder;
                h.setText("i am a TextView.");
            }

            @Override
            public boolean isThisType(Entity data) {
                return data.getType() == 1;
            }
        });
        result.add(new ITypeBehavior<Entity>() {
            @Override
            public MultiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ImageView tv = new ImageView(parent.getContext());
                tv.setLayoutParams(new AdapterView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new ImageHolder(tv);
            }

            @Override
            public void onBindViewHolder(Entity data, MultiHolder holder) {
                ImageHolder h = (ImageHolder) holder;
                h.setImage(R.drawable.ic_launcher_foreground);
            }

            @Override
            public boolean isThisType(Entity data) {
                return data.getType() == 2;
            }
        });
        return result;
    }

    public static class TextHolder extends MultiHolder {

        private TextView textView;

        public TextHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView;
        }

        public void setText(String text){
            textView.setText(text);
        }
    }

    public static class ImageHolder extends MultiHolder {

        private ImageView imageView;

        public ImageHolder(ImageView itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView;
        }

        public void setImage(int res){
            imageView.setImageResource(res);
        }
    }
}
