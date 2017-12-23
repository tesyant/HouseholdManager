import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lulua.tesyant.householdmanager.R;

import java.util.List;

import models.Btetap;

/**
 * Created by USER on 12/21/2017.
 */

public class FixedNeedsAdapter extends RecyclerView.Adapter<FixedNeedsAdapter.MyViewHolder> {

    private List<Btetap> btetaps;
    private Activity activity;

    CustomItemClickListener listener;

    @Override
    public FixedNeedsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_keb_tetap, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(view, myViewHolder.getAdapterPosition());
            }
        });
        return null;
    }

    @Override
    public void onBindViewHolder (FixedNeedsAdapter.MyViewHolder holder, int position) {
        holder.title.setText(""+ btetaps.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return btetaps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.fab_title_keb);
        }
    }

    public FixedNeedsAdapter(List<Btetap> btetaps, Activity activity, CustomItemClickListener listener ){
        this.btetaps = btetaps;
        this.activity = activity;
        this.listener = listener;
    }
}
