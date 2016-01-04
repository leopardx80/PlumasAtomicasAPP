/**
 * VideoAdapter
 * @author: Hever González
 * @copyright: Taller De Código
 */
package com.example.agl.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.agl.myapplication.VolleyJson.AppController;
import com.example.agl.myapplication.activity.VideoDetailActivity;
import com.example.agl.myapplication.model.VideoItem;
import com.example.agl.myapplication.R;
import com.example.agl.myapplication.util.LruBitmapCache;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItem> items;
    public String URL_IMAGE;

    ImageLoader mImageLoader;

    // Progress dialog

    private LayoutInflater inflater;
    private Context context;

    //-----------------------------------------
    // NavigationDrawerAdapter
    //-----------------------------------------

    public VideoAdapter(Context context, List<VideoItem> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;

    }

    //-----------------------------------------
    // delete
    //-----------------------------------------

    public void delete(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }
    //-----------------------------------------
    // VideoViewHolder
    //-----------------------------------------

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(view);
        return holder;
    }

    //-----------------------------------------
    // onBindViewHolder
    //-----------------------------------------
    @Override
    public void onBindViewHolder(VideoViewHolder holder, final int position) {

        VideoItem current = items.get(position);
        mImageLoader = AppController.getInstance().getImageLoader();
        mImageLoader = new ImageLoader(AppController.getInstance().getRequestQueue(), new LruBitmapCache());
        holder.title.setText(current.getTitle());
        holder.images.setImageUrl(current.getImage(), mImageLoader);

    }
    //-----------------------------------------
    // getItemCount
    //-----------------------------------------
    @Override public int getItemCount() { return items.size(); }


    /**
     * class: VideoViewHolder
     * */

    //-----------------------------------------
    // VideoViewHolder
    //-----------------------------------------

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        public String URL_IMAGE;
        public TextView title;
        public TextView extract;
        public NetworkImageView images;
        public ImageLoader mImageLoader;


            public VideoViewHolder(View v) {
                super(v);


                title = (TextView) v.findViewById(R.id.title);
                images = (NetworkImageView) v.findViewById(R.id.thumbnail);

                images.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        Intent intent = new Intent (v.getContext(), VideoDetailActivity.class);
                        intent.putExtra("Titulo",title.getText());
                        v.getContext().startActivity(intent);
                        System.out.println("Hello World");
                    }

                });
            }
    }
}

