package com.homework.testkeron.model;

import com.google.gson.annotations.SerializedName;

public class DataChildren {

    @SerializedName("thumbnail")
    public String url;
    @SerializedName("name")
    public String name;
    @SerializedName("author_fullname")
    public String fullName;
    @SerializedName("created_utc")
    public long postTime;
    @SerializedName("num_comments")
    public long commentCount;

}
