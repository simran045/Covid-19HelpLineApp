package com.example.covid_19helpline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryDetailsActivity extends AppCompatActivity {

        String country;
        private int positionCountry;
        SimpleArcLoader simpleArcLoader;
        TextView tvCountry,tvCases,tvRecoverd,tvCritical,tvActive,tvTodayCase,tvTodayDeath,deaths;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_country_details);

            Intent intent=getIntent();
            positionCountry =intent.getIntExtra("position",1);

            tvCountry=findViewById(R.id.tvCountryName);
            tvCases=findViewById(R.id.tvCases);
            tvRecoverd=findViewById(R.id.recovered);
            tvActive=findViewById(R.id.tvActive);
            tvCritical=findViewById(R.id.critical);
            tvTodayCase=findViewById(R.id.todayCases);
            deaths=findViewById(R.id.totalDeaths);
            tvTodayDeath=findViewById(R.id.todayDeaths);

            simpleArcLoader=findViewById(R.id.loader4);

            fetchData();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            // Log.d("test", "onCreate: "+ countryModelList.get(0));


        }


        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId()==android.R.id.home)
                finish();
            return super.onOptionsItemSelected(item);
        }


        private void fetchData() {
            RequestQueue requestQueue;
            requestQueue = Volley.newRequestQueue(this);

            String url = "https://corona.lmao.ninja/v2/countries/";

            simpleArcLoader.setVisibility(View.VISIBLE);

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            JSONObject o = jsonObject.getJSONObject("countryInfo");

                            country = jsonObject.getString("country");
                            int cases = jsonObject.getInt("cases");
                            int todayCases = jsonObject.getInt("todayCases");
                            int deaths1 = jsonObject.getInt("deaths");
                            int todayDeaths = jsonObject.getInt("todayDeaths");
                            int recovered = jsonObject.getInt("recovered");
                            int active = jsonObject.getInt("active");
                            int critical = jsonObject.getInt("critical");

                            String flag = o.getString("flag");


                            if (i == positionCountry) {

                                tvCases.setText("" + cases);
                                tvRecoverd.setText("" + recovered);
                                tvActive.setText("" + active);
                                tvCritical.setText("" + critical);
                                tvTodayCase.setText("" + todayCases);
                                deaths.setText("" + deaths1);
                                tvTodayDeath.setText("" + todayDeaths);
                                Log.d("test1", "onResponse: " + positionCountry);
                                getSupportActionBar().setTitle(country);

                                simpleArcLoader.setVisibility(View.GONE);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.setVisibility(View.GONE);

                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);

                }
            });
            requestQueue.add(jsonArrayRequest);

        }
}
