package com.vanhack.wafer.viewpresenter.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanhack.wafer.R;
import com.vanhack.wafer.model.Country;

import java.util.List;

/**
 * Adapter to provide information to the RecyclerView
 */
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> mData;

    public CountryAdapter(List<Country> list) {
        mData = list;
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_country, parent, false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Class to map our ViewHolder
     */
    static class CountryViewHolder extends RecyclerView.ViewHolder {

        //Layer Views
        private final View mBackgroundView;
        private final View mForegroundView;

        //UI Elements
        private final TextView mName;
        private final TextView mCurrency;
        private final TextView mLanguage;

        public CountryViewHolder(View itemView) {
            super(itemView);
            mBackgroundView = itemView.findViewById(R.id.background);
            mForegroundView = itemView.findViewById(R.id.foreground);
            mName = itemView.findViewById(R.id.name);
            mCurrency = itemView.findViewById(R.id.currency);
            mLanguage = itemView.findViewById(R.id.language);
        }

        void setData(Country country) {
            mName.setText(country.getName());
            mCurrency.setText(country.getCurrency());
            mLanguage.setText(country.getLanguage());
        }

        View getBackgroundView() {
            return mBackgroundView;
        }

        View getForegroundView() {
            return mForegroundView;
        }

    }
}
