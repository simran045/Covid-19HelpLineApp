package com.example.covid_19helpline;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfectedSateActivity extends AppCompatActivity {

    EditText edtSearch;
    ListView listView;
    //SimpleArcLoader simpleArcLoader;
    RecyclerView recyclerView;

    public static List<StateModel> countryModelList = new ArrayList<>();
    StateModel countryModel;
    RecyclerViewAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infectedstate);

        listView=findViewById(R.id.listView);
        recyclerView=findViewById(R.id.recyclerview);

        getSupportActionBar().setTitle("India State Wise Cases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        fetchData();



//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getApplicationContext(),CountryDetailsActivity.class).putExtra("position" ,
//                        position));
//            }
//        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void fetchData() {
        RequestQueue requestQueue;
        requestQueue=Volley.newRequestQueue(this);

        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject=response.getJSONObject("data");
                    JSONArray jsonArray =jsonObject.getJSONArray("regional");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject regional=jsonArray.getJSONObject(i);

                          String stateName=regional.getString("loc");
                          int cases=regional.getInt("totalConfirmed");
                          int recovered=regional.getInt("discharged");
                          int deaths=regional.getInt("deaths");

                        Log.d("test", "onResponse: "+stateName);

                          countryModel=new StateModel(stateName,cases,deaths,recovered);
                          countryModelList.add(countryModel);
                          myCustomAdapter=new RecyclerViewAdapter(InfectedSateActivity.this,countryModelList);
                        recyclerView.setAdapter(myCustomAdapter);
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}


//https://api.covid19india.org/data.json
//        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                try {
//                    JSONArray jsonArray=response.getJSONArray("statewise");
//                    for(int i=1;i<jsonArray.length();i++){
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String stateName=jsonObject.getString("state");
//                        String cases=jsonObject.getString("confirmed");
//                        String active=jsonObject.getString("active");
//                        String recovered=jsonObject.getString("recovered");
//                        String deaths=jsonObject.getString("deaths");
//
//
//                        countryModel=new StateModel(stateName,cases,deaths,recovered,active);
//                        countryModelList.add(countryModel);
//                        myCustomAdapter=new RecyclerViewAdapter(InfectedSateActivity.this,countryModelList);
//                        recyclerView.setAdapter(myCustomAdapter);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(jsonObjectRequest);
//    }
//}

