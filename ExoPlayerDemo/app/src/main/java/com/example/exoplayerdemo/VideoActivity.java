package com.example.exoplayerdemo;

import android.net.Uri;
import android.os.Bundle;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

public class VideoActivity extends AppCompatActivity {

    private SimpleExoPlayer player;
    private PlayerView playerView;
    private int videoId = -1;
    private String[] videoUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    public void initView() {
        setContentView(R.layout.activity_video);
        playerView = findViewById(R.id.playerView);
    }

    public void initData() {
        videoUrls = new String[]{"file:///android_asset/example.mp4","file:///android_asset/example2.mp4"};
        try {
            videoId = getIntent().getIntExtra("videoId", -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(videoId != -1) {
            final Uri mp4VideoUri = Uri.parse(videoUrls[videoId]);
            DefaultTrackSelector trackSelector = new DefaultTrackSelector();
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            playerView.setPlayer(player);


            player.addListener(new Player.EventListener() {
                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    Log.d("MainActivity", "playWhenReady:" + playWhenReady + ", state:" + playbackState);
                }
            });
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "com.example.exoplayerdemo"));
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mp4VideoUri);
            player.prepare(videoSource);
            player.setPlayWhenReady(true);
        }
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.stop();
            player.release();
        }
        super.onDestroy();
    }
}
