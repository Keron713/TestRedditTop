package com.homework.testkeron.model;

public class PostModel {
    private String author;
    private long postTime;
    private String url;
    private long commentCount;
    private String fullName;

    public PostModel(String author, long postTime, String url, long commentCount, String fullName) {
        this.author = author;
        this.postTime = postTime;
        this.url = url;
        this.commentCount = commentCount;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public PostModel() {

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPostTime() {
        return postTime;
    }

    public void setPostTime(long postTime) {
        this.postTime = postTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public void toPostModel(DataChildren children) {
        this.author = children.name;
        this.postTime = children.postTime*1000;
        this.url = children.url;
        this.commentCount = children.commentCount;
        this.fullName = children.fullName;
    }
}
