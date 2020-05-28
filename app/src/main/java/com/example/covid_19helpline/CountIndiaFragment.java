package com.example.covid_19helpline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountIndiaFragment extends Fragment {

    private Toolbar toolbar;
    TextView tvCases,tvRecovered,tvActive, tvTotalDeaths,tvForeign;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollview;
    PieChart mPieChart;
    Button btn;
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_countindia, container, false);

        tvRecovered=view.findViewById(R.id.recovered);
        tvCases=view.findViewById(R.id.tvCases);
        tvTotalDeaths=view.findViewById(R.id.totalDeaths);
        tvActive=view.findViewById(R.id.tvActive);
        tvForeign=view.findViewById(R.id.tvForeign);
        simpleArcLoader=view.findViewById(R.id.loader);
        mPieChart = view.findViewById(R.id.piechart);
        btn=view.findViewById(R.id.btnTrack1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),InfectedSateActivity.class));
            }
        });

        fetchData();
        return view;

    }


    private void fetchData() {

        String url= "https://api.rootnet.in/covid19-in/stats/latest";

        simpleArcLoader.setVisibility(View.VISIBLE);
        requestQueue=Volley.newRequestQueue(getContext());

        final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject=response.getJSONObject("data");

                            JSONObject jsonObject1 =jsonObject.getJSONObject("summary");
                            String cases=jsonObject1.getString("total");
                            String recovered=jsonObject1.getString("discharged");
                            String deaths=jsonObject1.getString("deaths");
                            String cnfIndian=jsonObject1.getString("confirmedCasesIndian");
                            String cnfForeign=jsonObject1.getString("confirmedCasesForeign");

                            Log.d("test", "onResponse: "+cases);

                            tvCases.setText(cases);
                            tvActive.setText(cnfIndian);
                            tvForeign.setText(cnfForeign);
                            tvRecovered.setText(recovered);
                            tvTotalDeaths.setText(deaths);


                              mPieChart.addPieSlice(new PieModel("Cases", Integer.parseInt(jsonObject1.getString("total")), Color.parseColor("#FFA726")));
                              mPieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(jsonObject1.getString("discharged")), Color.parseColor("#66BB6A")));
                              mPieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(jsonObject1.getString("deaths")), Color.parseColor("#EF5350")));
                              mPieChart.addPieSlice(new PieModel("Active", Integer.parseInt(jsonObject1.getString("confirmedCasesIndian")), Color.parseColor("#29B6F6")));
                              mPieChart.startAnimation();
                              simpleArcLoader.setVisibility(View.GONE);

                        } catch (JSONException ex) {
                        ex.printStackTrace();
                        simpleArcLoader.setVisibility(View.GONE);
                    }

            }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        requestQueue.add(jsonObjectRequest);
        }
}


//                    JSONArray jsonArray=response.getJSONArray("statewise");
//                    JSONObject o=jsonArray.getJSONObject(0);
//
//
//                    tvCases.setText(""+o.getString("confirmed"));
//                    tvRecovered.setText(""+ o.getString("recovered"));
//                    tvTotalDeaths.setText(""+o.getString("deaths"));
//                    tvActive.setText(""+o.getString("active"));