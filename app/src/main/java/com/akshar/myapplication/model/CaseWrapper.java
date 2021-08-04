package com.akshar.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaseWrapper {

    @SerializedName("All")
    @Expose
    private CaseCountry caseCountry;

    public CaseCountry getCountry() {
        return caseCountry;
    }
}
