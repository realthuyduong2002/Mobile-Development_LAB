package com.example.exercise3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {
    Bitmap[] frames = new Bitmap[100];
    int i = 0;
    public GraphicsView(Context context) {
        super(context);
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop1);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop2);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop3);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop4);
        frames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop5);
        frames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop6);
        frames[6] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop7);
        frames[7] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop8);
        frames[8] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop9);
        frames[9] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop10);
        frames[10] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop11);
        frames[11] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop12);
        frames[12] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop13);
        frames[13] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop14);
        frames[14] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop15);
        frames[15] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop16);
        frames[16] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop17);
        frames[17] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop18);
        frames[18] = BitmapFactory.decodeResource(getResources(), R.drawable.hiphop19);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (i < 128 && frames[i] != null){
            canvas.scale(0.75f, 0.8f);
            canvas.drawBitmap(frames[i], 40, 40, new Paint());
        } else {
            i = 0;
        }
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        i++;
        return true;
    }
}