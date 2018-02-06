package com.example.e7440ma2.interface_pl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.text.*;
import java.lang.Throwable;

import static android.content.ContentValues.TAG;

/**
 * Created by E7440MA2 on 02/02/2018.
 */

public class TouchEventView extends View {
    String COMMA_DELIMITER = ",";
    String NEW_LINE_SEPARATOR = "\n";
    private Paint paint = new Paint();
    private Path path = new Path();
    List<Coord> listCoord = new ArrayList<>();

    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15f);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawPath(path, paint);
    }

    public void clearCanvas(){
        path.reset();
        invalidate();
    }

 /*   public void savetofile()
    {
        final File path =
                Environment.getExternalStoragePublicDirectory
                        ( Environment.DIRECTORY_DOWNLOADS );

        final File file = new File(path, "coord.txt");
        try
        {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            for(Coord c : listCoord){
                myOutWriter.append(NEW_LINE_SEPARATOR);
                myOutWriter.append(String.valueOf(c.getX()));
                myOutWriter.append(COMMA_DELIMITER);
                myOutWriter.append(String.valueOf(c.getY()));
            }
            myOutWriter.close();
            fOut.flush();
            fOut.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    } */

 /*  public void savefile() {
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\E7440MA2\\AndroidStudioProjects\\Interfacepl\\app\\src\\main\\java\\com\\example\\e7440ma2\\interface_pl\\coord.csv",true);
            fileWriter.append(FILE_HEADER);
            for(Coord c : listCoord){
                fileWriter.append(NEW_LINE_SEPARATOR);
                fileWriter.append(String.valueOf(c.getX()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(c.getY()));
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */



    @Override
    public boolean onTouchEvent(MotionEvent event){
        float xPos = event.getX();
        float yPos = event.getY();
        listCoord.add(new Coord(xPos, yPos));

        String message = String.format("Coordinates: (x = %.2f, y = %.2f))",xPos,yPos);

        Log.i(PlotActivity.DEBUGTAG, message);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos,yPos);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }
}
