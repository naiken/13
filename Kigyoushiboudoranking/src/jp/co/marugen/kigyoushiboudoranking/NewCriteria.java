package jp.co.marugen.kigyoushiboudoranking;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name0;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name1;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name2;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name3;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name4;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name5;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name6;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name7;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name8;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_name9;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num0;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num1;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num2;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num3;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num4;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num5;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num6;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num7;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num8;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_num9;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight0;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight1;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight2;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight3;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight4;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight5;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight6;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight7;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight8;
import static jp.co.marugen.kigyoushiboudoranking.Constants.criteria_weight9;
import static jp.co.marugen.kigyoushiboudoranking.Constants.table_name;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewCriteria extends Activity implements OnClickListener {

    private DBAdapter helper;
    private DBMethods dbMe = new DBMethods();
    private Dialogs dialog = new Dialogs();

    private EditText editText;
    private String inputName;
    private int counter;

    private Button decided_btn;
    private Button return_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_criteria);

        // //////////その他の準備///////////
        editText = (EditText) findViewById(R.id.newCriteria_edit);
        decided_btn = (Button) findViewById(R.id.newCriteria_decided_btn);
        return_btn = (Button) findViewById(R.id.newCriteria_return_btn);
        decided_btn.setOnClickListener(this);
        return_btn.setOnClickListener(this);
        // データベースの生成
        helper = new DBAdapter(this);
    }

    @Override
    public void onClick(View v) {
        if (v == decided_btn) {
            // エディットテキストのテキストを取得します
            inputName = editText.getText().toString();
            // データベースに企業の情報を登録
            try {
                Cursor c = dbMe.getTotalRank(this);
                showEvents(c);
            } finally {
                helper.close();
            }

            if (counter >= 5) {
                dialog.showDialog(this, "これ以上登録できません");
                counter = 0;
            } else {
                Intent intent = new Intent(this, Commpany.class);
                startActivity(intent);
            }
        } else {
            Intent intent2 = new Intent(this, Commpany.class);
            startActivity(intent2);
        }

    }

    private void showEvents(Cursor c) {
        // if文の条件分岐を一回だけ実行させる．
        boolean checker = false;
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        int counter2 = 0;
        while (c.moveToNext()) {

            int id = c.getInt(0);
            int num0 = c.getInt(4);
            int num1 = c.getInt(6);
            int num2 = c.getInt(8);
            int num3 = c.getInt(10);
            int num4 = c.getInt(12);
            int num5 = c.getInt(14);
            int num6 = c.getInt(16);
            int num7 = c.getInt(18);
            int num8 = c.getInt(20);
            int num9 = c.getInt(22);
            // 空の判断基準を探す
            if (num0 == -1 && checker == false) {
                counter = 1;
                checker = true;
            } else if (num1 == -1 && checker == false) {
                counter = 2;
                checker = true;
            } else if (num2 == -1 && checker == false) {
                counter = 3;
                checker = true;
            } else if (num3 == -1 && checker == false) {
                counter = 4;
                checker = true;
            } else if (num4 == -1 && checker == false) {
                counter = 5;
                checker = true;
            } else if (num5 == -1 && checker == false) {
                counter = 6;
                checker = true;
                break;
            } else if (num6 == -1 && checker == false) {
                counter = 7;
                checker = true;
            } else if (num7 == -1 && checker == false) {
                counter = 8;
                checker = true;
            } else if (num8 == -1 && checker == false) {
                counter = 9;
                checker = true;
            } else if (num9 == -1 && checker == false) {
                counter = 10;
                checker = true;
            } else if (num1 != -1 & num2 != -1 && num3 != -1 && num4 != -1
                    && num0 != -1 && num5 != -1 && num6 != -1 && num7 != -1
                    && num8 != -1 && num9 != -1) {
                counter = -1;
                break;
            }

            if (counter == 1) {
                values.put(criteria_name0, inputName);
                values.put(criteria_num0, counter2);
                values.put(criteria_weight0, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 2) {
                values.put(criteria_name1, inputName);
                values.put(criteria_num1, counter2);
                values.put(criteria_weight1, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 3) {
                values.put(criteria_name2, inputName);
                values.put(criteria_num2, counter2);
                values.put(criteria_weight2, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 4) {
                values.put(criteria_name3, inputName);
                values.put(criteria_num3, counter2);
                values.put(criteria_weight3, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 5) {
                values.put(criteria_name4, inputName);
                values.put(criteria_num4, counter2);
                values.put(criteria_weight4, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 6) {
                values.put(criteria_name5, inputName);
                values.put(criteria_num5, counter2);
                values.put(criteria_weight5, 3);
                db.update(table_name, values, _ID + "=" + id, null);
                break;
            } else if (counter == 7) {
                values.put(criteria_name6, inputName);
                values.put(criteria_num6, counter2);
                values.put(criteria_weight6, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 8) {
                values.put(criteria_name7, inputName);
                values.put(criteria_num7, counter2);
                values.put(criteria_weight7, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 9) {
                values.put(criteria_name8, inputName);
                values.put(criteria_num8, counter2);
                values.put(criteria_weight8, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            } else if (counter == 10) {
                values.put(criteria_name9, inputName);
                values.put(criteria_num9, counter2);
                values.put(criteria_weight9, 3);
                db.update(table_name, values, _ID + "=" + id, null);
            }
            counter2++;
        }
    }
}