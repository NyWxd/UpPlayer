package com.ny.ijk.upplayer.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.ny.ijk.upplayer.R;
import com.ny.ijk.upplayer.media.IRenderView;
import com.ny.ijk.upplayer.media.IjkVideoView;
import com.ny.ijk.upplayer.media.PlayerManager;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private IjkVideoView mVideoView;
    private PlayerManager mPlayerManager;
    private String url_1 = "http://221.228.226.23/11/t/j/v/b/tjvbwspwhqdmgouolposcsfafpedmb/sh.yinyuetai.com/691201536EE4912BF7E4F1E2C67B8119.mp4";
    private String url_2 = "http://221.228.226.5/14/z/w/y/y/zwyyobhyqvmwslabxyoaixvyubmekc/sh.yinyuetai.com/4599015ED06F94848EBF877EAAE13886.mp4";
    private String url_3 = "http://221.228.226.5/15/t/s/h/v/tshvhsxwkbjlipfohhamjkraxuknsc/sh.yinyuetai.com/88DC015DB03C829C2126EEBBB5A887CB.mp4";
    private String url_4 = "/storage/emulated/0/DCIM/fvmobile/test_video.mp4";
    List<String> urls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这是普通的仅播放
//        initView();
        //这是带控制的播放
        initVideo();
    }

    //把使用播放器的Activity的onTouch事件传递给PlayerManager
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mPlayerManager.gestureDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 初始化PlayerManager
     * 可左半屏滑动控制亮度，右半屏控制音量，双击切换比例 （无提示）
     * */
    private void initVideo() {

        urls.add(url_1);
        urls.add(url_2);
        urls.add(url_3);
        urls.add(url_4);

        mPlayerManager = new PlayerManager(this);
        mPlayerManager.setFullScreenOnly(true);
        mPlayerManager.live(false);
        mPlayerManager.setScaleType(PlayerManager.SCALETYPE_WRAPCONTENT);
        mPlayerManager.playerInFullScreen(true);
//        mPlayerManager.play(url_2);
        mPlayerManager.play(urls,1);
        IjkVideoView videoView = mPlayerManager.getVideoView();
        videoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                switch (i){
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                        break;
                }
                return false;
            }
        });
    }

    private void initView() {
        mVideoView = findViewById(R.id.up_player_view);

        mVideoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        mVideoView.setVideoURI(Uri.parse(url_4));
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerManager.pause();
    }

    @Override
    protected void onResume() {
        mPlayerManager.onResume();
        super.onResume();
    }
}
