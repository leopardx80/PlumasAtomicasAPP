package com.example.agl.myapplication.util;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import java.net.ContentHandler;

/**
 * Created by agl on 12/17/15.
 */
public class PollService extends IntentService {

    private static final String TAG = "Pool Service";


    public static Intent newIntent(Context context){

        return newIntent(context);

    }

    public PollService(){
        super(TAG);
    }

     @Override
     protected void onHandleIntent(Intent intent){

         if(!isNetworkAvailableAndConnected()){
             return;
         }

         Log.i(TAG,"Received an Internet" + intent);
     }


    private boolean isNetworkAvailableAndConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

}
