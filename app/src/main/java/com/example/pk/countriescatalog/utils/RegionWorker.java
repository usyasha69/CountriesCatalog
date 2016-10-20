package com.example.pk.countriescatalog.utils;


import com.example.pk.countriescatalog.models.CountryModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class RegionWorker {

    public static ArrayList<String> foundRegions(ArrayList<CountryModel> countryModels) {
        LinkedHashSet<String> regions = new LinkedHashSet<>();

        for (CountryModel countryModel : countryModels) {
            regions.add(countryModel.region);
        }

        return new ArrayList<>(regions);
    }

    public static ArrayList<String> foundSubRegions(ArrayList<CountryModel> countryModels) {
        LinkedHashSet<String> subRegions = new LinkedHashSet<>();

        for (CountryModel countryModel : countryModels) {
            subRegions.add(countryModel.subregion);
        }

        return new ArrayList<>(subRegions);
    }

    public static ArrayList<CountryModel> useFilter(ArrayList<CountryModel> countryModels
            , String filter) {
        ArrayList<CountryModel> filterCountryModels = new ArrayList<>();

        for (CountryModel countryModel : countryModels) {
            if (countryModel.name.contains(filter) || countryModel.capital.contains(filter)
                    || countryModel.region.contains(filter) || countryModel.subregion.contains(filter)
                    || isContainsLanguagesFilter(countryModel.languages, filter)) {
                filterCountryModels.add(countryModel);
            }
        }

        return filterCountryModels;
    }

    private static boolean isContainsLanguagesFilter(ArrayList<String> languages, String filter) {
        boolean result = false;

        for (String language : languages) {
            if (language.contains(filter)) {
                result = true;
            }
        }

        return result;
    }
}
