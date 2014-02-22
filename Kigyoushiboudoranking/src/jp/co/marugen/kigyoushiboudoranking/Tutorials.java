package jp.co.marugen.kigyoushiboudoranking;

import static jp.co.marugen.kigyoushiboudoranking.Constants.nameOfPref;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Tutorials {

    private boolean cheker = false;
    private Editor edi;
    private SharedPreferences pref;

    protected void showTutorial(Context context, String keyName,
            String titleName) {
        // 仕方なくインナー変数とつくる
        final String keyname = keyName;
        ;
        // プリファレンスに書き込むためのEditorオブジェクト取得 //
        // プリファレンスの準備
        pref = context.getSharedPreferences(nameOfPref, Context.MODE_PRIVATE);
        edi = pref.edit();
        // /////////チュートリアルの表示/////////////
        if (pref.getBoolean(keyName, false) == false) {
            // チェック表示用のアラートダイアログ
            final CharSequence[] chkItems = { "毎回表示する" };
            final boolean[] chkSts = { true, false, false };
            AlertDialog.Builder checkDlg = new AlertDialog.Builder(context);
            checkDlg.setTitle(titleName);
            checkDlg.setMultiChoiceItems(chkItems, chkSts,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialog, int which,
                                boolean flag) {
                            // 項目選択時の処理
                            // i は、選択されたアイテムのインデックス
                            // flag は、チェック状態
                            // プリファレンスにtrueを入れる
                            if (flag == false)
                                cheker = true;
                            // "user_name" というキーで名前を登録
                            edi.putBoolean(keyname, cheker);
                            // 書き込みの確定（実際にファイルに書き込む）
                            edi.commit();
                        }
                    });

            // 表示
            checkDlg.create().show();
        } else {
            // 何もしない
        }
    }
}
