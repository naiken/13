package jp.co.marugen.kigyoushiboudoranking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.format.Time;
import android.widget.ImageView;

public class BackGround {

    private ImageView mBgImage;

    // 写真を選択するREQUEST_CODE
    private static final int REQUEST_CODE_GALLERY = 1;

    // //////////////デフォルトの背景画像セット//////////////////
    protected void setDefault() {
        // 分の取得
        Time time = new Time("Asia/Tokyo");
        int data = time.hour;

        if (data == 0 || data == 15 || data == 14) {

        } else {

        }

    }

    protected void startGallery() {
        // ギャラリーを呼び出す
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    private void startActivityForResult(Intent intent, int requestCodeGallery) {
        // TODO Auto-generated method stub

    }

    // //////////ギャラリーからの写真をセット①////////////////
    protected void setBgImage(Uri uri) {
        // 正しく回転されたBitmapを取得
        Bitmap bitmap = BitmapUtils.decodeUri(this, uri, 800);
        // 背景用のImageViewにBitmapをセット
        mBgImage.setImageBitmap(bitmap);
    }

}
