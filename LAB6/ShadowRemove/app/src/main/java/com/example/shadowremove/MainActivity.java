package com.example.shadowremove;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.android.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Initialization failed");
        }
    }

    private ImageView imageView;
    private Bitmap bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        Button btnRemoveShadow = findViewById(R.id.btnRemoveShadow);

        // Load the book image from resources
        bookImage = BitmapFactory.decodeResource(getResources(), R.drawable.img_11);
        imageView.setImageBitmap(bookImage);

        btnRemoveShadow.setOnClickListener(v -> {
            if (bookImage != null) {
                removeShadow(bookImage, new MyCallback<Bitmap>() {
                    @Override
                    public void onComplete(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                        Toast.makeText(MainActivity.this, "Shadow removal complete", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "Image not loaded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removeShadow(Bitmap bitmap, MyCallback<Bitmap> callback) {
        getShadowFilteredImage(bitmap, callback);
    }

    public interface MyCallback<T> {
        void onComplete(T result);
    }

    public void getShadowFilteredImage(Bitmap bitmap, final MyCallback<Bitmap> callback) {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            Mat srcMat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC3);
            Utils.bitmapToMat(bitmap, srcMat);
            Imgproc.cvtColor(srcMat, srcMat, Imgproc.COLOR_BGR2HSV);

            List<Mat> hsvPlanes = new ArrayList<>();
            Core.split(srcMat, hsvPlanes);

            Mat vChannel = hsvPlanes.get(2); // V channel for processing

            // Shadow removal processing
            Mat dilated = new Mat();
            Mat kernel = Mat.ones(7, 7, CvType.CV_32F);
            Imgproc.dilate(vChannel, dilated, kernel);
            Imgproc.medianBlur(dilated, dilated, 21);
            Mat diff = new Mat();
            Core.absdiff(vChannel, dilated, diff);
            Core.bitwise_not(diff, diff);
            Mat normalized = new Mat();
            Core.normalize(diff, normalized, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);

            // Lighten the V channel
            Core.add(normalized, new Scalar(50), normalized); // Increase brightness by adding a constant value

            hsvPlanes.set(2, normalized); // Replace V channel with the processed one

            Mat resultMat = new Mat();
            Core.merge(hsvPlanes, resultMat);
            Imgproc.cvtColor(resultMat, resultMat, Imgproc.COLOR_HSV2BGR);

            Bitmap resultBitmap = Bitmap.createBitmap(resultMat.cols(), resultMat.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(resultMat, resultBitmap);

            handler.post(() -> callback.onComplete(resultBitmap));
        });
    }
}