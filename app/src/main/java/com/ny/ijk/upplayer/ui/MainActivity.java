package com.ny.ijk.upplayer.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ny.ijk.upplayer.R;
import com.ny.ijk.upplayer.media.IRenderView;
import com.ny.ijk.upplayer.media.IjkVideoView;
import com.ny.ijk.upplayer.media.PlayerManager;

public class MainActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    private PlayerManager mPlayerManager;
    private String url = "http://stream1.grtn.cn/tvs2/sd/live.m3u8?_ts&time=1518428696629";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mVideoView = findViewById(R.id.up_player_view);

        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.start();
    }
}
