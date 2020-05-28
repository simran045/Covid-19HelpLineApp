package com.example.covid_19helpline;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DistrictStateAdapter extends RecyclerView.Adapter<DistrictStateAdapter.ViewHolder>{

   Context context;
   List<DState> states;



    public DistrictStateAdapter(Context context, List<DState> states) {
        this.context = context;
        this.states = states;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView stateTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stateTitle = itemView.findViewById(R.id.stateTitle);
        }
    }

    @NonNull
    @Override
    public DistrictStateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_state_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.stateTitle.setText(states.get(position).getState());

        holder.stateTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start new intent
                Intent i=new Intent(v.getContext(), DistrictDetails.class);
                i.putExtra("district",position+1);//sending
                v.getContext().startActivity(i);
            }
        });

    }

    public interface selectedState{
        void selectedState(DState state);
    }

    @Override
    public int getItemCount() {
        return states.size();
    }
}
