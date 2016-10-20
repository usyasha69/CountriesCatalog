package com.example.pk.countriescatalog.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pk.countriescatalog.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegionRVAdapter extends RecyclerView.Adapter<RegionRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> regions;

    public RegionRVAdapter(Context context, ArrayList<String> regions) {
        this.context = context;
        this.regions = regions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View root = layoutInflater.inflate(R.layout.regions_rv_item, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.region.setText(regions.get(position));
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rrvi_region)
        TextView region;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
