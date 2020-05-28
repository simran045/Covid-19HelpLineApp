package com.example.covid_19helpline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.List;

public class InfectedActivity extends AppCompatActivity {

    EditText edtSearch;
    ListView listView;
    SimpleArcLoader simpleArcLoader;

    public static List<CountryModel> countryModelList = new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infected);

        listView = findViewById(R.id.listView);
        simpleArcLoader = findViewById(R.id.loader2);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fetchData();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), CountryDetailsActivity.class).putExtra("position",
                        position));
            }
        });


//        edtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                myCustomAdapter.getFilter().filter(s);
//                myCustomAdapter.notifyDataSetChanged();
//
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
    }
        public boolean onOptionsItemSelected (@NonNull MenuItem item){
            if (item.getItemId() == android.R.id.home)
                finish();
            return super.onOptionsItemSelected(item);
        }

    private void fetchData() {
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);

        String url = "https://corona.lmao.ninja/v2/countries/";

        simpleArcLoader.setVisibility(View.VISIBLE);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        JSONObject o=jsonObject.getJSONObject("countryInfo");

                        String country=jsonObject.getString("country");
                        String cases=jsonObject.getString("cases");
                        String todayCases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String recovered=jsonObject.getString("recovered");
                        String active=jsonObject.getString("active");
                        String critical=jsonObject.getString("critical");

                        String flag=o.getString("flag");

                        countryModel =new CountryModel(flag,country,cases,todayCases,deaths,todayDeaths,recovered,active,critical);
                        countryModelList.add(countryModel);

                        myCustomAdapter=new MyCustomAdapter(InfectedActivity.this,countryModelList);
                        listView.setAdapter(myCustomAdapter);

                        simpleArcLoader.setVisibility(View.GONE);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);


    }
}

