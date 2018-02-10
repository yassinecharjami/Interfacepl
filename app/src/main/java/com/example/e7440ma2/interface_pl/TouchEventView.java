package com.example.e7440ma2.interface_pl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import static android.content.ContentValues.TAG;

/**
 * Created by E7440MA2 on 02/02/2018.
 */

public class TouchEventView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    String COMMA_DELIMITER = ",     ";
    String NEW_LINE_SEPARATOR = "\n";
    private Paint paint = new Paint();
    private Path path = new Path();
    List<Coord> listCoord = new ArrayList<>();
    GestureDetector mGestureDetector = new GestureDetector(getContext(),this);
    float xPos,yPos;
    public static int yas = 3;
    float axPos=0, ayPos=0;
    String sama = "x = " + xPos + "  " +"y = "+ yPos;



    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15f);
    }
 /*   public void jump(int x) {
        TextView message = null;
        PlotActivity act = new PlotActivity();
        if (x != 0) {
            message = (TextView) act.findViewById(R.id.textView);
            message.setText("Jump");
        } else {
            message.setText(sama);
        }
    } */


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        if(yas==1){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.disque);
        Bitmap bmm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        canvas.drawBitmap(bmm, xPos, yPos, null);
                  axPos = xPos;
                  ayPos = yPos;
        }else if(yas==0){
            Paint p = new Paint();
            p.setAntiAlias(true);
            p.setColor(Color.MAGENTA);
            p.setStrokeJoin(Paint.Join.ROUND);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(15f);
            canvas.drawLine(axPos,ayPos,xPos, yPos, p);

        }
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }

    public void savefilee(String filename){
        String fileName = filename + ".txt";

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);

        try {
         /*   FileOutputStream fos = new FileOutputStream(file);
            fos.write("yassine".getBytes());
            fos.close(); */
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append("x       //      y");
            for (Coord c : listCoord) {
                myOutWriter.append(NEW_LINE_SEPARATOR);
                myOutWriter.append(String.valueOf(c.getA()));
                myOutWriter.append(COMMA_DELIMITER);
                myOutWriter.append(String.valueOf(c.getB()));
            }
            myOutWriter.close();
            fOut.flush();
            fOut.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void savetofile() {
        String filename = "yassine.txt";
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS);

        if (!path.exists()) {
            path.mkdir();
            if(path.mkdir()==false){
                Log.i(PlotActivity.DEBUGTAG, "directorydoesnotexist");}

        }

        final File file = new File(path, filename);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append("yassine");
             for (Coord c : listCoord) {
                myOutWriter.append(NEW_LINE_SEPARATOR);
                myOutWriter.append(String.valueOf(c.getA()));
                myOutWriter.append(COMMA_DELIMITER);
                myOutWriter.append(String.valueOf(c.getB()));
            }
            myOutWriter.close();
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

 /*   public void savefile() {
        try {
            FileWriter fileWriter = new FileWriter(Environment.getExternalStorageDirectory()+"/crd.txt", true);
            for (Coord c : listCoord) {
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(String.valueOf(c.getA()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(c.getB()));
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
          xPos = event.getX();
          yPos = event.getY();
        listCoord.add(new Coord(xPos, yPos));
        mGestureDetector.onTouchEvent(event);
        String message = String.format("Coordinates: (x = %.2f, y = %.2f))", xPos, yPos);
         Log.i(PlotActivity.DEBUGTAG, message);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos, yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos, yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        yas = 1;
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        yas = 1;
        Log.i(PlotActivity.DEBUGTAG, "jump");
        return true;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        yas = 0;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }
}
