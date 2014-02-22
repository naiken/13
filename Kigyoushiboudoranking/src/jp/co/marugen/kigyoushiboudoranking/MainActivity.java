package jp.co.marugen.kigyoushiboudoranking;

import static jp.co.marugen.kigyoushiboudoranking.Constants.flagTutoMain;
import static jp.co.marugen.kigyoushiboudoranking.Constants.nameOfPref;
import static jp.co.marugen.kigyoushiboudoranking.Constants.prePic;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.newIDOfNewCom;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.registeredComNum;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedComID;
import static jp.co.marugen.kigyoushiboudoranking.TransferValues.selectedComName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jp.co.cayto.appc.sdk.android.AppC;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

//現在時刻の取得

@SuppressLint({ "NewApi", "InlinedApi" })
public class MainActivity extends ListActivity {
    // ////////フィールド///////////////////////////
    private DBAdapter helper = new DBAdapter(this);;
    private Initial initial = new Initial();
    private DBMethods dbMe = new DBMethods();
    private Tutorials tutorial = new Tutorials();

    private List<String> list = new ArrayList<String>();

    private List<Integer> commpany_id = new ArrayList<Integer>();
    private List<String> commpany_name2 = new ArrayList<String>();
    private final int[] backGroundSorceList = { R.drawable.bg0, R.drawable.bg1,
            R.drawable.bg2, R.drawable.bg3, R.drawable.bg4, R.drawable.bg5,
            R.drawable.bg6, R.drawable.bg7, R.drawable.bg8, R.drawable.bg9,
            R.drawable.bg10, R.drawable.bg11, R.drawable.bg12, R.drawable.bg13,
            R.drawable.bg14, R.drawable.bg15, R.drawable.bg16, R.drawable.bg17,
            R.drawable.bg18, R.drawable.bg19 };
    // Randomクラスのインスタンス化
    Random randam = new Random();

    private int counter = 0;
    private ListView listView;

    private SharedPreferences preference;
    private SharedPreferences pref;
    private Editor editor;
    private Editor edi;

    private SimpleCursorAdapter simpleCursorAdapter;
    private AppC mAppC;

    // 背景用のImageView
    private ImageView mBgImage;
    // 写真を選択するREQUEST_CODE
    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int String = 0;
    private static final int ArrayList = 0;
    // 写真のUri
    private Uri mImageUri;
    // Animation
    Animation animFadein;
    private Activity actionMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 　アクションバーを透明にする
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);

        setTitle("ランキング");

        // アクションバーに画像をセット
        Drawable d = getResources().getDrawable(R.drawable.black75);
        getActionBar().setBackgroundDrawable(d);
        // アクションバーのアイコンを除去
        getActionBar().setDisplayShowHomeEnabled(false);

        // //////////その他の準備 /////////////
        // プリファレンスの準備
        pref = getSharedPreferences(nameOfPref, Context.MODE_PRIVATE);
        // プリファレンスに書き込むためのEditorオブジェクト取得
        edi = pref.edit();
        // リストビュー
        listView = (ListView) findViewById(android.R.id.list);
        // appCのインスタンス化
        mAppC = new AppC(this);
        // 背景用のImageViewインスタンスを取得
        mBgImage = (ImageView) findViewById(R.id.bg_image);

        // 画面を切り替えるために，プレファレンスに値を書き込む

        // ///////// SQLiteに初期値を入力する/////////////////
        preference = getSharedPreferences("Preference Name", MODE_PRIVATE);
        editor = preference.edit();
        if (preference.getBoolean("preIni", false) == false) {
            // 初回起動時の処理
            initial.initialization(this);
            // プリファレンスの書き変え
            editor.putBoolean("preIni", true);
            editor.commit();
        } else {
            // 2回目以降の処理
        }
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
    protected void onResume() {
        super.onResume();
        // チュートリアルの表示
        tutorial.showTutorial(this, flagTutoMain, "企業名をタップしよう！");
        // カットイン初期化
        mAppC.initCutin();
        // ///////背景画像のセット//////////
        String st = pref.getString(prePic, null);
        // && pref.getBoolean(flagPic, false) == false)←下のif文内
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
        Cursor c = dbMe.getTotalRank(this);

        while (c.moveToNext()) {
            counter++;
            int id = c.getInt(0);
            String name = c.getString(1);
            // リストに項目を追加する
            list.add(counter + "位　" + name);
            commpany_id.add(id);
            commpany_name2.add(name);
        }
        helper.close();

        // /////////リストビューの処理//////////////
        // ListViewとデータをつなぐアダプタを作成
        // アイテムのレイアウトはAndroid組み込みの物を使用
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.activity_main_item, R.id.item_text, list);

        // ListViewにアダプタ登録
        listView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 　カウンターとリストビュー初期化
        counter = 0;
        list.clear();
        commpany_id.clear();
        commpany_name2.clear();
    }

    // @Override
    // protected void onDestroy() {
    // super.onDestroy();
    // flagPicNotChange = false;
    // edi.putBoolean(flagPic, flagPicNotChange);
    // edi.commit();
    // }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mAppC.callCutinFinish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    // ///////////// アクションバーの設置////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
        // アクションバーに余裕があればアイコンを表示
        setSelection(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    // ////////アクションボタンが押された時///////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.top_actionbutton_add:
            // IDを昇順にソート
            Collections.sort(commpany_id);
            int counter2 = 0;
            Iterator<Integer> i = commpany_id.iterator();

            try {
                while (i.hasNext()) {
                    if (counter2 == commpany_id.get(counter2)) {
                        counter2++;
                    } else {
                        break;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }

            commpany_id.clear();
            commpany_name2.clear();
            newIDOfNewCom = counter2;
            registeredComNum = counter;
            Intent intent = new Intent(this, NewCommpany.class);
            startActivity(intent);
            return true;

        case R.id.menu_settings:
            showSelectDialog();
            return true;

        case R.id.top_actionbutton_delete:
            Intent intent2 = new Intent(MainActivity.this, InitialPage.class);
            startActivity(intent2);
            return true;

        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void showSelectDialog() {
        // 選択肢の文字列
        String[] items = new String[] { "デフォルト", "ギャラリーから選択" };
        // ダイアログで選択されたときに呼び出されるリスナー
        android.content.DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                case 0: // 「デフォルト」が選ばれた場合
                    // プレファレンスを初期化
                    edi.putString(prePic, null);
                    edi.commit();
                    // ギャラリー画像を消す
                    mBgImage.setImageBitmap(null);
                    setDefault();
                    break;
                case 1: // 「ギャラリーから選択」が選ばれた場合
                    // デフォルト画像を消す
                    mBgImage.setBackgroundDrawable(null);
                    startGallery();
                    break;
                }
            }
        };
        // Builderを作って
        Builder builder = new AlertDialog.Builder(this);
        // 選択肢とリスナーをセットして
        builder.setItems(items, listener);
        // AlertDialogを作って
        AlertDialog dialog = builder.create();
        // ダイアログを表示!
        dialog.show();
    }

    protected void startGallery() {
        // ギャラリーを呼び出す
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    // //////////////デフォルトの背景画像セット//////////////////
    private void setDefault() {
        int ran = randam.nextInt(20);
        mBgImage.setImageResource(backGroundSorceList[ran]);
        mBgImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // resultCodeがRESULT_OKではない場合は何もしない
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        // ギャラリーの呼び出しの場合
        if (requestCode == REQUEST_CODE_GALLERY) {
            // プレファレンスにUriを保存
            edi.putString(prePic, data.getData().toString());
            edi.commit();
            // 背景画像をセットする
            setBgImage(data.getData());
            // フェードイン!
            Animation a = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            mBgImage.startAnimation(a);
        }
    }

    // /////////リストビューがクリックされた時の処理//////////////
    // 会社の詳細情報ページへ飛ぶ
    protected void onListItemClick(ListView l, View v, int position, long id) {
        for (int i = 0; i < commpany_id.size(); i++) {
            if (position == i) {
                selectedComName = commpany_name2.get(i);
                selectedComID = commpany_id.get(i);
                Intent intent = new Intent(MainActivity.this, Commpany.class);
                commpany_id.clear();
                commpany_name2.clear();
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        }
    }

    // //////////ギャラリーからの写真をセット①////////////////
    protected void setBgImage(Uri uri) {
        // 正しく回転されたBitmapを取得
        Bitmap bitmap = BitmapUtils.decodeUri(this, uri, 800);
        // 背景用のImageViewにBitmapをセット
        mBgImage.setImageBitmap(bitmap);
    }

}
