package com.example.agl.myapplication.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.agl.myapplication.R;

public class VideoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent(); // gets the previously created intent


        String PATH = "http://brightcove.vo.llnwd.net/e1/uds/pd/4649896697001/4649896697001_4653570864001_4653549503001.mp4";

        VideoView video_view = (VideoView)findViewById(R.id.video_view);
        video_view.setVideoURI(Uri.parse(PATH));
        video_view.setMediaController(new android.widget.MediaController(this));
        video_view.requestFocus();
        video_view.start();

        String Titulo = intent.getStringExtra("Titulo");
        TextView t = (TextView) findViewById(R.id.titulo);
        t.setText(Titulo);

    }

}
