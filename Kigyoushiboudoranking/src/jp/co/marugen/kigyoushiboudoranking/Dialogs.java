package jp.co.marugen.kigyoushiboudoranking;

import android.app.AlertDialog;
import android.content.Context;

public class Dialogs {

    protected void showDialog(Context context, String str) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // ダイアログの設定
        alertDialog.setTitle(str); // タイトル

        // ダイアログの作成と表示
        alertDialog.create();
        alertDialog.show();
    }

}
