package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableInt;
import android.view.View;

/**
 * Created by akhil on 21/12/16.
 */

public class ErrorVM extends BaseObservable{

    private String mErrorText = "Some error";
    private ObservableInt mErrorViewVisibility = new ObservableInt(View.GONE);

    public void setErrorText(String errorText) {
        mErrorText = errorText;
    }

    @Bindable
    public String getErrorText() {
        return mErrorText;
    }

    @Bindable
    public ObservableInt getErrorViewVisibility() {
        return mErrorViewVisibility;
    }

    public void setErrorViewVisibility(int errorViewVisibility) {
        mErrorViewVisibility.set(errorViewVisibility);
    }

}
