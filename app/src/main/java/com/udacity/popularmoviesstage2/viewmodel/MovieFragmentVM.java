package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableInt;
import android.view.View;


/**
 * Created by akhil on 21/12/16.
 */

public class MovieFragmentVM extends BaseObservable{

    private ObservableInt mRecyclerViewVisibility = new ObservableInt(View.VISIBLE);
    private ObservableInt mProgressViewVisibility = new ObservableInt(View.VISIBLE);
    private ErrorVM mErrorVM = new ErrorVM();

    @Bindable
    public ObservableInt getRecyclerViewVisibility() {
        return mRecyclerViewVisibility;
    }

    public void setRecyclerViewVisibility(int recyclerViewVisibility) {
        mRecyclerViewVisibility.set(recyclerViewVisibility);
    }

    @Bindable
    public ObservableInt getProgressViewVisibility() {
        return mProgressViewVisibility;
    }

    public void setProgressViewVisibility(int progressViewVisibility) {
        mProgressViewVisibility.set(progressViewVisibility);
    }

    public void setErrorViewVisibility(int errorViewVisibility) {
        mErrorVM.setErrorViewVisibility(errorViewVisibility);
    }


    public void setErrorText(String errorText) {
        mErrorVM.setErrorText(errorText);
    }

    public ErrorVM getErrorVM() {
        return mErrorVM;
    }

    @Bindable
    public void setErrorVM(ErrorVM errorVM) {
        mErrorVM = errorVM;
    }
}
