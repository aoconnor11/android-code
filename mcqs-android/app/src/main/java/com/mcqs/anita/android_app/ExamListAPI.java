package com.mcqs.anita.android_app;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject
public class ExamListAPI {

    @JsonField
    private int total;

    @JsonField
    private int perPage;

    @JsonField
    private int currentPage;

    @JsonField
    private int lastPage;

    @JsonField
    private String nextPageUrl;

    @JsonField
    private String prevPageUrl;

    @JsonField
    private int from;

    @JsonField
    private int to;

    @JsonField
    private ExamAPI[] data;

    public ExamListAPI() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public ExamAPI[] getData() {
        return data;
    }

    public void setData(ExamAPI[] data) {
        this.data = data;
    }
}
