package com.akshar.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CaseCountry implements ChartDataProvider{

    @SerializedName("confirmed")
    @Expose
    private long confirmed;

    @SerializedName("recovered")
    @Expose
    private long recovered;

    @SerializedName("deaths")
    @Expose
    private long deaths;

    @SerializedName("population")
    @Expose
    private long population;

    @SerializedName("updated")
    @Expose
    private String lastUpdated;

    @SerializedName("country")
    @Expose
    private String name;

    public long getConfirmed() {
        return confirmed;
    }

    public long getRecovered() {
        return recovered;
    }

    public String getName() {
        return name;
    }

    public long getDeaths() {
        return deaths;
    }

    public long getPopulation() {
        return population;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String getCountryName() {
        return name;
    }

    @Override
    public List<Long> getDisplayValues() {
        List<Long> values = new ArrayList<>();
        values.add(confirmed);
        values.add(deaths);
        values.add(recovered);
        return values;
    }

    @Override
    public List<String> getDisplayLabels() {
        List<String> displayLabels = new ArrayList<>();
        displayLabels.add("Confirmed");
        displayLabels.add("Deaths");
        displayLabels.add("Recovered");
        return displayLabels;
    }

    @Override
    public String getUpdate() {
        return lastUpdated;
    }
}
