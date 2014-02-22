package jp.co.marugen.kigyoushiboudoranking;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.kigyoushiboudoranking.Constants.commpany_name;
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
import static jp.co.marugen.kigyoushiboudoranking.Constants.total_num;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.newIDOfNewCom;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.registeredComNum;
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

public class NewCommpany extends Activity implements OnClickListener {

    private DBAdapter helper = new DBAdapter(this);
    private DBMethods dbMe = new DBMethods();

    private EditText editText;
    private String inputName;

    private int num0;
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    private int num6;
    private int num7;
    private int num8;
    private int num9;
    private int number0;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5;
    private int number6;
    private int number7;
    private int number8;
    private int number9;
    private String criteria0;
    private String criteria1;
    private String criteria2;
    private String criteria3;
    private String criteria4;
    private String criteria5;
    private String criteria6;
    private String criteria7;
    private String criteria8;
    private String criteria9;
    private int cri_weight0;
    private int cri_weight1;
    private int cri_weight2;
    private int cri_weight3;
    private int cri_weight4;
    private int cri_weight5;
    private int cri_weight6;
    private int cri_weight7;
    private int cri_weight8;
    private int cri_weight9;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_commpany);
        // //////////その他の準備///////////
        editText = (EditText) findViewById(R.id.newCommpany_edit);
        Button decided_btn = (Button) findViewById(R.id.newCommpany_decided_btn);
        Button return_btn = (Button) findViewById(R.id.newCommpany_return_btn);
        decided_btn.setOnClickListener(this);
        return_btn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.newCommpany_decided_btn:
            // エディットテキストのテキストを取得します
            inputName = editText.getText().toString();
            // データベースに企業の情報を登録
            try {
                Cursor c = dbMe.getDBData(this);
                showEvents(c);
                addEvent(inputName);
            } finally {
                helper.close();
            }
            Intent a = new Intent(this, MainActivity.class);
            startActivity(a);
            break;
        case R.id.newCommpany_return_btn:
            // MainActivityに戻る
            Intent b = new Intent(this, MainActivity.class);
            startActivity(b);
            break;
        }
    }

    private void showEvents(Cursor c) {
        while (c.moveToNext()) {

            cri_weight0 = c.getInt(23);
            cri_weight1 = c.getInt(24);
            cri_weight2 = c.getInt(25);
            cri_weight3 = c.getInt(26);
            cri_weight4 = c.getInt(27);
            cri_weight5 = c.getInt(28);
            cri_weight6 = c.getInt(29);
            cri_weight7 = c.getInt(30);
            cri_weight8 = c.getInt(31);
            cri_weight9 = c.getInt(32);

            criteria0 = c.getString(3);
            if (criteria0.equals("kara")) {
                num0 = -1;
                number0 = 0;
            } else {
                num0 = registeredComNum;
                number0 = registeredComNum;
            }
            criteria1 = c.getString(5);
            if (criteria1.equals("kara")) {
                num1 = -1;
                number1 = 0;
            } else {
                num1 = registeredComNum;
                number1 = registeredComNum;
            }
            criteria2 = c.getString(7);
            if (criteria2.equals("kara")) {
                num2 = -1;
                number2 = 0;
            } else {
                num2 = registeredComNum;
                number2 = registeredComNum;
            }
            criteria3 = c.getString(9);
            if (criteria3.equals("kara")) {
                num3 = -1;
                number3 = 0;
            } else {
                num3 = registeredComNum;
                number3 = registeredComNum;
            }
            criteria4 = c.getString(11);
            if (criteria4.equals("kara")) {
                num4 = -1;
                number4 = 0;
            } else {
                num4 = registeredComNum;
                number4 = registeredComNum;
            }
            criteria5 = c.getString(13);
            if (criteria5.equals("kara")) {
                num5 = -1;
                number5 = 0;
            } else {
                num5 = registeredComNum;
                number5 = registeredComNum;
            }
            criteria6 = c.getString(15);
            if (criteria6.equals("kara")) {
                num6 = -1;
                number6 = 0;
            } else {
                num6 = registeredComNum;
                number6 = registeredComNum;
            }
            criteria7 = c.getString(17);
            if (criteria7.equals("kara")) {
                num7 = -1;
                number7 = 0;
            } else {
                num7 = registeredComNum;
                number7 = registeredComNum;
            }
            criteria8 = c.getString(19);
            if (criteria8.equals("kara")) {
                num8 = -1;
                number8 = 0;
            } else {
                num8 = registeredComNum;
                number8 = registeredComNum;
            }
            criteria9 = c.getString(21);
            if (criteria9.equals("kara")) {
                num9 = -1;
                number9 = 0;
            } else {
                num9 = registeredComNum;
                number9 = registeredComNum;
            }
            // データベースから１行だけデータを取り出すためここで止める
            break;
        }
    }

    // ////////////データベースに書き込み//////////////
    private void addEvent(String string) {
        helper = new DBAdapter(this);

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(table_name, _ID + "=" + newIDOfNewCom, null);
        ContentValues values = new ContentValues();
        values.put(_ID, newIDOfNewCom);
        values.put(commpany_name, inputName);
        values.put(total_num, number0 * cri_weight0 + number1 * cri_weight1
                + number2 * cri_weight2 + number3 * cri_weight3 + number4
                * cri_weight4 + number5 * cri_weight5 + number6 * cri_weight6
                + number7 * cri_weight7 + number8 * cri_weight8 + number9
                * cri_weight9);
        values.put(criteria_name0, criteria0);
        values.put(criteria_num0, num0);
        values.put(criteria_name1, criteria1);
        values.put(criteria_num1, num1);
        values.put(criteria_name2, criteria2);
        values.put(criteria_num2, num2);
        values.put(criteria_name3, criteria3);
        values.put(criteria_num3, num3);
        values.put(criteria_name4, criteria4);
        values.put(criteria_num4, num4);
        values.put(criteria_name5, criteria5);
        values.put(criteria_num5, num5);
        values.put(criteria_name6, criteria6);
        values.put(criteria_num6, num6);
        values.put(criteria_name7, criteria7);
        values.put(criteria_num7, num7);
        values.put(criteria_name8, criteria8);
        values.put(criteria_num8, num8);
        values.put(criteria_name9, criteria9);
        values.put(criteria_num9, num9);
        values.put(criteria_weight0, cri_weight0);
        values.put(criteria_weight1, cri_weight1);
        values.put(criteria_weight2, cri_weight2);
        values.put(criteria_weight3, cri_weight3);
        values.put(criteria_weight4, cri_weight4);
        values.put(criteria_weight5, cri_weight5);
        values.put(criteria_weight6, cri_weight6);
        values.put(criteria_weight7, cri_weight7);
        values.put(criteria_weight8, cri_weight8);
        values.put(criteria_weight9, cri_weight9);
        db.insertOrThrow(table_name, null, values);
    }
}