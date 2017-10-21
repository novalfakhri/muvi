package com.novalfakhri.muvi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Toshiba on 8/25/2017.
 */

public class TrailersResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Trailers> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Trailers> getResults() {
        return results;
    }

    public void setResults(List<Trailers> results) {
        this.results = results;
    }
}
