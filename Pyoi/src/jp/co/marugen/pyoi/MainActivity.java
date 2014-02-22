package jp.co.marugen.pyoi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.media.FaceDetector;
import android.media.FaceDetector.Face;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private static final int REQUEST_GALLERY = 0;

    private ImageButton imgBtnLeft;
    private ImageButton imgBtnRight;
    private ImageButton imgBtnSmallLeft;
    private ImageButton imgBtnSmallRight;
    private ImageButton turnImgBtnRight;
    private ImageButton turnImgBtnLeft;
    private ImageButton imgBtnSelectionTurnLeft;
    private ImageButton imgBtnSelectionTurnRight;
    private boolean isRight;
    private boolean rightSelection;
    private boolean leftSelection;
    private boolean pyoiSelection;

    private Bitmap trueCreateimgBitmap;
    private Bitmap falseCreateimgBitmap;
    private Bitmap leftBitmap;
    private Bitmap rightBitmap;
    private Canvas leftCanvas;
    private Canvas rightCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        imgBtnSmallLeft = (ImageButton) this.findViewById(R.id.imageButton6);
        imgBtnSmallLeft.setEnabled(false);

        imgBtnSelectionTurnLeft = (ImageButton) findViewById(R.id.imageButton3);
        imgBtnSelectionTurnLeft.setEnabled(false);

        imgBtnSelectionTurnRight = (ImageButton) findViewById(R.id.imageButton5);
        imgBtnSelectionTurnRight.setEnabled(false);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // ダイアログの設定
        alertDialog.setTitle("保存の確認"); // タイトル
        alertDialog.setMessage("保存しました！！"); // 内容
        alertDialog.setIcon(R.drawable.ic_launcher); // アイコン設定

        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("AlertDialog", "Positive which :" + which);
                    }
                });

        imgBtnLeft = (ImageButton) findViewById(R.id.imageButton1);
        imgBtnLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isRight = false;
                callGallery();
            }
        });

        imgBtnRight = (ImageButton) findViewById(R.id.imageButton2);
        imgBtnRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isRight = true;
                callGallery();
            }

        });

        imgBtnSmallLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                float leftLeft = 0;
                float leftTop = 0;
                float leftRight = 0;
                float leftBottom = 0;
                float rightLeft = 0;
                float rightTop = 0;
                float rightRight = 0;
                float rightBottom = 0;

                leftBitmap = falseCreateimgBitmap;
                leftBitmap = leftBitmap.copy(Bitmap.Config.RGB_565, true);
                leftCanvas = new Canvas(leftBitmap);

                final int leftMAX_FACES = 1;
                Face[] leftFaces = new Face[leftMAX_FACES];
                FaceDetector leftdetector = new FaceDetector(leftBitmap
                        .getWidth(), leftBitmap.getHeight(), leftMAX_FACES);
                int leftNum = leftdetector.findFaces(leftBitmap, leftFaces);

                if (leftNum > 0 && leftFaces[0] != null) {
                    PointF point = new PointF();
                    leftFaces[0].getMidPoint(point);

                    int width = (int) leftFaces[0].eyesDistance() * 3;
                    int height = (int) (width * 1.5);

                    leftLeft = point.x - width / 2;
                    leftTop = point.y - height / 2;
                    leftRight = point.x + width / 2;
                    leftBottom = point.y + height / 2;
                }

                rightBitmap = trueCreateimgBitmap;
                rightBitmap = rightBitmap.copy(Bitmap.Config.RGB_565, true);
                rightCanvas = new Canvas(rightBitmap);

                final int rightMAX_FACES = 1;
                Face[] rightFaces = new Face[rightMAX_FACES];
                FaceDetector rightDetector = new FaceDetector(rightBitmap
                        .getWidth(), rightBitmap.getHeight(), rightMAX_FACES);
                int rightNum = rightDetector.findFaces(rightBitmap, rightFaces);

                if (rightNum > 0 && rightFaces != null) {
                    PointF point = new PointF();
                    rightFaces[0].getMidPoint(point);

                    int width = (int) rightFaces[0].eyesDistance() * 3;
                    int height = (int) (width * 1.5);

                    rightLeft = point.x - width / 2;
                    rightTop = point.y - height / 2;
                    rightRight = point.x + width / 2;
                    rightBottom = point.y + height / 2;
                }

                Rect leftGrid = new Rect((int) leftLeft, (int) leftTop,
                        (int) leftRight, (int) leftBottom);

                Rect leftFaceGrid = new Rect(0, 0,
                        (int) (leftRight - leftLeft),
                        (int) (leftBottom - leftTop));

                Rect rightGrid = new Rect((int) rightLeft, (int) rightTop,
                        (int) rightRight, (int) rightBottom);

                Rect rightFaceGrid = new Rect(0, 0,
                        (int) (rightRight - rightLeft),
                        (int) (rightBottom - rightTop));

                Bitmap leftFace = Bitmap.createBitmap(leftBitmap,
                        (int) leftLeft, (int) leftTop,
                        (int) (leftRight - leftLeft) + 1,
                        (int) (leftBottom - leftTop) + 1);

                Bitmap rightFace = Bitmap.createBitmap(rightBitmap,
                        (int) rightLeft, (int) rightTop,
                        (int) (rightRight - rightLeft) + 1,
                        (int) (rightBottom - rightTop) + 1);

                rightCanvas.drawBitmap(leftFace, leftFaceGrid, rightGrid, null);
                ImageButton leftImageButton = (ImageButton) findViewById(R.id.imageButton1);
                leftImageButton.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                leftImageButton.setImageBitmap(leftBitmap);

                leftCanvas.drawBitmap(rightFace, rightFaceGrid, leftGrid, null);
                ImageButton rightImageButton = (ImageButton) findViewById(R.id.imageButton2);
                rightImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
                rightImageButton.setImageBitmap(rightBitmap);

                pyoiSelection = true;

            }
        });

        imgBtnSmallRight = (ImageButton) findViewById(R.id.imageButton4);
        imgBtnSmallRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                leftSaveFiles(leftBitmap);
                rightSaveFiles(rightBitmap);

                // handler.postDelayed(showMessageTask, 1000);

                if (pyoiSelection) {
                    alertDialog.create();
                    alertDialog.show();
                }
            }
        });

        imgBtnSelectionTurnRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                turnImgBtnRight = (ImageButton) findViewById(R.id.imageButton2);
                turnImgBtnRight.setScaleType(ImageButton.ScaleType.MATRIX);
                Matrix mtx = turnImgBtnRight.getImageMatrix();
                mtx.postRotate(-90.0f, turnImgBtnRight.getWidth() / 2,
                        turnImgBtnRight.getHeight() / 2);
                turnImgBtnRight.setImageMatrix(mtx);
                turnImgBtnRight.invalidate();
            }

        });
        // Toast.makeText(this, count, Toast.LENGTH_SHORT).show();

        imgBtnSelectionTurnLeft.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                turnImgBtnLeft = (ImageButton) findViewById(R.id.imageButton1);
                turnImgBtnLeft.setScaleType(ImageButton.ScaleType.MATRIX);
                Matrix mtx = turnImgBtnLeft.getImageMatrix();
                mtx.postRotate(-90.0f, turnImgBtnLeft.getWidth() / 2,
                        turnImgBtnLeft.getHeight() / 2);
                turnImgBtnLeft.setImageMatrix(mtx);
                turnImgBtnLeft.invalidate();
            }

        });
    }

    private void callGallery() {
        // ギャラリー呼び出し
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                BitmapFactory.Options bitmapOption = new BitmapFactory.Options();

                bitmapOption.inSampleSize = 4;

                InputStream in = getContentResolver().openInputStream(
                        data.getData());
                Bitmap img = BitmapFactory.decodeStream(in, null, bitmapOption);
                in.close();

                // 選択した画像を表示
                if (isRight) {
                    trueCreateimgBitmap = Bitmap.createBitmap(img);
                    ImageButton LeftImage = (ImageButton) findViewById(R.id.imageButton2);
                    LeftImage.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                    LeftImage.setImageBitmap(trueCreateimgBitmap);

                    rightSelection = true;
                    imgBtnSelectionTurnRight.setEnabled(true);

                    if (leftSelection) {
                        imgBtnSmallLeft.setEnabled(true);
                    }
                } else {
                    falseCreateimgBitmap = Bitmap.createBitmap(img);
                    ImageButton RightImage = (ImageButton) findViewById(R.id.imageButton1);
                    RightImage.setScaleType(ImageButton.ScaleType.FIT_CENTER);
                    RightImage.setImageBitmap(falseCreateimgBitmap);

                    leftSelection = true;
                    imgBtnSelectionTurnLeft.setEnabled(true);

                    if (rightSelection) {
                        imgBtnSmallLeft.setEnabled(true);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void leftSaveFiles(Bitmap leftBitmap) {

        File dir = null;
        if (readySdcard()) { // 1
            dir = new File(getDirPath());
            if (!dir.exists()) { // 2
                if (!dir.mkdirs()) {
                    // return new PluginResult(Status.ERROR,
                    // "Failed Create Dir");
                }
            }
        } else {
            dir = getApplicationContext().getFilesDir(); // 3
        }
        File path = new File(getFilePath(dir, "left")); // 4
        System.out.println("File path = new File(getFilePath(dir))"
                + String.valueOf(path));

        // Bitmap bmp = this.convertBitmapFromJpeg(data); // data =
        // (String)"data:image/jpeg;base64,・・・"

        try {
            this.saveImage(path, leftBitmap);
            try {
                addGarally(path);
                System.out.println("画像の保存に成功");
            } catch (Exception e) {
                System.out.println("画像の保存に失敗 : " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("画像の保存に失敗 : " + e.getMessage());
        }
    }

    private void rightSaveFiles(Bitmap rightBitmap) {

        File dir = null;
        if (readySdcard()) { // 1
            dir = new File(getDirPath());
            if (!dir.exists()) { // 2
                if (!dir.mkdirs()) {
                    // return new PluginResult(Status.ERROR,
                    // "Failed Create Dir");
                }
            }
        } else {
            dir = getApplicationContext().getFilesDir(); // 3
        }
        File path = new File(getFilePath(dir, "right")); // 4
        System.out.println("File path = new File(getFilePath(dir))"
                + String.valueOf(path));

        // Bitmap bmp = this.convertBitmapFromJpeg(data); // data =
        // (String)"data:image/jpeg;base64,・・・"

        try {
            this.saveImage(path, rightBitmap);
            try {
                addGarally(path);
                System.out.println("画像の保存に成功");
            } catch (Exception e) {
                System.out.println("画像の保存に失敗 : " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("画像の保存に失敗 : " + e.getMessage());
        }
    }

    private boolean readySdcard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    private String getDirPath() {
        return Environment.getExternalStorageDirectory().getPath() + "/MyApp/";
    }

    @SuppressWarnings("deprecation")
    private String getFilePath(File dir, String name) {
        Date d = new Date();

        String path = dir.getAbsolutePath() + "/";
        path += String.format("%4d%02d%02d%02d%02d%02d-create" + name + ".jpg",
                (1900 + d.getYear()), (d.getMonth() + 1), d.getDate(),
                d.getHours(), d.getMinutes(), d.getSeconds());

        return path;
    }

    private void saveImage(File path, Bitmap bmp) throws Exception {
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            bmp.compress(CompressFormat.JPEG, 100, out);
            out.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void addGarally(File path) throws Exception {
        try {
            ContentValues values = new ContentValues();
            ContentResolver contentResolver = getApplicationContext()
                    .getContentResolver();
            values.put(Images.Media.MIME_TYPE, "image/jpeg");
            values.put(Images.Media.DATE_MODIFIED,
                    System.currentTimeMillis() / 1000);
            values.put(Images.Media.SIZE, path.length());
            values.put(Images.Media.TITLE, path.getName());
            values.put(Images.Media.DATA, path.getPath());
            contentResolver.insert(Media.EXTERNAL_CONTENT_URI, values);
        } catch (Exception e) {
            throw e;
        }
    }

    public class HelloDialog extends Dialog {

        public HelloDialog(Context context) {
            super(context);
        }

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setTitle(R.string.test_title);
            setContentView(R.layout.activity_main);
        }

    }

}
