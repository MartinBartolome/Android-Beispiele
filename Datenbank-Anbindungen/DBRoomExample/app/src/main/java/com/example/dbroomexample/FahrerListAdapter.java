package com.example.dbroomexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FahrerListAdapter extends RecyclerView.Adapter<FahrerListAdapter.FahrerViewHolder>{
    class FahrerViewHolder extends RecyclerView.ViewHolder {
        private final TextView fahrerItemView;
        private final TextView teamItemView;
        private final TextView punkteItemView;
        private final TextView siegeItemView;


        private FahrerViewHolder(View itemView) {
            super(itemView);
            fahrerItemView = itemView.findViewById(R.id.fahrerTextView);
            teamItemView = itemView.findViewById(R.id.teamTextView);
            punkteItemView = itemView.findViewById(R.id.punktTextView);
            siegeItemView = itemView.findViewById(R.id.siegeTextView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Fahrer> mFahrers;

    FahrerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public FahrerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FahrerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FahrerViewHolder holder, int position) {
        if (mFahrers != null) {
            Fahrer current = mFahrers.get(position);
            holder.fahrerItemView.setText(current.getName());
            holder.teamItemView.setText(current.getTeam());
            holder.punkteItemView.setText(Integer.toString(current.getPunkte()));
            holder.siegeItemView.setText(Integer.toString(current.getSiege()));
        } else {
            // Covers the case of data not being ready yet.
            holder.fahrerItemView.setText("No Name");
            holder.teamItemView.setText("No Name");
            holder.punkteItemView.setText(Integer.toString(0));
            holder.siegeItemView.setText(Integer.toString(02));
        }
    }

    void setFahress(List<Fahrer> fahrers){
        mFahrers = fahrers;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mFahrers != null)
            return mFahrers.size();
        else return 0;
    }
}
