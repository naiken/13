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
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBMethods {

    private DBAdapter helper;
    private Cursor mCursor;
    private static String[] from = { _ID, commpany_name, total_num,
            criteria_name0, criteria_num0, criteria_name1, criteria_num1,
            criteria_name2, criteria_num2, criteria_name3, criteria_num3,
            criteria_name4, criteria_num4, criteria_name5, criteria_num5,
            criteria_name6, criteria_num6, criteria_name7, criteria_num7,
            criteria_name8, criteria_num8, criteria_name9, criteria_num9,
            criteria_weight0, criteria_weight1, criteria_weight2,
            criteria_weight3, criteria_weight4, criteria_weight5,
            criteria_weight6, criteria_weight7, criteria_weight8,
            criteria_weight9 };

    // /////////////////企業情報のページ///////////////////
    protected Cursor getCri(Context context) {

        helper = new DBAdapter(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String SQL_select = "SELECT * FROM " + table_name + " ORDER BY "
                + criteria_num0 + " ASC";
        mCursor = db.rawQuery(SQL_select, null);
        return mCursor;
    }

    // //////////////トップページ////////////////
    protected Cursor getTotalRank(Context context) {

        helper = new DBAdapter(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String SQL_select = "SELECT * FROM " + table_name + " ORDER BY "
                + total_num + " ASC";
        mCursor = db.rawQuery(SQL_select, null);
        return mCursor;
    }

    // //////////////データベースの読み込み////////////////
    protected Cursor getDBData(Context context) {

        helper = new DBAdapter(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query(table_name, from, null, null, null, null, null);
        return c;
    }

}
