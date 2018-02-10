package com.example.e7440ma2.interface_pl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;



/**
 * Created by E7440MA2 on 08/02/2018.
 */

public class DblcEvent implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener{

    //Bitmap bm = BitmapFactory.decodeResource(null, R.drawable.disque);


    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.i(PlotActivity.DEBUGTAG, "yassjjsh");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }
}
