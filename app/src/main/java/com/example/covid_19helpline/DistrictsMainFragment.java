package com.example.covid_19helpline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DistrictsMainFragment extends Fragment
{
    private RecyclerView recyclerView;
    List<State> states;
    Adapter adapter;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_districtsmain, container, false);

        recyclerView=view.findViewById(R.id.statesList);
        states = new ArrayList<>();
        extractStates();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
    private void extractStates() {
        String url = "https://api.covid19india.org/v2/state_district_wise.json";
        requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=1;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        State state=new State(jsonObject.getString("state"));
                        states.add(state);
                        adapter= new Adapter(getContext(),states);
                        recyclerView.setAdapter(adapter);


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
