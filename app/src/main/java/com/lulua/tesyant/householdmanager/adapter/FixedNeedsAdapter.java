package com.lulua.tesyant.householdmanager.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lulua.tesyant.householdmanager.R;
import com.lulua.tesyant.householdmanager.activities.AddFixedNeedsActivity;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;

import java.util.LinkedList;

/**
 * Created by USER on 12/21/2017.
 */

public class FixedNeedsAdapter extends RecyclerView.Adapter<FixedNeedsAdapter.FixedViewHolder> {

    private LinkedList<FixedNeeds> listFixedNeeds;
    private Activity activity;

    public FixedNeedsAdapter(Activity activity) {
        this.activity = activity;
    }

    public LinkedList<FixedNeeds> getListFixedNeeds() {
        return listFixedNeeds;
    }

    public void setListFixedNeeds(LinkedList<FixedNeeds> listFixedNeeds) {
        this.listFixedNeeds = listFixedNeeds;
    }

    @Override
    public FixedNeedsAdapter.FixedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_needs, parent, false);
        return new FixedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FixedNeedsAdapter.FixedViewHolder holder, int position) {
        holder.tvName.setText(getListFixedNeeds().get(position).getNama());
        holder.tvName.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, AddFixedNeedsActivity.class);
                intent.putExtra(AddFixedNeedsActivity.EXTRA_POSITION, position);
                intent.putExtra(AddFixedNeedsActivity.EXTRA_FIXED_NEEDS, getListFixedNeeds().get(position));
                activity.startActivityForResult(intent, AddFixedNeedsActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListFixedNeeds().size();
    }

    public class FixedViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public FixedViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_item);

        }
    }
}
