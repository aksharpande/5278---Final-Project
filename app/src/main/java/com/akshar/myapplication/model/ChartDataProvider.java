package com.akshar.myapplication.model;

import java.util.List;

public interface ChartDataProvider {

    String getCountryName();

    List<Long> getDisplayValues();

    List<String> getDisplayLabels();

    String getUpdate();
}
