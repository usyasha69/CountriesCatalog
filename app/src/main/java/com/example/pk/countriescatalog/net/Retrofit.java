package com.example.pk.countriescatalog.net;

import com.example.pk.countriescatalog.models.CountryModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class Retrofit {
    private static final String BASE_URL = "https://restcountries.eu";
    private static CountryAPI countryAPI;

    static {
        initialize();
    }

    private interface CountryAPI {
        @GET("/rest/v1/all")
        void getCountries(Callback<ArrayList<CountryModel>> callback);
    }

    private static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        countryAPI = restAdapter.create(CountryAPI.class);
    }

    public static void getCountries(Callback<ArrayList<CountryModel>> callback) {
        countryAPI.getCountries(callback);
    }
}
