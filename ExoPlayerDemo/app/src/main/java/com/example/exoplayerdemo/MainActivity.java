package com.example.exoplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.UriMatcher;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.google.android.exoplayer2.upstream.DataSource.*;

//MainActivity.java
public class MainActivity extends AppCompatActivity {

    private ListView videoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    void initView() {
        setContentView(R.layout.activity_main);
        videoList = findViewById(R.id.videoList);
    }

    void initData() {
        String[] data = {"默认视频1", "默认视频2"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        videoList.setAdapter(adapter);
        videoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("videoId", position);
                intent.setClass(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
    }
}
