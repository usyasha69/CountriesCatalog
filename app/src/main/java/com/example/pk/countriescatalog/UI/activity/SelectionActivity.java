package com.example.pk.countriescatalog.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pk.countriescatalog.R;
import com.example.pk.countriescatalog.models.CountryModel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectionActivity extends AppCompatActivity {
    private ArrayList<CountryModel> countryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ButterKnife.bind(this);

        countryModels = getIntent().getParcelableArrayListExtra(SplashScreenActivity.COUNTRY_MODELS_KEY);
    }

    @OnClick(R.id.as_show_regions)
    public void showRegions() {
        startActivity(new Intent(this, RegionActivity.class)
                .putExtra(SplashScreenActivity.COUNTRY_MODELS_KEY, countryModels));
    }

    @OnClick(R.id.as_show_sub_regions)
    public void showSubRegions() {
        startActivity(new Intent(this, SubRegionActivity.class)
                .putExtra(SplashScreenActivity.COUNTRY_MODELS_KEY, countryModels));
    }

    @OnClick(R.id.as_show_countries)
    public void showCountries() {
        startActivity(new Intent(this, CountryActivity.class)
                .putExtra(SplashScreenActivity.COUNTRY_MODELS_KEY, countryModels));
    }
}
