package jp.co.marugen.kigyoushiboudoranking;

import static android.provider.BaseColumns._ID;
import static jp.co.marugen.kigyoushiboudoranking.Constants.flagTutoCri;
import static jp.co.marugen.kigyoushiboudoranking.Constants.table_name;
import static jp.co.marugen.kigyoushiboudoranking.Constants.total_num;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.columNumCri;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedCriName;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriName;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriNum;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.tableCriWeiNum;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Criteria extends Activity implements OnItemClickListener {

    // データベース
    private DBAdapter helper;
    private SQLiteDatabase db;
    private Tutorials tutorial = new Tutorials();

    private int total;

    private int pre_position;
    private int aft_position;

    static final List<String> PREFS2 = new ArrayList<String>();
    private List<Integer> ID = new ArrayList<Integer>();
    private List<Integer> list = new ArrayList<Integer>();

    // ダイアログ
    private AlertDialog alertDialog;
    private Intent intent_delete;

    int mDraggingPosition = -1;
    SampleAdapter mAdapter;
    DragDropListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // アクションバーを透明にする
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.criteria);
        // アクションバーに画像をセット
        Drawable d = getResources().getDrawable(R.drawable.black75);
        getActionBar().setBackgroundDrawable(d);
        getActionBar().setDisplayShowHomeEnabled(false);

        // ////////その他の準備//////////////
        setTitle(selectedCriName);
        // データベースの生成
        helper = new DBAdapter(this);
        // 　画面遷移のためのインテントを作成
        intent_delete = new Intent(this, Commpany.class);
        // チュートリアルの表示
        tutorial.showTutorial(this, flagTutoCri, "好きな順にDrag&Dropで並び替えよう！");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ////////////データベース処理////////////////
        try {
            Cursor c = getEvents();
            while (c.moveToNext()) {

                int id = c.getInt(0);
                int num = c.getInt(columNumCri);
                String name = c.getString(1);
                // アダプタに項目を追加する
                PREFS2.add(name);
                ID.add(id);
                list.add(num);

            }
        } finally {
            helper.close();
        }
        mAdapter = new SampleAdapter();
        mListView = (DragDropListView) findViewById(R.id.list);
        mListView.setDragListener(new DragListener());
        mListView.setSortable(true);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 新しいポジションを登録
        SQLiteDatabase db = helper.getWritableDatabase();
        for (int i = 0; i < list.size(); i++) {
            int id = ID.get(i);
            ContentValues values = new ContentValues();
            values.put(tableCriNum, list.get(i));
            db.update(table_name, values, _ID + "=" + id, null);
        }
        try {
            Cursor c = getEvents();
            showEvents(c);
        } finally {
            helper.close();
        }
        // リストビューを初期化
        PREFS2.clear();
        ID.clear();
        list.clear();
    }

    // ////////// アクションバーの設置////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.criteria_menu, menu);
        // 上階層へ上がる
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // アクションバーに余裕があればアイコンを表示
        setSelection(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    // ///////自動生成された//////////
    private void setSelection(int showAsActionIfRoom) {
        // TODO Auto-generated method stub

    }

    // ////////アクションボタンが押された時///////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

        case android.R.id.home:

            // 値の引き継ぎ
            Intent intent = new Intent(this, Commpany.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);

            return true;

        case R.id.menu_settings_criteria:
            Intent putIntent = new Intent(this, CreiteriaWeight.class);
            startActivity(putIntent);

            return true;

        case R.id.criteria_actionbutton_delete:
            // ////////警告ダイアログのセット///////////////////
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // ダイアログの設定
            alertDialogBuilder.setTitle("警告"); // タイトル
            alertDialogBuilder.setMessage("本当に" + selectedCriName + "を削除しますか？"); // 内容
            alertDialogBuilder.setIcon(R.drawable.worning); // アイコン設定

            // はいボタンの設定
            alertDialogBuilder.setPositiveButton("はい",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 判断基準を更新
                            try {
                                Cursor c = getEvents();
                                db = helper.getWritableDatabase();
                                showEvents2(c);
                            } finally {
                                helper.close();
                            }
                            // リストビューを初期化
                            PREFS2.clear();
                            ID.clear();
                            list.clear();
                            // 画面遷移
                            startActivity(intent_delete);
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

    // /////////////データベースの読み込み////////////////
    private Cursor getEvents() {

        db = helper.getReadableDatabase();
        String SQL_select = "SELECT * FROM " + table_name + " ORDER BY "
                + tableCriNum + " ASC";
        Cursor mCursor = db.rawQuery(SQL_select, null);
        return mCursor;
    }

    // ////////データベースからデータを取り出す&書き込む，戻るとき////////////
    private void showEvents2(Cursor c) {
        while (c.moveToNext()) {
            int id = c.getInt(0);

            ContentValues values = new ContentValues();
            values.put(tableCriNum, -1);
            values.put(tableCriName, "kara");
            values.put(tableCriWeiNum, 3);
            db.update(table_name, values, _ID + "=" + id, null);
        }
    }

    // ////////データベースからデータを取り出す，消すとき////////////
    private void showEvents(Cursor c) {
        ContentValues values = new ContentValues();
        while (c.moveToNext()) {

            int id = c.getInt(0);
            int num0 = c.getInt(4);
            if (num0 == -1) {
                num0 = 0;
            }
            int num1 = c.getInt(6);
            if (num1 == -1) {
                num1 = 0;
            }
            int num2 = c.getInt(8);
            if (num2 == -1) {
                num2 = 0;
            }
            int num3 = c.getInt(10);
            if (num3 == -1) {
                num3 = 0;
            }
            int num4 = c.getInt(12);
            if (num4 == -1) {
                num4 = 0;
            }
            int num5 = c.getInt(14);
            if (num5 == -1) {
                num5 = 0;
            }
            int num6 = c.getInt(16);
            if (num6 == -1) {
                num6 = 0;
            }
            int num7 = c.getInt(18);
            if (num7 == -1) {
                num7 = 0;
            }
            int num8 = c.getInt(20);
            if (num8 == -1) {
                num8 = 0;
            }
            int num9 = c.getInt(22);
            if (num9 == -1) {
                num9 = 0;
            }
            int weight0 = c.getInt(23);
            int weight1 = c.getInt(24);
            int weight2 = c.getInt(25);
            int weight3 = c.getInt(26);
            int weight4 = c.getInt(27);
            int weight5 = c.getInt(28);
            int weight6 = c.getInt(29);
            int weight7 = c.getInt(30);
            int weight8 = c.getInt(31);
            int weight9 = c.getInt(32);

            total = num0 * weight0 + num1 * weight1 + num2 * weight2 + num3
                    * weight3 + num4 * weight4 + num5 * weight5 + num6
                    * weight6 + num7 * weight7 + num8 * weight8 + num9
                    * weight9;
            values.put(total_num, total);
            db.update(table_name, values, _ID + "=" + id, null);
        }
    }

    // ///////////////Drag&Drop後に取得したpositionをもとに，データベースの更新//////////////
    private void Input() {
        for (int i = 0; i < list.size(); i++) {
            if (pre_position == list.get(i)) {
                list.set(i, aft_position);
                for (int a = 0; a < list.size(); a++) {
                    if (a == i) {
                        // 何もしない
                    } else if (pre_position > aft_position) {
                        if (aft_position <= list.get(a)
                                && list.get(a) < pre_position) {
                            list.set(a, list.get(a) + 1);
                        }
                    } else if (pre_position < aft_position) {
                        if (pre_position < list.get(a)
                                && list.get(a) <= aft_position) {
                            list.set(a, list.get(a) - 1);
                        }
                    }
                }
                ;
                break;
            }
        }
    }

    class SampleAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return PREFS2.size();
        }

        @Override
        public String getItem(int position) {
            return PREFS2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        android.R.layout.simple_list_item_1, null);
            }
            final TextView view = (TextView) convertView;
            view.setText(PREFS2.get(position));
            view.setVisibility(position == mDraggingPosition ? View.INVISIBLE
                    : View.VISIBLE);
            return convertView;
        }
    }

    class DragListener extends DragDropListView.SimpleDragListener {
        @Override
        public int onStartDrag(int position) {
            // クリック時のポジション
            pre_position = position;

            mDraggingPosition = position;
            mListView.invalidateViews();
            return position;
        }

        @Override
        public int onDuringDrag(int positionFrom, int positionTo) {
            if (positionFrom < 0 || positionTo < 0
                    || positionFrom == positionTo) {
                return positionFrom;
            }
            if (positionFrom < positionTo) {
                final int min = positionFrom;
                final int max = positionTo;
                final String data2 = PREFS2.get(min);

                int i = min;
                while (i < max) {
                    PREFS2.set(i, PREFS2.get(i + 1));
                    i++;
                }
                PREFS2.set(max, data2);

            } else if (positionFrom > positionTo) {
                final int min = positionTo;
                final int max = positionFrom;
                final String data2 = PREFS2.get(max);

                int i = max;
                while (i > min) {
                    PREFS2.set(i, PREFS2.get(i - 1));
                    i--;
                }
                PREFS2.set(min, data2);
            }
            mDraggingPosition = positionTo;
            mListView.invalidateViews();
            return positionTo;
        }

        @Override
        public boolean onStopDrag(int positionFrom, int positionTo) {
            // クリック後のポジション
            aft_position = positionFrom;
            // listの中にDrag&Drop後のポジションを入れる
            Input();

            mDraggingPosition = -1;
            mListView.invalidateViews();
            return super.onStopDrag(positionFrom, positionTo);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub

    }
}