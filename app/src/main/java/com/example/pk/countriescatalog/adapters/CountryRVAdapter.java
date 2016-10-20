package com.example.pk.countriescatalog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pk.countriescatalog.R;
import com.example.pk.countriescatalog.models.CountryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryRVAdapter extends RecyclerView.Adapter<CountryRVAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(CountryModel item, int position);
    }

    private Context context;
    private ArrayList<CountryModel> countryModels;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(CountryRVAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public CountryRVAdapter(Context context, ArrayList<CountryModel> countryModels) {
        this.context = context;
        this.countryModels = countryModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View root = layoutInflater.inflate(R.layout.countries_rv_item, parent, false);

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.country.setText(countryModels.get(position).name);
    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.crvi_country)
        TextView country;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(countryModels.get(getAdapterPosition()), getAdapterPosition());
            } else {
                throw new RuntimeException("You must init onItemClickListener " +
                        "by calling setOnItemClickListener() method.");
            }
        }
    }
}
