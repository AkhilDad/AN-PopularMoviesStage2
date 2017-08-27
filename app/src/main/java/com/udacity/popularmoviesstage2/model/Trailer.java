package com.udacity.popularmoviesstage2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akhil on 28/12/16.
 */

public class Trailer {

    @SerializedName("id")
    private String mId;

    @SerializedName("key")
    private String mKey;

    @SerializedName("name")
    private String mTrailerName;

    @SerializedName("type")
    private String mTrailerType;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getTrailerName() {
        return mTrailerName;
    }

    public void setTrailerName(String trailerName) {
        mTrailerName = trailerName;
    }

    public String getTrailerType() {
        return mTrailerType;
    }

    public void setTrailerType(String trailerType) {
        mTrailerType = trailerType;
    }
}
