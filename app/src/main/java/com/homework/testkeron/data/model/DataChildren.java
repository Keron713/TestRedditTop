package com.homework.testkeron.data.model;

import com.google.gson.annotations.SerializedName;

public class DataChildren {

    @SerializedName("thumbnail")
    public String thumbnail;
    @SerializedName("author")
    public String author;
    @SerializedName("author_fullname")
    public String fullName;
    @SerializedName("created_utc")
    public long postTime;
    @SerializedName("num_comments")
    public long commentCount;
    @SerializedName("url")
    public String url;

}
