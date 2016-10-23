package com.example.pk.countriescatalog.utils;

import android.content.Context;

import com.example.pk.countriescatalog.models.CountryModel;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CountryDataManager {
    private Context context;

    public CountryDataManager(Context context) {
        this.context = context;
    }

    /**
     * This method save country models data to internal storage in json- format.
     *
     * @param countryModels - list with country models
     */
    public void saveData(ArrayList<CountryModel> countryModels) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput("country data", Context.MODE_PRIVATE);

            ArrayList<String> json = CountryParser.toJson(countryModels);

            StringBuilder data = new StringBuilder();

            for (String s : json) {
                data.append(s).append("\n");
            }

            fileOutputStream.write(data.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method read data from internal storage.
     *
     * @return - list with country models
     */
    public ArrayList<CountryModel> readData() {
        ArrayList<CountryModel> countryModels = new ArrayList<>();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = context.openFileInput("country data");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            ArrayList<String> json = new ArrayList<>();
            String data;

            while ((data = bufferedReader.readLine()) != null) {
                json.add(data + "\n");
            }

            countryModels = CountryParser.fromJson(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return countryModels;
    }
}
