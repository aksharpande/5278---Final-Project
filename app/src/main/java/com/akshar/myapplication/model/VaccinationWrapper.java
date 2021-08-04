package com.akshar.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VaccinationWrapper {
    @SerializedName("All")
    @Expose
    private VaccinationCountry vaccinationCountry;

    public VaccinationCountry getCountry() {
        return vaccinationCountry;
    }
}
