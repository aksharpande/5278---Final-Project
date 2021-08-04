package com.akshar.myapplication.datalayer;

import com.akshar.myapplication.model.CaseWrapper;
import com.akshar.myapplication.model.VaccinationWrapper;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidAPI {

    @GET("/v1/cases")
    Call<Map<String, CaseWrapper>> getCaseData();

    @GET("/v1/vaccines")
    Call<Map<String, VaccinationWrapper>> getVaccinationData();
}
