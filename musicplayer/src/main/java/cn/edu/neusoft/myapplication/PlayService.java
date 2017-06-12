package cn.edu.neusoft.myapplication;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by 光荣与梦想 on 2017/6/2.
 */

public class PlayService extends Service{
    private MediaPlayer mediaPlayer;
    public static int current;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        current=intent.getIntExtra("pos",0);
        play(current);
        //当一首歌播放完毕以后激发的事件
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play(++current%MyApplication.musicList.size());//这里做模运算是为了播放完一轮以后再从头开始
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }
    public void play(int pos){
        Music music=MyApplication.musicList.get(pos);
        //设置通知栏
        Notification.Builder builder=new Notification.Builder(PlayService.this);
        builder.setSmallIcon(R.mipmap.ic_launcher).//设置小图标 即未下拉之前通知栏的图标
                setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)).//设置大图标 即下拉之后通知栏的图标
                setContentTitle("播放").setContentText("正在播放" + music.getName());//通知的标题和内容

        Notification notification =  builder.build();
        startForeground(1,notification);

        mediaPlayer.reset();//当循环播放的时候重置mediaPlayer或者点击另一首歌的时候要重置mediaPlayer

        try {
            mediaPlayer.setDataSource(music.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (IllegalStateException ex){
            ex.printStackTrace();
        }
    }
}
