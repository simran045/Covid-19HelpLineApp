package com.example.covid_19helpline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mcontext;
    private List<StateModel> mData;


    public RecyclerViewAdapter(Context mcontext, List<StateModel> mData) {
        this.mcontext = mcontext;
        this.mData = mData;


    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        view=inflater.inflate(R.layout.anim_row_item,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tvCases.setText(""+mData.get(position).getCases());
        holder.tvRecovered.setText(""+mData.get(position).getRecovered());
        holder.tvDeaths.setText(""+mData.get(position).getDeaths());
        holder.tvState.setText(mData.get(position).getState());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvCases,tvRecovered,tvDeaths,tvState;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCases=itemView.findViewById(R.id.tvCases);
            tvRecovered=itemView.findViewById(R.id.recovered);
            tvDeaths=itemView.findViewById(R.id.tvDeaths);
            tvState=itemView.findViewById(R.id.state_name);

        }
    }
}
