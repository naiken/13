package jp.co.marugen.litterbatting;

import jp.co.cayto.appc.sdk.android.AppC;
import jp.co.cayto.appc.sdk.android.AppCSimpleView;
import jp.co.marugen.litterbatting.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class MainActivity extends Activity {

    private static final String URL = "file:///android_asset/litter_batting.html";
    private WebView webView;
    private String databasePath;
    private AppCSimpleView adLayout;
    private AppC appc;

    @SuppressLint("SetJavaScriptEnabled")
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasePath = this.getApplicationContext()
                .getDir("localstorage", Context.MODE_PRIVATE).getPath();
        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(URL);

        // //////////////// webViewの各種設定/////////////////
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.setVerticalScrollbarOverlay(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabasePath(databasePath);

//        // ///////////////広告を非表示にするため//////////////////
//        JsObject jsObj = new JsObject();
//        webView.addJavascriptInterface(jsObj, "andjs");
//        adLayout = (AppCSimpleView) findViewById(R.id.adLayout);

        // //////////////カットイン型広告/////////////////
        appc = new AppC(this);
    }

    @Override
    protected void onResume() {
        super.onResume();// カットイン初期化
        appc.initCutin();
    }

//    public class JsObject {
//
//        // 広告の表示
//        @JavascriptInterface
//        public void showAd() {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adLayout.setVisibility(View.VISIBLE);
//                }
//            });
//        }
//
//        // 広告の非表示
//        @JavascriptInterface
//        public void hideAd() {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    adLayout.setVisibility(View.INVISIBLE);
//                }
//            });
//        }
//        // カットイン
//        @JavascriptInterface
//        public void cutinAd() {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    appc.callCutin();
////                    adLayoutCutin.setVisibility(View.INVISIBLE);
//                }
//            });
//        }
//    }
}