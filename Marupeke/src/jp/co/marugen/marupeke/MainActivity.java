package jp.co.marugen.marupeke;

import net.app_c.cloud.sdk.AppCCloud;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	private AppCCloud appCCloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     // appC cloud生成
        appCCloud = new AppCCloud(this).start();

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton1);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void finish() {
        super.finish();
        // appC cloud終了処理
        appCCloud.finish();
    }
}
