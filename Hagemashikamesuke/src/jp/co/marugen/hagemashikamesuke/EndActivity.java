package jp.co.marugen.hagemashikamesuke;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class EndActivity extends Activity {

    private ImageButton homebtn;// ホームボタン
    private ImageButton bgmbtn;// BGMボタン
    private BgmPlayer bgm;
    private static final String PREF_KEY = "kamesukebgm";
    boolean state;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        mContext = this;

        // レイアウト画面の部品の指定
        homebtn = (ImageButton) findViewById(R.id.imageButton1);
        bgmbtn = (ImageButton) findViewById(R.id.imageButton3);
        // プレイヤーの初期化
        this.bgm = new BgmPlayer(this);

        // SharedPrefernces の取得
        pref = getSharedPreferences(PREF_KEY, Activity.MODE_PRIVATE);
        state = pref.getBoolean(PREF_KEY, false);
        // Editor の設定
        editor = pref.edit();

        // ホームボタンクリック
        homebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 終了画面のアクティビティ終了
                finish();
            }
        });
        // 音符ボタンクリック
        bgmbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state = pref.getBoolean(PREF_KEY, false);
                if (state) {
                    // BGMの停止
                    bgm.stop(mContext);
                    // Editor に値を代入
                    editor.putBoolean(PREF_KEY, false);
                    // データの保存
                    editor.commit();
                    bgmbtn.setImageResource(R.drawable.bgmbtn_on);
                } else {// BGMの再生
                    bgm.start(mContext);
                    // Editor に値を代入
                    editor.putBoolean(PREF_KEY, true);
                    // データの保存
                    editor.commit();
                    bgmbtn.setImageResource(R.drawable.bgmbtn_off);
                }
            }
        });
    }

    // //////////プレファレンスの中身がtrueなら音楽を再生/////////////
    @Override
    protected void onResume() {
        super.onResume();
        state = pref.getBoolean(PREF_KEY, false);
        if (state) {
            // BGMの再生
            bgm.start(mContext);
            // OFFボタンの設置
            bgmbtn.setImageResource(R.drawable.bgmbtn_off);
        } else {
            // ONボタンの設置
            bgmbtn.setImageResource(R.drawable.bgmbtn_on);
        }
    }

    // ///////////画面が遷移したら，音楽を止める///////////
    @Override
    protected void onPause() {
        super.onPause();
        // BGMの停止
        bgm.stop(mContext);
        // Editor に値を代入
        editor.putBoolean(PREF_KEY, false);
        editor.commit();
    }
}
