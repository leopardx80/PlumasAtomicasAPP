package com.example.agl.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import com.brightcove.player.media.DeliveryType;
import com.brightcove.player.model.Video;
import com.brightcove.player.view.BrightcoveVideoView;

import com.example.agl.myapplication.R;
import com.example.agl.myapplication.VolleyJson.AppController;
import com.example.agl.myapplication.adapter.VideoAdapter;
import com.example.agl.myapplication.model.VideoItem;
import com.example.agl.myapplication.util.PollService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private View containerView;
    private VideoAdapter adapter;
    public List items = new ArrayList();

    private static String TAG = MainActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;

    // CONFIG

    private String API_KEY          = "DMWg6QzBwSmq935dDvdzNrtKNH6MYfGZS7gZVvxNFY9JpnCVMReqVfnmFh5";
    private String API_SECRET_KEY   = "XVjgdJtf7eyQkCF4dzgMFNYVmLQWQxUdUpZVqFxt7pK8bHBsLwzzvfdNfug";
    private String URL_JSON         = "http://internos.lisa.com.mx/pa/json/pa.json?offset=";

    private int OFF_SET = 0;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Cargando...");
        pDialog.setTitle("Plumas Atómicas");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(this);


        // Begin load json ;D

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                    fetchVideo();
                }
            }
        );

        recyclerView = (RecyclerView) rootView.findViewById(R.id.videoList);

        adapter = new VideoAdapter(getActivity(), items);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    /**
     * fetchVideo
     * Function get json volley method
     * */

    private void fetchVideo() {

        showpDialog();

        //---------------------------------------------------------
        //
        // JsonObjectRequest
        //
        //---------------------------------------------------------

        URL_JSON = URL_JSON + OFF_SET + API_SECRET_KEY;


       // if (conn.isNetworkAvailableAndConnected()){

            JsonArrayRequest jsonObjReq = new JsonArrayRequest(URL_JSON,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            if (response.length() > 0) {
                                try {

                                    for (int i = 0; i < response.length(); i++) {

                                        JSONObject video = (JSONObject) response.get(i);

                                        // int idVideo = video.getInt("Id");

                                        String titulo = video.getString("title");
                                        String imagen = video.getString("image");
                                        String extract = "";
                                        int views = 1;
                                        String url_video = "";

                                        items.add(new VideoItem(titulo, extract, imagen, url_video, views));

                                        // updating offset value to highest value
                                        //  if (idVideo >= OFF_SET)
                                        //    OFF_SET = idVideo;
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                adapter.notifyDataSetChanged();
                            }
                            Toast.makeText(getContext(), "Carga completa!", Toast.LENGTH_SHORT).show();

                            // stopping swipe refresh
                            swipeRefreshLayout.setRefreshing(false);

                            hidepDialog();
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    System.out.println(error.getMessage());
                    // hide the progress dialog
                    hidepDialog();

                    Toast.makeText(getContext(), "Error en la carga!", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq);

      /*  }else{
            hidepDialog();
            Toast.makeText(getContext(), "Sin conexión", Toast.LENGTH_SHORT).show();
        }*/

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    /**
     * onRefresh
     * */
    @Override
    public void onRefresh() {
      fetchVideo();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
