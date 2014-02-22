package jp.co.marugen.marupeke;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity {

    private GestureLibrary gestureLibrary;
    private int number01;
    private int number02;
    private int number03;
    private int number04;
    private int number05;
    private TextView text;
    private int flg;
    private int okcount = 0;
    private CountDownTimer cdt;
    AnimationDrawable animDraw;
    AnimationDrawable rocketAnimation;
    private ScaleAnimation scale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gestureLibrary.load()) {
            finish();
        }

        new Random();
        text = (TextView) findViewById(R.id.textView1);

        startQuestion();

        GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        gestures.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay,
                    Gesture gesture) {
                ArrayList<Prediction> predictions = gestureLibrary
                        .recognize(gesture);
                // 「○」か「?」を入力したとき
                // gestureファイルの容量判定
                if (predictions.size() > 0) {
                    Prediction prediction = predictions.get(0);

                    // 入力が認識されたとき
                    if (prediction.score > 1.0) {

                        if (flg == 0) {
                            judgmentok(prediction);
                        } else {
                            judgmentmiss(prediction);
                        }
                    }
                }
            }
        });
    }

    // 正解問題
    public void Ok1() {
        int questionok = (int) (Math.random() * 2);
        number01 = (int) (Math.random() * 10);
        number02 = (int) (Math.random() * 10);
        flg = 0;
        // 足し算
        if (questionok == 0) {
            int question01 = number01 + number02;
            text.setText(number01 + "+" + number02 + "=" + question01);
            // 引き算
        } else if (questionok == 1) {
            int question02 = number01 - number02;
            text.setText(number01 + "-" + number02 + "=" + question02);

        }

    }
    public void Ok2() {
        int questionok = (int) (Math.random() * 3);
        number01 = (int) (Math.random() * 10);
        number02 = (int) (Math.random() * 10);
        flg = 0;
        // 足し算
        if (questionok == 0) {
            int question01 = number01 + number02;
            text.setText(number01 + "+" + number02 + "=" + question01);
            // 引き算
        } else if (questionok == 1) {
            int question02 = number01 - number02;
            text.setText(number01 + "-" + number02 + "=" + question02);
            //かけ算
        }else if (questionok == 2) {
            int question03 = number01 * number02;
            text.setText(number01 + "×" + number02 + "=" + question03);
        }
    }
    public void Ok3() {
        int questionok = (int) (Math.random() * 4);
        number01 = (int) (Math.random() * 10);
        number02 = (int) (Math.random() * 10);
        number03 = (int) (Math.random() * 9 + 1);
        number04 = (int) (Math.random() * 9 + 1);
        flg = 0;
        // 足し算
        if (questionok == 0) {
            int question01 = number01 + number02;
            text.setText(number01 + "+" + number02 + "=" + question01);
            // 引き算
        } else if (questionok == 1) {
            int question02 = number01 - number02;
            text.setText(number01 + "-" + number02 + "=" + question02);
            //かけ算
        }else if (questionok == 2) {
            int question03 = number01 * number02;
            text.setText(number01 + "×" + number02 + "=" + question03);
            //わり算
        }else if (questionok == 3){
            int question04 = number03 / number04;
            text.setText(number03 + "÷" + number04 + "=" + question04);
        }

    }

    // 不正解問題
    public void Miss1() {
        int questionmiss = (int) (Math.random() * 2);
        number01 = (int) (Math.random() * 10);
        number02 = (int) (Math.random() * 10);
        number03 = (int) (Math.random() * 10 + 1);
        flg = 1;
        // 足し算
        if (questionmiss == 0) {
            int question05 = number01 + number02 - number03;
            text.setText(number01 + "+" + number02 + "=" + question05);
            // 引き算
        } else if (questionmiss == 1) {
            int question06 = number01 - number02 + number03;
            text.setText(number01 + "-" + number02 + "=" + question06);
        }

    }
    public void Miss2() {
        int questionok = (int) (Math.random() * 3);
        number01 = (int) (Math.random() * 10);
        number02 = (int) (Math.random() * 10);
        number03 = (int) (Math.random() * 10 +1);
        flg = 1;
        // 足し算
        if (questionok == 0) {
            int question05 = number01 + number02 - number03;
            text.setText(number01 + "+" + number02 + "=" + question05);
            // 引き算
        } else if (questionok == 1) {
            int question06 = number01 - number02 + number03;
            text.setText(number01 + "-" + number02 + "=" + question06);
            //かけ算
        }else if (questionok == 2) {
            int question07 = number01 * number02 - number03;
            text.setText(number01 + "×" + number02 + "=" + question07);
        }
    }
    public void Miss3() {
        int questionok = (int) (Math.random() * 4);
        number01 = (int) (Math.random() * 10);
        number02 = (int) (Math.random() * 10);
        number03 = (int) (Math.random() * 10 + 1);
        number04 = (int) (Math.random() * 9 + 1);
        number05 = (int) (Math.random() * 9 + 1);
        flg = 1;
        // 足し算
        if (questionok == 0) {
            int question05 = number01 + number02 - number03;
            text.setText(number01 + "+" + number02 + "=" + question05);
            // 引き算
        } else if (questionok == 1) {
            int question06 = number01 - number02 + number03;
            text.setText(number01 + "-" + number02 + "=" + question06);
            //かけ算
        }else if (questionok == 2) {
            int question07 = number01 * number02 - number03;
            text.setText(number01 + "×" + number02 + "=" + question07);
            //わり算
        }else if (questionok == 3){
            int question08 = number04 / number05 - number03;
            text.setText(number04 + "÷" + number05 + "=" + question08);
        }
    }

    public void judgmentok(Prediction prediction) {
        if (prediction.name.equals("おｋ")) {
            okcount++;

            startQuestion();
        } else if (prediction.name.equals("バツ")) {
            resultPage();
        }
    }

    public void judgmentmiss(Prediction prediction) {
        if (prediction.name.equals("バツ")) {
            okcount++;
            startQuestion();
        } else if (prediction.name.equals("おｋ")) {
            resultPage();
        }
    }

    public void countdown() {

        if (null != cdt) {
            cdt.cancel();
        }
        cdt = new CountDownTimer(5000, 1000) {
            ImageView iv = (ImageView) findViewById(R.id.imageView2);

            @Override
            public void onTick(long millisUntilFinished) {
                // float toX = millisUntilFinished / 5000;
                if (millisUntilFinished / 1000 == 5) {
                    scale = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.0f);
                    // アニメーション終了時の表示状態を維持する
                    scale.setFillEnabled(true);
                    scale.setFillAfter(true);
                    iv.startAnimation(scale);
                } else if (millisUntilFinished / 1000 == 4) {
                    scale = new ScaleAnimation(1.0f, 0.8f, 1.0f, 1.0f);
                    // アニメーション終了時の表示状態を維持する
                    scale.setFillEnabled(true);
                    scale.setFillAfter(true);
                    iv.startAnimation(scale);
                } else if (millisUntilFinished / 1000 == 3) {
                    scale = new ScaleAnimation(0.8f, 0.6f, 1.0f, 1.0f);
                    // アニメーション終了時の表示状態を維持する
                    scale.setFillEnabled(true);
                    scale.setFillAfter(true);
                    iv.startAnimation(scale);
                } else if (millisUntilFinished / 1000 == 2) {
                    scale = new ScaleAnimation(0.6f, 0.4f, 1.0f, 1.0f);
                    // アニメーション終了時の表示状態を維持する
                    scale.setFillEnabled(true);
                    scale.setFillAfter(true);
                    iv.startAnimation(scale);
                } else if (millisUntilFinished / 1000 == 1) {
                    scale = new ScaleAnimation(0.4f, 0.2f, 1.0f, 1.0f);
                    // アニメーション終了時の表示状態を維持する
                    scale.setFillEnabled(true);
                    scale.setFillAfter(true);
                    iv.startAnimation(scale);
                }
            }

            @Override
            public void onFinish() {
                resultPage();
            }
        };
        cdt.start();
    }

    public void startQuestion() {
        int OkMiss = (int) (Math.random() * 2);

        if (OkMiss == 0) {

            // ランダムな値が偶数時、正解問題表示
            if(okcount >= 0 && okcount < 20){
            	Ok1();
            }else if(okcount >= 20 && okcount <= 30){
            	Ok2();
            }else if(okcount > 30){
            	Ok3();
            }
        } else if (OkMiss == 1) {

            // 奇数時、不正解問題表示
            if(okcount >= 0 && okcount < 20){
            	Miss1();
            }else if(okcount >= 20 && okcount <= 30){
            	Miss2();
            }else if(okcount > 30){
            	Miss3();
            }

        }
        countdown();
    }

    public void resultPage() {
        if (null != cdt) {
            cdt.cancel();
        }
        Intent intent = new Intent(GameActivity.this, EndActivity.class);
        intent.putExtra("score", okcount);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        if (null != cdt) {
            cdt.cancel();
        }
        super.onPause();
    }
}