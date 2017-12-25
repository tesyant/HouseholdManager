package com.lulua.tesyant.householdmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;

import java.util.ArrayList;

/**
 * Created by USER on 12/21/2017.
 */

public class FixedNeedsAdapter extends RecyclerView.Adapter<FixedNeedsAdapter.FixedViewHolder> {

    private ArrayList<FixedNeeds> fixedNeeds = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public FixedNeedsAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FixedNeedsAdapter.FixedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_needs, parent, false);
        return new FixedViewHolder(view);
    }

    public void addItem(ArrayList<FixedNeeds> mData) {
        this.fixedNeeds = mData;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(FixedNeedsAdapter.FixedViewHolder holder, int position) {
        holder.tvName.setText(fixedNeeds.get(position).getNama());
    }

    @Override
    public int getItemViewType (int position) {
        return 0;
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return fixedNeeds.size();
    }

    public class FixedViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public FixedViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_item);

        }
    }
}
