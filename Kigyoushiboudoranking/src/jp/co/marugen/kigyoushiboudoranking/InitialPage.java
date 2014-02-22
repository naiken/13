package jp.co.marugen.kigyoushiboudoranking;

import static jp.co.marugen.kigyoushiboudoranking.Constants.flagTutoCom;
import static jp.co.marugen.kigyoushiboudoranking.Constants.flagTutoCri;
import static jp.co.marugen.kigyoushiboudoranking.Constants.flagTutoMain;
import static jp.co.marugen.kigyoushiboudoranking.Constants.nameOfPref;
import static jp.co.marugen.kigyoushiboudoranking.Constants.prePic;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class InitialPage extends Activity implements OnClickListener {

    private TextView textView;
    private Button decided_btn;
    private Button return_btn;
    private Intent intent;
    // 仕方なく作った
    private Context con;

    private Initial ini;
    private Editor edi;
    private SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initialpage);

        // //////////その他の準備///////////
        textView = (TextView) findViewById(R.id.initialpage_text);
        textView.setText("初期化しますか？");
        decided_btn = (Button) findViewById(R.id.initialpage_decided_btn);
        return_btn = (Button) findViewById(R.id.initialpage_return_btn);
        decided_btn.setOnClickListener(this);
        return_btn.setOnClickListener(this);
        pref = getSharedPreferences(nameOfPref, Context.MODE_PRIVATE);
        edi = pref.edit();
        ini = new Initial();
    }

    @Override
    public void onClick(View v) {
        con = this;
        intent = new Intent(this, MainActivity.class);

        if (v == decided_btn) {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("警告");
            alert.setMessage("本当に初期化しますか？");
            alert.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // ////////////Yesボタンが押された時の処理 //////////
                            ini.initialization(con);

                            edi.putBoolean(flagTutoMain, false);
                            edi.putBoolean(flagTutoCom, false);
                            edi.putBoolean(flagTutoCri, false);
                            edi.putString(prePic, null);
                            edi.commit();

                            startActivity(intent);
                        }
                    });

            alert.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Noボタンが押された時の処理
                            startActivity(intent);
                        }
                    });
            alert.show();
        } else {
            startActivity(intent);
        }
    }

}