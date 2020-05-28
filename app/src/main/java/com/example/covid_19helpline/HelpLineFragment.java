package com.example.covid_19helpline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class HelpLineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_helpline, container, false);

        mRecyclerView=view.findViewById(R.id.recycler_view);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mExampleList=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(getContext());
        parseJSON();
        return view;

    }

    private void parseJSON(){
        String url="https://api.rootnet.in/covid19-in/contacts";
        JsonObjectRequest request= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject=response.getJSONObject("data");
                    JSONObject jsonObject1=jsonObject.getJSONObject("contacts");
                    JSONArray jsonArray =jsonObject1.getJSONArray("regional");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject regional=jsonArray.getJSONObject(i);


                        String location=regional.getString("loc");
                        String number=regional.getString("number");

                        Log.d("test", "onResponse: "+location);

                        mExampleList.add(new ExampleItem(location,number));
                    }

                    mExampleAdapter =new ExampleAdapter(getContext(),mExampleList);
                    mRecyclerView.setAdapter(mExampleAdapter);

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

        mRequestQueue.add(request);
    }
}