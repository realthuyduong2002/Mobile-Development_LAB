package com.example.tutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.d("OpenCV", "Initialization failed");
        }
    }

    private ImageView imageView;
    private CascadeClassifier faceDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        // Load OpenCV cascade file from app resources
        try {
            InputStream is = getResources().openRawResource(R.raw.lbpcascade_frontalface);
            File cascadeDir = getDir("cascade", MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "lbpcascade_frontalface.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();

            faceDetector = new CascadeClassifier(mCascadeFile.getAbsolutePath());
        } catch (Exception e) {
            Log.e("OpenCV", "Error loading cascade", e);
        }

        // Load the image from drawable and perform face detection
        loadAndDetectFace();
    }

    private void loadAndDetectFace() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img); // Replace 'test_photo' with your photo's name
        imageView.setImageBitmap(bitmap);

        // Convert to OpenCV Mat
        Mat imageMat = new Mat();
        Bitmap tempBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        org.opencv.android.Utils.bitmapToMat(tempBitmap, imageMat);

        // Perform face detection
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(imageMat, faceDetections);

        // Change the color of the bounding box (Blue, Green, Red, Alpha)
        Scalar boundingBoxColor = new Scalar(255, 0, 0, 255); // Blue color

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(imageMat, rect.tl(), rect.br(), boundingBoxColor, 10);
        }

        // Convert back to Bitmap and display
        org.opencv.android.Utils.matToBitmap(imageMat, tempBitmap);
        imageView.setImageBitmap(tempBitmap);

        Toast.makeText(this, "Face detection complete", Toast.LENGTH_SHORT).show();
    }
}