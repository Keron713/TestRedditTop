package com.homework.testkeron.model;

import com.google.gson.annotations.SerializedName;

public class DataReddit {

    @SerializedName("after")
    public String after;

    @SerializedName("children")
    public DataHolder[] childrenList;

}
