package com.akshar.myapplication.datalayer;

import com.akshar.myapplication.model.CaseWrapper;
import com.akshar.myapplication.model.CaseCountry;
import com.akshar.myapplication.model.VaccinationCountry;
import com.akshar.myapplication.model.VaccinationWrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidRepository {

    private static CovidRepository singleton_instance = null;
    public Map<String, CaseWrapper> caseStore = new HashMap<String, CaseWrapper>();
    public Map<String, VaccinationWrapper> vaccinationStore = new HashMap<String, VaccinationWrapper>();

    private CovidRepository() {
    }

    public static CovidRepository getInstance() {
        if (singleton_instance == null) {
            singleton_instance = new CovidRepository();
        }
        return singleton_instance;
    }

    public void getCovidData() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-api.mmediagroup.fr")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        CovidAPI service = retrofit.create(CovidAPI.class);
        Call<Map<String, CaseWrapper>> callCases = service.getCaseData();
        Call<Map<String, VaccinationWrapper>> callVaccines = service.getVaccinationData();
        try {
            Response<Map<String, CaseWrapper>> caseResponse = callCases.execute();
            Response<Map<String, VaccinationWrapper>> vaccineResponse = callVaccines.execute();
            caseStore = caseResponse.body();
            vaccinationStore = vaccineResponse.body();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Set<String> getCountryList() {
        return caseStore.keySet();
    }

    public CaseCountry getCasesByCountry(String countryName) {
        CaseWrapper data = caseStore.get(countryName);
        if (data != null) {
            return data.getCountry();
        }
        return null;
    }

    public VaccinationCountry getVaccinesByCountry(String countryName) {
        VaccinationWrapper data = vaccinationStore.get(countryName);
        if (data != null) {
            return data.getCountry();
        }
        return null;
    }

}
