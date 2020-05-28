package com.example.covid_19helpline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DistrictDetails extends AppCompatActivity {


    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    DistrictModel districtModel;
    private DRecyclerViewAdapter myCustomAdapter1;
    public static List<DistrictModel> districtModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_districtdetails);

        recyclerView=findViewById(R.id.recyclerview1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fetchDistricts();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchDistricts(){
        String url="https://api.covid19india.org/v2/state_district_wise.json";
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                districtModelList.clear();
                for (int i = 1; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String state = jsonObject.getString("state");
                        getSupportActionBar().setTitle("District-Wise Cases");
                        JSONArray jsonArray=jsonObject.getJSONArray("districtData");
                        for(int j=0;j<jsonArray.length();j++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                            String district = jsonObject1.getString("district");
                            String cases = jsonObject1.getString("confirmed");
                            String active = jsonObject1.getString("active");
                            String recovered = jsonObject1.getString("recovered");
                            String deaths = jsonObject1.getString("deceased");

                            Intent intent = getIntent();
                            Log.d("p", "onResponse: " + intent.getIntExtra("district",1));
                            if (intent.getIntExtra("district",1)==i) {
                                districtModel = new DistrictModel(district, cases, deaths, recovered, active);
                                districtModelList.add(districtModel);
                                myCustomAdapter1 = new DRecyclerViewAdapter(DistrictDetails.this, districtModelList);
                                recyclerView.setAdapter(myCustomAdapter1);

                            }

//                        }
                        }



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
