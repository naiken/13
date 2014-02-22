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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "syukatu_sample4_ver1";
    private static final int DATABASE_VERSION = 2;

    /** Create a helper object for the Events database */
    public DBAdapter(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + " (" + _ID + " INTEGER, "
                + commpany_name + " text not null, " + total_num + " integer, "
                + criteria_name0 + " text not null, " + criteria_num0
                + " integer, " + criteria_name1 + " text not null, "
                + criteria_num1 + " integer, " + criteria_name2
                + " text not null, " + criteria_num2 + " integer, "
                + criteria_name3 + " text not null, " + criteria_num3
                + " integer, " + criteria_name4 + " text not null, "
                + criteria_num4 + " integer, " + criteria_name5
                + " text not null, " + criteria_num5 + " integer, "
                + criteria_name6 + " text not null, " + criteria_num6
                + " integer, " + criteria_name7 + " text not null, "
                + criteria_num7 + " integer, " + criteria_name8
                + " text not null, " + criteria_num8 + " integer, "
                + criteria_name9 + " text not null, " + criteria_num9
                + " integer, " + criteria_weight0 + " integer, "
                + criteria_weight1 + " integer, " + criteria_weight2
                + " integer, " + criteria_weight3 + " integer, "
                + criteria_weight4 + " integer, " + criteria_weight5
                + " integer, " + criteria_weight6 + " integer, "
                + criteria_weight7 + " integer, " + criteria_weight8
                + " integer, " + criteria_weight9 + " integer);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
}