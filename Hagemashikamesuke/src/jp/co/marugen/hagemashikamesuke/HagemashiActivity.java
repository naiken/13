package jp.co.marugen.hagemashikamesuke;

import java.util.ArrayList;
import java.util.Collections;

import jp.co.cayto.appc.sdk.android.AppC;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HagemashiActivity extends Activity {

    private ImageButton mottobtn;// もっとボタン
    private ImageButton endbtn;// 終了ボタン
    private ImageButton bgmbtn;// BGMボタン
    private BgmPlayer bgm;
    private ArrayList numberlist;
    private final int[] cheerscreenlist = { R.drawable.akiramemokanjin,
            R.drawable.bokudattejibunni, R.drawable.bokuhaboku,
            R.drawable.bokumotukareta, R.drawable.daijobu, R.drawable.dekiru,
            R.drawable.fight, R.drawable.ganbare, R.drawable.genkidasite,
            R.drawable.hattedemo, R.drawable.hitorijanai, R.drawable.ikiro,
            R.drawable.ikiruiyoku, R.drawable.ikirukoto, R.drawable.imakoko,
            R.drawable.irutoiukoto, R.drawable.kabe, R.drawable.keizoku,
            R.drawable.kimidatte, R.drawable.kimigaerabumichi,
            R.drawable.kimihaganbatteru, R.drawable.kuraberutaisho,
            R.drawable.makeruna, R.drawable.minnanayande, R.drawable.nakanai,
            R.drawable.nantokanaru, R.drawable.nozomanaikagiri,
            R.drawable.ouensiteruyo, R.drawable.senpaikouhai,
            R.drawable.sinpainai, R.drawable.sonochoshi, R.drawable.sonomama,
            R.drawable.sonotokihasonotoki, R.drawable.sukinakoto,
            R.drawable.sukiniikinayo, R.drawable.taihendattane,
            R.drawable.tamechadame, R.drawable.tomatteiruyounimiete,
            R.drawable.ugokasumono, R.drawable.umarete, R.drawable.utaou,
            R.drawable.yumeha };

    private int count;
    private ImageView imageView;
    private static final String PREF_KEY = "kamesukebgm";
    private AppC mAppC;
    boolean state;
    private LinearLayout background;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hagemashi);
        mContext = this;

        mottobtn = (ImageButton) findViewById(R.id.imageButton1);
        endbtn = (ImageButton) findViewById(R.id.imageButton2);
        bgmbtn = (ImageButton) findViewById(R.id.imageButton3);
        imageView = (ImageView) findViewById(R.id.imageView1);
        background = (LinearLayout) findViewById(R.id.Background);

        // appCをインスタンス化
        mAppC = new AppC(this);
        // プレイヤーの初期化
        this.bgm = new BgmPlayer(this);

        // 時間の取得
        Time time = new Time("Asia/Tokyo");
        time.setToNow();
        int data = time.hour;
        // 背景の設定
        if (8 <= data && data <= 15) {
            // 昼
            background.setBackgroundResource(R.drawable.background);
        } else if (5 <= data && data <= 7) {
            // 明け方
            background.setBackgroundResource(R.drawable.backgroundsunrise);
        } else if (16 <= data && data <= 18) {
            // 夕方
            background.setBackgroundResource(R.drawable.backgroundsunset);
        }

        // SharedPrefernces の取得
        pref = getSharedPreferences(PREF_KEY, Activity.MODE_PRIVATE);
        // Editor の設定
        editor = pref.edit();

        // 1 ～ 10 の数値が入ったリストを作成
        numberlist = new ArrayList();
        for (int i = 0; i < cheerscreenlist.length; i++) {
            numberlist.add(i);
        }
        // リストの内容をシャッフルします。（１回目）
        Collections.shuffle(numberlist);

        imageView.setImageResource(cheerscreenlist[(Integer) numberlist
                .get(count)]);

        // もっとボタンクリック
        mottobtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                imageView.setImageResource(cheerscreenlist[(Integer) numberlist
                        .get(count)]);
                if (count == cheerscreenlist.length - 1) {
                    count = 0;
                    // リストの内容をシャッフルします。（１回目）
                    Collections.shuffle(numberlist);
                }
            }
        });

        // 終了ボタンクリック
        endbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 次画面のアクティビティ終了
                Intent intent = new Intent(HagemashiActivity.this,
                        EndActivity.class);
                startActivity(intent);
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

    // ///////////画面が変わったら，音楽を止める///////////
    @Override
    protected void onPause() {
        super.onPause();
        // BGMの停止
        bgm.stop(mContext);
    }
}
