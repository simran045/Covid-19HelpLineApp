package com.example.covid_19helpline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DRecyclerViewAdapter extends RecyclerView.Adapter<DRecyclerViewAdapter.MyViewHolder>{

    private Context mcontext;
    private List<DistrictModel> mData;


    public DRecyclerViewAdapter(Context mcontext, List<DistrictModel> mData) {
        this.mcontext = mcontext;
        this.mData = mData;


    }


    @NonNull
    @Override
    public DRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        view=inflater.inflate(R.layout.anim_district_item,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tvCases.setText(mData.get(position).getCases());
        holder.tvActive.setText(mData.get(position).getActive());
        holder.tvRecovered.setText(mData.get(position).getRecovered());
        holder.tvDeaths.setText(mData.get(position).getDeaths());
        holder.tvDistrict.setText(mData.get(position).getDistrict());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvCases,tvActive,tvRecovered,tvDeaths,tvDistrict;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCases=itemView.findViewById(R.id.tvCases);
            tvActive=itemView.findViewById(R.id.active);
            tvRecovered=itemView.findViewById(R.id.recovered);
            tvDeaths=itemView.findViewById(R.id.tvDeaths);
            tvDistrict=itemView.findViewById(R.id.district_name);

        }
    }
}
