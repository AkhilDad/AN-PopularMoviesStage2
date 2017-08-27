package com.udacity.popularmoviesstage2.view;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 21/06/16.
 */
public class RecyclerBindingAdapter<T> extends RecyclerView.Adapter<DataBindingViewHolder>{

    private List<T> mDataList;
    @LayoutRes
    private int mLayoutId;
    private int mVariableId;

    public RecyclerBindingAdapter(List<T> dataList, @LayoutRes int layoutId, int variableId) {
        mDataList = dataList != null ? dataList : new ArrayList<T>();
        mLayoutId = layoutId;
        mVariableId = variableId;
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataBindingViewHolder(LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        holder.getBinding().setVariable(mVariableId, mDataList.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDataList.size() > 0 ? mDataList.size() : 0;
    }


    public void addData(List<T> dataList) {
        int position = mDataList.size() - 1;
        mDataList.addAll(dataList);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void updateData(List<T> dataList) {
        clearAll();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mDataList.clear();
        notifyDataSetChanged();
    }
}
