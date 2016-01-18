package com.example.agl.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;



import com.example.agl.myapplication.R;

public class VideoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        Intent intent = getIntent(); // gets the previously created intent


        String PATH = "http://brightcove.vo.llnwd.net/e1/uds/pd/4649896697001/4649896697001_4653570864001_4653549503001.mp4";

        VideoView video_view = (VideoView)findViewById(R.id.video_view);
        video_view.setVideoURI(Uri.parse(PATH));
        video_view.setMediaController(new android.widget.MediaController(this));
        video_view.requestFocus();
        video_view.start();

        String Titulo = intent.getStringExtra("Titulo");
        TextView t = (TextView) findViewById(R.id.title);
        t.setText(Titulo);

    }

}
