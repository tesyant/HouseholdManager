import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lulua.tesyant.householdmanager.R;

import java.util.List;

import models.Btidaktetap;

/**
 * Created by USER on 12/22/2017.
 */

public class UnfixedNeedsAdapter extends RecyclerView.Adapter<UnfixedNeedsAdapter.MyViewHolder> {

    private List<Btidaktetap> btidaktetaps;
    private Activity activity;

    CustomItemClickListener listener;


    @Override
    public UnfixedNeedsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_keb_tdk_tetap, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                listener.onItemClick(view, myViewHolder.getAdapterPosition());
            }
        });

        return null;
    }

    @Override
    public void onBindViewHolder(UnfixedNeedsAdapter.MyViewHolder holder, int position) {
        holder.title.setText(""+btidaktetaps.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.fab_title_keb_tdk_ttp);
        }
    }

    public UnfixedNeedsAdapter(List<Btidaktetap> btidaktetaps, Activity activity, CustomItemClickListener listener){
        this.btidaktetaps = btidaktetaps;
        this.activity = activity;
        this.listener = listener;
    }
}
