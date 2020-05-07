package com.app.palpharmacy.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.app.palpharmacy.R;
import com.app.palpharmacy.adapters.fragmentadapter;
import com.app.palpharmacy.model.Promotion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class advertisment extends Fragment {
    String urlpromo = "https://www.palpharmacy.com/getPromotions";
    View v;
    fragmentadapter recycleradapter;
    private RecyclerView myrecyclervieww;
    private List<Promotion> lstpharm;
    private RequestQueue requestQueue2;
    private JsonArrayRequest request2;

    public advertisment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.advertisment, container, false);
        myrecyclervieww = v.findViewById(R.id.recyclerf);
        recycleradapter = new fragmentadapter(getContext(), lstpharm);
        myrecyclervieww.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstpharm = new ArrayList<>();
        jsonrequest();

    }

    private void jsonrequest() {

        request2 = new JsonArrayRequest(urlpromo, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Promotion pharma = new Promotion();
                        pharma.setEndtime(jsonObject.getString("end_date"));
                        pharma.setPharname(jsonObject.getString("pharmacy_name"));
                        pharma.setTitle(jsonObject.getString("title"));
                        pharma.setImage(jsonObject.getString("image_url"));
                        pharma.setDecs(jsonObject.getString("description"));

                        lstpharm.add(pharma);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue2 = Volley.newRequestQueue(getActivity());
        requestQueue2.add(request2);

    }

    private void setuprecyclerview() {
        recycleradapter = new fragmentadapter(getActivity(),lstpharm);

        myrecyclervieww.setAdapter(recycleradapter);
        recycleradapter.notifyDataSetChanged();


    }
}
