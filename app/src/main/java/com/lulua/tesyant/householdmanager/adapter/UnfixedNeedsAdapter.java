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
import com.lulua.tesyant.householdmanager.activities.AddUnfixedNeedsActivity;
import com.lulua.tesyant.householdmanager.models.FixedNeeds;
import com.lulua.tesyant.householdmanager.models.UnfixedNeeds;

import java.util.LinkedList;

/**
 * Created by USER on 12/22/2017.
 */

public class UnfixedNeedsAdapter extends RecyclerView.Adapter<UnfixedNeedsAdapter.UnfixedViewHolder> {

    private LinkedList<UnfixedNeeds> listUnfixedNeeds;
    private Activity activity;

    public UnfixedNeedsAdapter(Activity activity) {
        this.activity = activity;
    }

    public LinkedList<UnfixedNeeds> getListUnfixedNeeds() {
        return listUnfixedNeeds;
    }

    public void setListUnfixedNeeds(LinkedList<UnfixedNeeds> listUnfixedNeeds) {
        this.listUnfixedNeeds = listUnfixedNeeds;
    }

    @Override
    public UnfixedNeedsAdapter.UnfixedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_needs, parent, false);
        return new UnfixedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UnfixedNeedsAdapter.UnfixedViewHolder holder, int position) {
        holder.tvName.setText(getListUnfixedNeeds().get(position).getNama());
        holder.tvName.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, AddUnfixedNeedsActivity.class);
                intent.putExtra(AddUnfixedNeedsActivity.EXTRA_POSITION, position);
                intent.putExtra(AddUnfixedNeedsActivity.EXTRA_FIXED_NEEDS, getListUnfixedNeeds().get(position));
                activity.startActivityForResult(intent, AddUnfixedNeedsActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListUnfixedNeeds().size();
    }

    public class UnfixedViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public UnfixedViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_item);

        }
    }
}
