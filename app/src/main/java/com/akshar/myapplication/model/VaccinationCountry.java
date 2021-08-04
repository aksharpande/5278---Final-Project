package com.akshar.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VaccinationCountry implements ChartDataProvider {
    @SerializedName("administered")
    @Expose
    private long administered;

    @SerializedName("people_vaccinated")
    @Expose
    private long vaccinatedCount;

    @SerializedName("people_partially_vaccinated")
    @Expose
    private long partialVaccinationCount;

    @SerializedName("population")
    @Expose
    private long population;

    @SerializedName("updated")
    @Expose
    private String lastUpdated;

    @SerializedName("country")
    @Expose
    private String name;

    public long getAdministered() {
        return administered;
    }

    public long getVaccinatedCount() {
        return vaccinatedCount;
    }

    public long getPartialVaccinationCount() {
        return partialVaccinationCount;
    }

    public long getPopulation() {
        return population;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getCountryName() {
        return name;
    }

    @Override
    public List<Long> getDisplayValues() {
        List<Long> values = new ArrayList<>();
        values.add(administered);
        values.add(vaccinatedCount);
        values.add(partialVaccinationCount);
        return values;
    }

    @Override
    public List<String> getDisplayLabels() {
        List<String> displayLabels = new ArrayList<>();
        displayLabels.add("Administered");
        displayLabels.add("Vaccinated");
        displayLabels.add("Partially Vaccinated");
        return displayLabels;
    }

    @Override
    public String getUpdate() {
        return lastUpdated;
    }
}
