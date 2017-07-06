package com.example.webwerks1.myapp.movies;

import java.util.List;

/**
 * Created by webwerks1 on 13/5/16.
 */
public class ResponseModel
{


    private int page;
    private int total_results;
    private int total_pages;
    private List<Results> results;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }


}
