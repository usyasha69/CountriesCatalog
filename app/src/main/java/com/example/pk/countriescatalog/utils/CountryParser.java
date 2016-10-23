package com.example.pk.countriescatalog.utils;

import com.example.pk.countriescatalog.models.CountryModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class CountryParser {
    private static final Gson gson = new GsonBuilder().create();

    public static ArrayList<String> toJson(ArrayList<CountryModel> countryModels) {
        ArrayList<String> json = new ArrayList<>();

        for (CountryModel countryModel : countryModels) {
            json.add(gson.toJson(countryModel));
        }

        return json;
    }

    public static ArrayList<CountryModel> fromJson(ArrayList<String> json) {
        ArrayList<CountryModel> countryModels = new ArrayList<>();

        for (String s : json) {
            countryModels.add(gson.fromJson(s, CountryModel.class));
        }

        return countryModels;
    }
}
