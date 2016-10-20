package com.example.pk.countriescatalog.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pk.countriescatalog.R;
import com.example.pk.countriescatalog.adapters.SubregionRVAdapter;
import com.example.pk.countriescatalog.models.CountryModel;
import com.example.pk.countriescatalog.utils.RegionWorker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubRegionActivity extends AppCompatActivity {
    @BindView(R.id.asr_sub_regions_rv)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_region);
        ButterKnife.bind(this);

        ArrayList<CountryModel> countryModels =
                getIntent().getParcelableArrayListExtra(SelectionActivity.COUNTRY_MODELS_KEY);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SubregionRVAdapter(this, RegionWorker.foundSubRegions(countryModels)));
    }
}
