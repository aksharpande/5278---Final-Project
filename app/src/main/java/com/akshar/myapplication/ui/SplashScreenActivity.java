package com.akshar.myapplication.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.akshar.myapplication.R;
import com.akshar.myapplication.datalayer.CovidRepository;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        AsyncTask.execute(new GetData());

    }

    class GetData implements Runnable {
        @Override
        public void run() {
            CovidRepository.getInstance().getCovidData();
            handler.postDelayed(new NavigateActivityRunnable(), 3000);
        }

    }

    class NavigateActivityRunnable implements Runnable {
        @Override
        public void run() {
            Intent countryIntent = new Intent(SplashScreenActivity.this, CountryActivity.class);
            countryIntent.putExtra(CountryActivity.ARG_COUNTRY_NAME, "Global");
            startActivity(countryIntent);
        }
    }

}