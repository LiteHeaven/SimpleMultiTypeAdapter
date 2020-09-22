package com.nykj.simplemultiadapter.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 给AdapterView用的adapter。支持多类型、支持不识别时的缺省视图。
 * Create by liangy on 2020/9/22
 */
public abstract class SimpleMultiAdapter<D> extends BaseAdapter {

    protected List<D> dataList = new ArrayList<>();
    private List<ITypeBehavior<D>> capacities;

    public SimpleMultiAdapter() {
    }

    abstract protected @NonNull List<ITypeBehavior<D>> onCreateCapacity();

    private @NonNull List<ITypeBehavior<D>> getCapacity() {
        if (capacities == null) {
            capacities = onCreateCapacity();
            capacities.add(emptyTypeBehavior);
        }
        return capacities;
    }

    public void update(List<D> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        D data = getItem(position);
        for (int i = 0; i < getCapacity().size(); i++) {
            ITypeBehavior<D> each = capacities.get(i);
            if (each.isThisType(data)) {
                return i;
            }
        }
        // 不可能发生，有兜底视图
        throw new IllegalArgumentException("type error at " + position);
    }

    @Override
    public int getViewTypeCount() {
        return getCapacity().size();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public D getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ITypeBehavior<D> behaviorOfType = getCapacity().get(type);

        View v;
        if (convertView == null) {
            MultiHolder holder = behaviorOfType.onCreateViewHolder(parent, type);
            v = holder.itemView;
            v.setTag(holder);
        } else {
            v = convertView;
        }

        D item = getItem(position);
        Object tag = v.getTag();
        // 空视图没有holder
        MultiHolder holder = (MultiHolder) tag;
        behaviorOfType.onBindViewHolder(item, holder);
        return v;
    }

    private ITypeBehavior<D> emptyTypeBehavior = new ITypeBehavior<D>() {
        @Override
        public MultiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = new View(parent.getContext());
            ViewGroup.LayoutParams lp = new AdapterView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
            v.setLayoutParams(lp);
            return new MultiHolder(v);
        }

        @Override
        public void onBindViewHolder(D data, MultiHolder holder) {
            // no-op
        }

        @Override
        public boolean isThisType(D data) {
            // 本对象放在最后一个元素，如果前面都没匹配那就匹配空视图
            return true;
        }
    };
}
