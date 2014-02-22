package jp.co.marugen.hagemashikamesuke;

import java.io.IOException;

import android.content.Context;
import android.media.MediaPlayer;

public class BgmPlayer {
    private MediaPlayer mediaPlayer;

    public BgmPlayer(Context context) {
        // BGMファイルを読み込む
        mediaPlayer = MediaPlayer.create(context, R.raw.bgm);
        if (mediaPlayer != null) {
            // ループ再生
            mediaPlayer.setLooping(true);
            // 音量設定
            mediaPlayer.setVolume(1.0f, 1.0f);
        } else {
            mediaPlayer = MediaPlayer.create(context, R.raw.bgm);
        }
    }

    /**
     * BGMを再生する
     */
    public void start(Context context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.bgm);
        }
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    /**
     * BGMを停止する
     */
    public void stop(Context context) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, R.raw.bgm);
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            } catch (IllegalStateException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            } catch (IOException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
    }

}