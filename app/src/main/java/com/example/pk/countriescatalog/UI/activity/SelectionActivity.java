package com.example.pk.countriescatalog.UI.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pk.countriescatalog.R;
import com.example.pk.countriescatalog.models.CountryModel;
import com.example.pk.countriescatalog.net.Retrofit;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SelectionActivity extends AppCompatActivity {
    public static final String COUNTRY_MODELS_KEY = "country models";

    private ArrayList<CountryModel> countryModelsForIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        ButterKnife.bind(this);

        if (countryModelsForIntent == null) {
            Retrofit.getCountries(new Callback<ArrayList<CountryModel>>() {
                @Override
                public void success(ArrayList<CountryModel> countryModels, Response response) {
                    countryModelsForIntent = countryModels;
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }
    }

    @OnClick(R.id.as_show_regions)
    public void showRegions() {
        if (countryModelsForIntent != null) {
            Intent intent = new Intent(this, RegionActivity.class);
            intent.putExtra(COUNTRY_MODELS_KEY, countryModelsForIntent);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Information has not yet come! Wait.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.as_show_sub_regions)
    public void showSubRegions() {
        if (countryModelsForIntent != null) {
            Intent intent = new Intent(this, SubRegionActivity.class);
            intent.putExtra(COUNTRY_MODELS_KEY, countryModelsForIntent);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Information has not yet come! Wait.", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.as_show_countries)
    public void showCountries() {
        if (countryModelsForIntent != null) {
            Intent intent = new Intent(this, CountryActivity.class);
            intent.putExtra(COUNTRY_MODELS_KEY, countryModelsForIntent);

            startActivity(intent);
        } else {
            Toast.makeText(this, "Information has not yet come! Wait.", Toast.LENGTH_SHORT).show();
        }
    }
}
