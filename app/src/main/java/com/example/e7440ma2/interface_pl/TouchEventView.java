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
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.io.*;
import java.util.*;
import java.io.File;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TouchEventView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{
    String COMMA_DELIMITER = ",     ";
    String NEW_LINE_SEPARATOR = "\n";
    private Paint paint = new Paint();
    private Path path = new Path();
    private Path pat = new Path();
    private Path patt = new Path();
    private Paint p = new Paint();
    private Paint paintpt = new Paint();
    Path pcircle = new Path();
    List<Coord> listCoord = new ArrayList<>();
    Vector<Double> xtt =new Vector<Double>(1,1);
    Vector<Double> ytt =new Vector<Double>(1,1);

    GestureDetector mGestureDetector = new GestureDetector(getContext(),this);
    float xPos,yPos;
    float yas = 3;
    float dpt = 0;
    float axPos=0, ayPos=0, pxPos=0, pyPos=0;
    String sama = "x = " + xPos + "  " +"y = "+ yPos;
    int coeff=1;


    public TouchEventView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(15f);
        //Arrow
        p.setAntiAlias(true);
        p.setColor(Color.DKGRAY);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(15f);
        pat.setFillType(Path.FillType.EVEN_ODD);
        // Control Point
        paintpt.setAntiAlias(true);
        paintpt.setColor(Color.MAGENTA);
        paintpt.setStrokeJoin(Paint.Join.ROUND);
        paintpt.setStyle(Paint.Style.STROKE);
        paintpt.setStrokeWidth(50f);
        // point
        pcircle.setFillType(Path.FillType.EVEN_ODD);
    }

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
            //Arrow creation
           pat.moveTo(axPos, ayPos);
           pat.lineTo(pxPos,pyPos);
           pat.close();
           canvas.drawPath(pat,p);
           float angle=15, radius=100;
            float anglerad= (float) (PI*angle/180.0f);
            float lineangle= (float) (atan2(pyPos-ayPos,pxPos-axPos));
            patt.setFillType(Path.FillType.EVEN_ODD);
            patt.moveTo(pxPos, pyPos);
            patt.lineTo((float)(pxPos-radius*cos(lineangle - (anglerad / 2.0))),
                    (float)(pyPos-radius*sin(lineangle - (anglerad / 2.0))));
            patt.lineTo((float)(pxPos-radius*cos(lineangle + (anglerad / 2.0))),
                    (float)(pyPos-radius*sin(lineangle + (anglerad / 2.0))));
            patt.close();
            canvas.drawPath(patt, p);
        }
        if(dpt==1){
            for(int i=0; i<xpp.length; i++){
              //  canvas.drawPoint((float) xpp[i],(float) ypp[i],paintpt);
                pcircle.addCircle((float) xpp[i],(float) ypp[i], 20, Path.Direction.CCW);
                pcircle.close();
                canvas.drawPath(pcircle,paintpt);
            }
        }
    }

    //Clear point of control table
    public void clearTable(){
        for (int i=0; i<xpp.length; i++){
            xpp[i]=0; ypp[i]=0;
            xtt.clear();
            ytt.clear();
            listCoord.clear();
        }
    }

    // Clear Canvas
    public void clearCanvas() {
        path.reset();
        pat.reset();
        patt.reset();
        pcircle.reset();
        dpt=0;
        yas=3;
        invalidate();
    }

    // control point coeff
    public void controlcoeff(int x){
        coeff = x;
    }
    // Save Coordinates as a TextFile
    public void savefilee(String filename){
        String fileName = filename + ".txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);
        try {
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

    // Linking interface with processing
    Float[] xfloat;
    Float[] yfloat;
    Point[] pc;
    double[] x;
    double[] y;
    double[] xpp;
    double[] ypp;
    Map<String, Object> hash = new HashMap<String, Object>();

    // return the draw as a Trajectory
    public Trajectory data(){
        Point[] coord = new Point[listCoord.size()];
        xfloat = new Float[listCoord.size()];
        yfloat = new Float[listCoord.size()];
        for (int i=0; i<listCoord.size();i++) {
            xfloat[i] = listCoord.get(i).getA();
            yfloat[i] = listCoord.get(i).getB();
        }
        for(int i=0; i<listCoord.size(); i++){
            Point pointCourant = new Point(xfloat[i],yfloat[i]);
            coord[i] = pointCourant;
        }
        return new Trajectory(listCoord.size(), coord);
    }

    // Saving the coordinates of control points in a hashMap
    int i=0;
    public Map<String, Object> ptcontrol(Point[] ptcont){
       // x = new double[ptcont.length];
      //  y = new double[ptcont.length];
        for (Point pt: ptcont) {
         // x[i]=pt.getCoordinates()[0];
       //   y[i]=pt.getCoordinates()[1];
          xtt.add(pt.getCoordinates()[0]);
          ytt.add(pt.getCoordinates()[1]);
          i=i+1;
        }

       // hash.put("xarray", x);
       // hash.put("yarray", y);
        hash.put("xarray", xtt);
        hash.put("yarray", ytt);

        return hash;

    }

    // Display Control points on screen
    public void displaycp(double[] xp, double[] yp){
        xpp = new double[xp.length];
        ypp = new double[yp.length];
            for (int i = 0; i < xp.length; i++) {
                if(xp[i]!=0 && yp[i]!=0){
                xpp[i] = xp[i];
                ypp[i] = yp[i];
                }
            }
            dpt = 1;
    }
    // Display Control points on screen
    public void displaycpp(Vector<Double> xp, Vector<Double> yp){
        xpp = new double[xp.size()];
        ypp = new double[yp.size()];
        for (int i = 0; i < xp.size(); i++) {
                xpp[i] = xp.get(i);
                ypp[i] = yp.get(i);
        }
        dpt = 1;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
          xPos = event.getX();
          yPos = event.getY();
        listCoord.add(new Coord(xPos, yPos));
        mGestureDetector.onTouchEvent(event);
        // Just For Debug
        Trajectory traj = this.data();
        int sz = traj.getSize();
        pc = traj.pointsDeControle(coeff);
        int pt = pc.length;
      //  pctrl = this.ptcontrol(pc);
        Log.d("Trajectory class test", "trajectory size:" + sz);
        Log.d("Trajectory class test", "control point degree:" + coeff);
        Log.d("Trajectory class test", "point de controle size:" + pt);
        //Log.d("x", "point control: " + Arrays.toString(pctrl));
        Log.d("x", "xfloat: " + Arrays.toString(xfloat));
        Log.d("y", "yfloat" + Arrays.toString(yfloat));
       // String message = String.format(Locale.FRANCE,"Coordinates: (x = %.2f, y = %.2f,size=%d ))", xPos, yPos,listCoord.size() );
      //  Log.i(PlotActivity.DEBUGTAG, message);

        // Drawing trajectory on screen
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

        // Gestures , DoubleTap to jump
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
        pxPos = xPos;
        pyPos = yPos;
        yas = 0;
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

}
