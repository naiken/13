package jp.co.marugen.kigyoushiboudoranking;

import static android.provider.BaseColumns._ID;
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
import static jp.co.marugen.kigyoushiboudoranking.Constants.flagTutoCom;
import static jp.co.marugen.kigyoushiboudoranking.Constants.nameOfPref;
import static jp.co.marugen.kigyoushiboudoranking.Constants.prePic;
import static jp.co.marugen.kigyoushiboudoranking.Constants.table_name;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.columNumCri;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.columNumCriWei;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedComID;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedComName;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedCriName;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriName;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriNum;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriWeiNum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//現在時刻の取得

@SuppressLint({ "NewApi", "InlinedApi" })
public class Commpany extends ListActivity implements OnItemClickListener {
    // ////////フィールド///////////////////////////
    private DBMethods dbMe = new DBMethods();
    private Tutorials tutorial = new Tutorials();
    private DBAdapter helper = new DBAdapter(this);;
    private Cursor mCursor;

    List<Map<String, String>> retDataList = new ArrayList<Map<String, String>>();
    Map<String, String> data = new HashMap<String, String>();
    private List<Integer> position_counter = new ArrayList<Integer>();
    private List<String> criteria_name = new ArrayList<String>();
    private List<Integer> criteria_col_num = new ArrayList<Integer>();
    private List<String> num = new ArrayList<String>();
    private final int[] backGroundSorceList = { R.drawable.bg0, R.drawable.bg1,
            R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
            R.drawable.bg6, R.drawable.bg7, R.drawable.bg8, R.drawable.bg9,
            R.drawable.bg10, R.drawable.bg11, R.drawable.bg12, R.drawable.bg13,
            R.drawable.bg14, R.drawable.bg15, R.drawable.bg16, R.drawable.bg17,
            R.drawable.bg18, R.drawable.bg19 };
    // Randomクラスのインスタンス化
    Random randam = new Random();

    private ListView listView;
    // private TextView tv_text;
    private AlertDialog alertDialog;
    private Intent intent_delete;

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
    private int counter = 0;

    // 背景用のImageView
    private ImageView mBgImage;
    // 写真のUri
    private Uri mImageUri;
    private SharedPreferences pref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // アクションバーを透明にする
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        // アクションバーに画像をセット
        Drawable d = getResources().getDrawable(R.drawable.black75);
        getActionBar().setBackgroundDrawable(d);
        getActionBar().setDisplayShowHomeEnabled(false);

        // //////////その他の処理/////////////
        setTitle(selectedComName);
        // tv_text = (TextView) findViewById(R.id.textView_commpany);
        listView = (ListView) findViewById(android.R.id.list);
        // 　画面遷移のためのインテントを作成
        intent_delete = new Intent(this, MainActivity.class);
        // 総合順位をセット
        // tv_text.setText("総合" + intent_rank + "位");
        // プレファレンスの準備
        pref = getSharedPreferences(nameOfPref, Context.MODE_PRIVATE);
        // 背景用のImageViewインスタンスを取得
        mBgImage = (ImageView) findViewById(R.id.bg_image);
        // チュートリアルの表示
        tutorial.showTutorial(this, flagTutoCom, "ビジョンや将来性をタップしよう！");

        // ///////////アニメーション////////////
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        animation = new TranslateAnimation(

        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
                0.0f);
        animation.setDuration(50);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(
                set, 0.5f);
        listView = getListView();
        listView.setLayoutAnimation(controller);

    }

    @Override
    public void onResume() {
        super.onResume();
        // ///////背景画像のセット//////////
        String st = pref.getString(prePic, null);
        if (st == null) {
            setDefault();
        } else {
            mImageUri = Uri.parse(st);
            if (mImageUri == null) {
                ;
            } else {
                // 背景画像をセットする
                setBgImage(mImageUri);
            }
        }
        // ////////////データベース処理////////////////
        try {
            Cursor c = dbMe.getDBData(this);

            while (c.moveToNext()) {

                int id = c.getInt(0);
                String name = c.getString(1);
                int total = c.getInt(2);
                String criteria0 = c.getString(3);
                int number0 = c.getInt(4);
                String criteria1 = c.getString(5);
                int number1 = c.getInt(6);
                String criteria2 = c.getString(7);
                int number2 = c.getInt(8);
                String criteria3 = c.getString(9);
                int number3 = c.getInt(10);
                String criteria4 = c.getString(11);
                int number4 = c.getInt(12);
                String criteria5 = c.getString(13);
                int number5 = c.getInt(14);
                String criteria6 = c.getString(15);
                int number6 = c.getInt(16);
                String criteria7 = c.getString(17);
                int number7 = c.getInt(18);
                String criteria8 = c.getString(19);
                int number8 = c.getInt(20);
                String criteria9 = c.getString(21);
                int number9 = c.getInt(22);

                if (id == selectedComID) {
                    // 　順位を文字に変換　(position+1の処理をしている)
                    String str0 = String.valueOf(number0 + 1);
                    String str1 = String.valueOf(number1 + 1);
                    String str2 = String.valueOf(number2 + 1);
                    String str3 = String.valueOf(number3 + 1);
                    String str4 = String.valueOf(number4 + 1);
                    String str5 = String.valueOf(number5 + 1);
                    String str6 = String.valueOf(number6 + 1);
                    String str7 = String.valueOf(number7 + 1);
                    String str8 = String.valueOf(number8 + 1);
                    String str9 = String.valueOf(number9 + 1);
                    // 企業を削除するための変数を取得
                    num0 = number0;
                    num1 = number1;
                    num2 = number2;
                    num3 = number3;
                    num4 = number4;
                    num5 = number5;
                    num6 = number6;
                    num7 = number7;
                    num8 = number8;
                    num9 = number9;

                    int counter = 0;
                    // アダプタに項目を追加する
                    if (num0 != -1) {
                        data.put("main", criteria0);
                        data.put("sub", str0 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria0);
                        criteria_col_num.add(4);
                        num.add("0");
                        counter++;
                    }
                    if (num1 != -1) {
                        // これを入れないとおかしくなる．
                        data = new HashMap<String, String>();

                        data.put("main", criteria1);
                        data.put("sub", str1 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria1);
                        criteria_col_num.add(6);
                        num.add("1");
                        counter++;
                    }
                    if (num2 != -1) {
                        // これを入れないとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria2);
                        data.put("sub", str2 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria2);
                        criteria_col_num.add(8);
                        num.add("2");
                        counter++;
                    }
                    if (num3 != -1) {
                        // これを入れないとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria3);
                        data.put("sub", str3 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria3);
                        criteria_col_num.add(10);
                        num.add("3");
                        counter++;
                    }
                    if (num4 != -1) {
                        // これを入れなとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria4);
                        data.put("sub", str4 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria4);
                        criteria_col_num.add(12);
                        num.add("4");
                    }
                    if (num5 != -1) {
                        // これを入れなとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria5);
                        data.put("sub", str5 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria5);
                        criteria_col_num.add(14);
                        num.add("5");
                    }
                    if (num6 != -1) {
                        // これを入れなとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria6);
                        data.put("sub", str6 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria6);
                        criteria_col_num.add(16);
                        num.add("6");
                    }
                    if (num7 != -1) {
                        // これを入れなとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria7);
                        data.put("sub", str7 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria7);
                        criteria_col_num.add(18);
                        num.add("7");
                    }
                    if (num8 != -1) {
                        // これを入れなとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria8);
                        data.put("sub", str8 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria8);
                        criteria_col_num.add(20);
                        num.add("8");
                    }
                    if (num9 != -1) {
                        // これを入れなとおかしくなる
                        data = new HashMap<String, String>();

                        data.put("main", criteria9);
                        data.put("sub", str9 + "位");
                        retDataList.add(data);
                        position_counter.add(counter);
                        criteria_name.add(criteria9);
                        criteria_col_num.add(22);
                        num.add("9");
                    }
                }
            }
        } finally {
            helper.close();
        }
        // /////////リストビューの処理//////////////
        // ListViewとデータをつなぐアダプタを作成
        // アイテムのレイアウトはAndroid組み込みの物を使用
        SimpleAdapter adapter = new SimpleAdapter(this, retDataList,
                R.layout.criteria_item, new String[] { "main", "sub" },
                new int[] { R.id.criteria_item_main, R.id.criteria_item_sub });
        // ListViewにアダプタ登録
        listView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        // リストビューを初期化
        retDataList.clear();
        position_counter.clear();
        criteria_name.clear();
        criteria_col_num.clear();
    }

    // ///////////// アクションバーの設置////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.commpany_menu, menu);
        // 上階層へ上がる
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // アクションバーに余裕があればアイコンを表示
        setSelection(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    // ////////アクションボタンが押された時///////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

        case android.R.id.home:
            // リストビューを初期化
            retDataList.clear();
            position_counter.clear();
            criteria_name.clear();
            criteria_col_num.clear();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
            return true;

        case R.id.commpany_actionbutton_add:
            // リストビューを初期化
            retDataList.clear();
            position_counter.clear();
            criteria_name.clear();
            criteria_col_num.clear();
            Intent intent = new Intent(Commpany.this, NewCriteria.class);
            startActivity(intent);
            return true;

        case R.id.commpany_actionbutton_delete:
            // ////////警告ダイアログのセット///////////////////
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // ダイアログの設定
            alertDialogBuilder.setTitle("警告"); // タイトル
            alertDialogBuilder.setMessage("本当に" + selectedComName + "を削除しますか？"); // 内容
            alertDialogBuilder.setIcon(R.drawable.worning); // アイコン設定

            // はいボタンの設定
            alertDialogBuilder.setPositiveButton("はい",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                Cursor c = getEventsCri();
                                showEventsCri(c);
                            } finally {
                                helper.close();
                            }
                            if (counter == 1) {
                                // 企業が一つしか登録されていなければ，企業を削除させない．
                                showDialog();
                                // カウンターの初期化
                                counter = 0;
                            } else {
                                // 企業を削除
                                SQLiteDatabase db = helper
                                        .getWritableDatabase();
                                db.delete(table_name,
                                        _ID + "=" + selectedComID, null);
                                helper.close();
                                // リストビューを初期化
                                retDataList.clear();
                                position_counter.clear();
                                criteria_name.clear();
                                criteria_col_num.clear();
                                // 画面遷移
                                startActivity(intent_delete);
                            }
                        }
                    });
            // いいえボタンの設定
            alertDialogBuilder.setNegativeButton("いいえ",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplication(), "キャンセルしました。",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
            // アラートダイアログのキャンセルが可能かどうかを設定します
            alertDialogBuilder.setCancelable(true);
            // 警告ダイアログの生成
            alertDialog = alertDialogBuilder.create();
            // 警告ダイアログの表示
            alertDialog.show();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    // //////////ギャラリーからの写真をセット①////////////////
    protected void setBgImage(Uri uri) {
        // 正しく回転されたBitmapを取得
        Bitmap bitmap = BitmapUtils.decodeUri(this, uri, 800);
        // 背景用のImageViewにBitmapをセット
        mBgImage.setImageBitmap(bitmap);
    }

    // //////////////デフォルトの背景画像セット//////////////////
    private void setDefault() {
        int ran = randam.nextInt(20);
        mBgImage.setImageResource(backGroundSorceList[ran]);
        mBgImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    // /////////リストビューがクリックされた時の処理//////////////
    // 会社の判断基準のページへ飛ぶ
    protected void onListItemClick(ListView l, View v, int position, long id) {
        for (int i = 0; i < position_counter.size(); i++) {
            if (position == position_counter.get(i)) {

                String str = String.valueOf(i);
                tableCriNum = "criteria_num" + num.get(i);
                tableCriName = "criteria_name" + num.get(i);
                columNumCriWei = i + 23;
                tableCriWeiNum = "criteria_weight" + str;
                columNumCri = criteria_col_num.get(i);
                selectedCriName = criteria_name.get(i);
                // リストビューを初期化
                position_counter.clear();
                criteria_name.clear();
                criteria_col_num.clear();

                Intent intent = new Intent(Commpany.this, Criteria.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

    }

    protected void showDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // ダイアログの設定
        alertDialog.setTitle("企業は１社以上登録しなければいけません"); // タイトル

        // ダイアログの作成と表示
        alertDialog.create();
        alertDialog.show();
    }

    // ///////////企業を削除する/////////////
    // 削除する企業より，順位の低い企業の順位を繰り上げ更新
    private Cursor getEventsCri() {
        SQLiteDatabase db = helper.getReadableDatabase();
        String SQL_select = "SELECT * FROM " + table_name + " ORDER BY "
                + criteria_num0 + " ASC";
        mCursor = db.rawQuery(SQL_select, null);
        return mCursor;
    }

    private void showEventsCri(Cursor c) {
        helper = new DBAdapter(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values0 = new ContentValues();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        ContentValues values6 = new ContentValues();
        ContentValues values7 = new ContentValues();
        ContentValues values8 = new ContentValues();
        ContentValues values9 = new ContentValues();

        while (c.moveToNext()) {

            // counterが１なら企業を削除させない
            counter++;

            int number0 = c.getInt(4);
            int number1 = c.getInt(6);
            int number2 = c.getInt(8);
            int number3 = c.getInt(10);
            int number4 = c.getInt(12);
            int number5 = c.getInt(14);
            int number6 = c.getInt(16);
            int number7 = c.getInt(18);
            int number8 = c.getInt(20);
            int number9 = c.getInt(22);

            // 判断基準０
            if (number0 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number0 <= num0) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values0.put(criteria_num0, number0 - 1);
                db.update(table_name, values0, criteria_num0 + "=" + number0,
                        null);
            }
            // 判断基準１
            if (number1 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number1 <= num1) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values1.put(criteria_num1, number1 - 1);
                db.update(table_name, values1, criteria_num1 + "=" + number1,
                        null);
            }
            // 判断基準２
            if (number2 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number2 <= num2) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values2.put(criteria_num2, number2 - 1);
                db.update(table_name, values2, criteria_num2 + "=" + number2,
                        null);
            }
            // 判断基準３
            if (number3 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number3 <= num3) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values3.put(criteria_num3, number3 - 1);
                db.update(table_name, values3, criteria_num3 + "=" + number3,
                        null);
            }
            // 判断基準４
            if (number4 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number4 <= num4) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values4.put(criteria_num4, number4 - 1);
                db.update(table_name, values4, criteria_num4 + "=" + number4,
                        null);
            }
            // 判断基準４
            if (number5 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number5 <= num5) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values5.put(criteria_num5, number5 - 1);
                db.update(table_name, values5, criteria_num5 + "=" + number5,
                        null);
            }
            // 判断基準４
            if (number6 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number6 <= num6) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values6.put(criteria_num6, number6 - 1);
                db.update(table_name, values6, criteria_num6 + "=" + number6,
                        null);
            }
            // 判断基準４
            if (number7 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number7 <= num7) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values7.put(criteria_num7, number7 - 1);
                db.update(table_name, values7, criteria_num7 + "=" + number7,
                        null);
            }
            // 判断基準４
            if (number8 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number8 <= num8) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values8.put(criteria_num8, number8 - 1);
                db.update(table_name, values8, criteria_num8 + "=" + number8,
                        null);
            }
            // 判断基準４
            if (number9 == -1) {
                // 判断基準が登録されていなければ何もしない
            } else if (number9 <= num9) {
                // 削除する企業よりも順位が高ければ何もしない
            } else {
                values9.put(criteria_num9, number9 - 1);
                db.update(table_name, values9, criteria_num9 + "=" + number9,
                        null);
            }

        }
    }

}