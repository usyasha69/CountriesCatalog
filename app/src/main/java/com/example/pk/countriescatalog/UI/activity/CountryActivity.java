package com.example.pk.countriescatalog.UI.activity;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pk.countriescatalog.R;
import com.example.pk.countriescatalog.adapters.CountryRVAdapter;
import com.example.pk.countriescatalog.models.CountryModel;
import com.example.pk.countriescatalog.utils.RegionWorker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountryActivity extends AppCompatActivity {
    @BindView(R.id.ac_countries_rv)
    RecyclerView recyclerView;
    @BindView(R.id.ac_add_filter)
    EditText filter;

    private CountryRVAdapter countryRVAdapter;
    private ArrayList<CountryModel> countryModels;
    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);

        assetManager = getAssets();

        countryModels = getIntent().getParcelableArrayListExtra(SplashScreenActivity.COUNTRY_MODELS_KEY);

        countryRVAdapter = new CountryRVAdapter(this, countryModels);
        countryRVAdapter.setOnItemClickListener(new CountryRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CountryModel item, int position) {
                InputStream inputStream = null;

                try {
                    inputStream = assetManager.open(item.name + ".png");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Drawable drawable = Drawable.createFromStream(inputStream, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(CountryActivity.this)
                        .setTitle("Country information")
                        .setMessage("Country: " + item.name + "\nCapital: " + item.capital
                                + "\nRegion: " + item.region + "\nSub region: " + item.subregion
                                + "\nArea: " + item.area + "\nLanguages: " + item.languages.toString())
                        .setCancelable(true)
                        .setIcon(drawable);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(countryRVAdapter);
    }

    @OnClick(R.id.ac_use_filter)
    public void useFilter() {
        if (!filter.getText().toString().equals("")) {
            RegionWorker.useFilter(countryModels, filter.getText().toString());

            countryRVAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Please, add filter!", Toast.LENGTH_SHORT).show();
        }
    }
}
