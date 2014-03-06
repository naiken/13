package jp.co.marugen.resizeimageformail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AnimationListener {

    private double scale = 1.0;
    private static final int REQUEST_GALLERY = 0;
    private ImageView imgView;
    private SeekBar varS;
    private CheckBox cheM;
    private Bitmap resized;
    private boolean imgSeted = false; // イメージがセットされたらアニメーションする
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("リサイズ設定");

        varS = (SeekBar) findViewById(R.id.barScale);
        final TextView texS = (TextView) findViewById(R.id.textScale);
        cheM = (CheckBox) findViewById(R.id.checkMail);
        final Button btnD = (Button) findViewById(R.id.btnDecide);
        imgView = (ImageView) findViewById(R.id.img);
        anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.custom);

        // ///////////////////////シークバー//////////////////////////////
        varS.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // ツマミをドラッグしたときに呼ばれる
                scale = (double) varS.getProgress() / 10;
                texS.setText(scale + "倍");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // ツマミに触れたときに呼ばれる
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // ツマミを離したときに呼ばれる
            }
        });
        // ///////////////////////チェックボックス/////////////////////
        cheM.setChecked(false);
        // リスナーに登録、クリックされた時の動作を実装
        cheM.setOnClickListener(new View.OnClickListener() {
            @Override
            // チェックされた時に呼び出されます
            public void onClick(View v) {
            }
        });

        // /////////////////////決定ボタン//////////////////////
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ギャラリー呼び出し
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Gmailだと２回呼ばれる
        // Gmailから最初に戻ったときには画像を表示しない。
        if (cheM.isChecked()) {
            // チェックボックスの初期化
            cheM.setChecked(false);
        } else if (imgSeted) {
            // サイズ変更せずにエリアの中央に表示
            imgView.setScaleType(ImageView.ScaleType.CENTER);
            // 選択した画像を表示
            imgView.setImageBitmap(resized);
            // フェードインアニメーション
            imgView.startAnimation(anim);
            anim.setAnimationListener(this);
            imgSeted = false;
            // シークバーの初期化
            varS.setProgress(10);

        }
    }

    // //////////////////////////////ギャラリーから戻ったら/////////////////////////////
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {

            InputStream in = null;
            try {
                in = getContentResolver().openInputStream(data.getData());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Bitmap img = BitmapFactory.decodeStream(in);
            resized = Bitmap.createScaledBitmap(img,
                    (int) (img.getWidth() * scale),
                    (int) (img.getHeight() * scale), true);
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // フラグ
            imgSeted = true;

            String strPath = MediaStore.Images.Media.insertImage(
                    getContentResolver(), resized, "Resized_Picture",
                    "Resized_Picture");

            if (cheM.isChecked()) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "", null));
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(strPath));
                // intent.putExtra("imageData", resized);
                startActivity(intent);
            }
        }
    }

    // ////////////////アニメーション////////////
    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(getApplicationContext(), "画像をギャラリーに保存しました。",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}
