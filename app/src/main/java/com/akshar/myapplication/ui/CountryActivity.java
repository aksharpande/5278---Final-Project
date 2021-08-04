package com.akshar.myapplication.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.akshar.myapplication.R;
import com.akshar.myapplication.datalayer.CovidRepository;
import com.akshar.myapplication.model.ChartDataProvider;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CountryActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    public static String ARG_COUNTRY_NAME = "country_name";
    private PieChart pieChart;
    private AutoCompleteTextView autoCompleteTextView;
    private RadioGroup dataTypeRadioGroup;
    private String currentCountryName = "Global";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        pieChart = findViewById(R.id.pieChart);
        autoCompleteTextView = findViewById(R.id.auto_complete_text_view);
        dataTypeRadioGroup = findViewById(R.id.data_type);
        setupAutoCompleteView();
        dataTypeRadioGroup.setOnCheckedChangeListener(this);
        Bundle bundle = getIntent().getExtras();
        currentCountryName = bundle.getString(ARG_COUNTRY_NAME);
        updateData();
        //Toast.makeText(this,"Loading...", Toast.LENGTH_SHORT).show();

    }

    private void setupAutoCompleteView() {
        Set<String> countryNames = CovidRepository.getInstance().getCountryList();
        String[] countryArray = new String[countryNames.size()];
        countryArray = countryNames.toArray(countryArray);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, countryArray);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
                currentCountryName = arrayAdapter.getItem(position);
                updateData();
            }
        });

    }

    private void updateData() {
        ChartDataProvider charDataProvider;
        List<Integer> colorList = new ArrayList<>();
        if(dataTypeRadioGroup.getCheckedRadioButtonId() == R.id.case_radio) {
            charDataProvider = CovidRepository.getInstance().getCasesByCountry(currentCountryName);
            colorList.add(Color.parseColor("#FFFF00"));
            colorList.add(Color.parseColor("#FF8A80"));
            colorList.add(Color.parseColor("#00E676"));
        } else {
            charDataProvider = CovidRepository.getInstance().getVaccinesByCountry(currentCountryName);
            colorList.add(Color.parseColor("#81D4FA"));
            colorList.add(Color.parseColor("#18FFFF"));
            colorList.add(Color.parseColor("#00BFA5"));
        }
        updateLayoutData(charDataProvider, colorList);
    }

    private void updateLayoutData(ChartDataProvider chartDataProvider, List<Integer> colorList) {
        if(chartDataProvider == null)    {
            pieChart.clear();
            pieChart.invalidate();
            return;
        }
            List<String> labels = chartDataProvider.getDisplayLabels();
        ArrayList<PieEntry> pieEntries = new ArrayList();
        int count = 0;
        for (Long value : chartDataProvider.getDisplayValues()) {
            pieEntries.add(new PieEntry(value, labels.get(count++)));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        PieData pieData = new PieData(pieDataSet);
        pieDataSet.setColors(colorList);
        pieDataSet.setSliceSpace(2f);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        updateData();
    }
}