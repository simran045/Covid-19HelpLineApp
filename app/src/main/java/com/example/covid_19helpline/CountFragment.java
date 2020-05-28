package com.example.covid_19helpline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class CountFragment extends Fragment {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases, tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollview;
    PieChart mPieChart;
    Button button,btnTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_count, container, false);
        tvCases=view.findViewById(R.id.tvCases);
        tvRecovered=view.findViewById(R.id.recovered);
        tvCritical=view.findViewById(R.id.critical);
        tvActive=view.findViewById(R.id.tvActive);
        tvTodayCases=view.findViewById(R.id.todayCases);
        tvTotalDeaths=view.findViewById(R.id.totalDeaths);
        tvTodayDeaths=view.findViewById(R.id.todayDeaths);
        tvAffectedCountries=view.findViewById(R.id.affectedCountries);

        simpleArcLoader=view.findViewById(R.id.loader);
        scrollview=view.findViewById(R.id.scrollStats);
        mPieChart = view.findViewById(R.id.piechart);
        button=view.findViewById(R.id.btnTrack);
        btnTest=view.findViewById(R.id.testing);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       startActivity(new Intent(getActivity(),InfectedActivity.class));
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                SymptomFragment symptomFragment = new SymptomFragment();
                FragmentTransaction transaction= getFragmentManager().beginTransaction();
                transaction.replace(R.id.container_fragment,symptomFragment);
                transaction.commit();
            }
        });

        fetchData();

        return view;

    }



    private void fetchData() {

        String url= "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.setVisibility(View.VISIBLE);

        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    tvCases.setText(""+response.getString("cases"));
                    tvRecovered.setText(""+ response.getLong("recovered"));
                    tvCritical.setText(""+response.getString("critical"));
                    tvTodayCases.setText(""+response.getString("todayCases"));
                    tvTodayDeaths.setText(""+response.getString("todayDeaths"));
                    tvTotalDeaths.setText(""+response.getString("deaths"));
                    tvActive.setText(""+response.getString("active"));
                    tvAffectedCountries.setText(""+response.getString("affectedCountries"));

                    mPieChart.addPieSlice(new PieModel("Cases", response.getLong("cases"), Color.parseColor("#FFA726")));
                    mPieChart.addPieSlice(new PieModel("Recovered", response.getLong("recovered"), Color.parseColor("#66BB6A")));
                    mPieChart.addPieSlice(new PieModel("Deaths", response.getLong("deaths"), Color.parseColor("#EF5350")));
                    mPieChart.addPieSlice(new PieModel("Active", response.getLong("active"), Color.parseColor("#29B6F6")));
                    mPieChart.startAnimation();

                    simpleArcLoader.setVisibility(View.GONE);
                    scrollview.setVisibility(View.VISIBLE);



                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollview.setVisibility(View.VISIBLE);

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollview.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        //to handle request,add request queue
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);


    }

}