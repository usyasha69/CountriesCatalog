package com.example.pk.countriescatalog.UI.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pk.countriescatalog.R;
import com.example.pk.countriescatalog.models.CountryModel;
import com.example.pk.countriescatalog.net.Retrofit;
import com.example.pk.countriescatalog.utils.CountryDataManager;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreenActivity extends AppCompatActivity {
    public static final String COUNTRY_MODELS_KEY = "country models";
    public static final String SP_IS_SAVE_DATA = "is save";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final CountryDataManager countryDataManager = new CountryDataManager(this);
        sharedPreferences = getSharedPreferences("isSaveData", Context.MODE_PRIVATE);

        if (!sharedPreferences.getBoolean(SP_IS_SAVE_DATA, false)) {
            Retrofit.getCountries(new Callback<ArrayList<CountryModel>>() {
                @Override
                public void success(ArrayList<CountryModel> countryModels, Response response) {
                    countryDataManager.saveData(countryModels);
                    countryDataManager.saveCountryImageToSDCard();

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor
                            .putBoolean(SP_IS_SAVE_DATA, true)
                            .apply();

                    startActivity(new Intent(SplashScreenActivity.this, SelectionActivity.class)
                            .putExtra(COUNTRY_MODELS_KEY, countryModels));
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        } else {
            startActivity(new Intent(SplashScreenActivity.this, SelectionActivity.class)
                    .putExtra(COUNTRY_MODELS_KEY, countryDataManager.readData()));
        }
    }
}
