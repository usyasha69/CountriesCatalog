package com.example.pk.countriescatalog.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.example.pk.countriescatalog.models.CountryModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

    /**
     * This method saved country images at assets to SD card.
     */
    public void saveCountryImageToSDCard() {
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File sdCard = Environment.getExternalStorageDirectory();

                File outFile = new File(sdCard.getAbsolutePath() + "/CountryImages", filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch(IOException e) {
                e.printStackTrace();
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
