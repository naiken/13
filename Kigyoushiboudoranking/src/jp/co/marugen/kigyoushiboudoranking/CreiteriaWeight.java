package jp.co.marugen.kigyoushiboudoranking;

import static jp.co.marugen.kigyoushiboudoranking.Constants.table_name;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.columNumCriWei;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedCriName;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriWeiNum;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class CreiteriaWeight extends Activity implements OnClickListener {

    private DBAdapter helper;
    private DBMethods dbMe = new DBMethods();

    private TextView NameText;
    private RatingBar rb;
    private int value;

    private Button decided_btn;
    private Button return_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criteria_weight);

        // //////////その他の準備///////////
        NameText = (TextView) findViewById(R.id.criteria_weigh_name);
        NameText.setText(selectedCriName);
        decided_btn = (Button) findViewById(R.id.criteria_weight_decided_btn);
        return_btn = (Button) findViewById(R.id.criteria_weight_return_btn);
        decided_btn.setOnClickListener(this);
        return_btn.setOnClickListener(this);
        rb = (RatingBar) findViewById(R.id.criteria_rb);
        // 星の数を７に設定
        rb.setNumStars(5);
        // レートの変更を可能にする
        rb.setIsIndicator(false);
        // レートが加減される時のステップ幅を0.5に設定
        rb.setStepSize(1);

        // データベースの生成
        helper = new DBAdapter(this);

        try {
            Cursor c = dbMe.getDBData(this);
            showEvents(c);
        } finally {
            helper.close();
        }
        // バーに初期値を設定
        rb.setRating(value);
    }

    @Override
    public void onClick(View v) {

        if (v == decided_btn) {
            // データベースに重要度を登録
            try {
                addEvent((int) rb.getRating());
            } finally {
                helper.close();
            }
            Intent intent = new Intent(this, Criteria.class);
            startActivity(intent);
        }

        if (v == return_btn) {
            Intent intent2 = new Intent(this, Criteria.class);
            startActivity(intent2);
        }

    }

    private void showEvents(Cursor c) {
        while (c.moveToNext()) {
            int num = c.getInt(columNumCriWei);
            value = num;
            break;
        }
    }

    // ////////////データベースに書き込み//////////////
    private void addEvent(int num) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tableCriWeiNum, num);
        db.update(table_name, values, tableCriWeiNum + "=" + value, null);
    }
}
