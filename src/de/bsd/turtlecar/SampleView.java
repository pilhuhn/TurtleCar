package de.bsd.turtlecar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
*  TODO: Document this
* @author Heiko W. Rupp
*/
class SampleView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap car;


    public SampleView(Context context) {
        super(context);
        init();
    }

    public SampleView(Context context,AttributeSet attributeSet) {
        super(context,attributeSet);
        init();
    }

    void init()
    {
        car = BitmapFactory.decodeResource(getResources(),R.drawable.turtle_car);
    }

    protected void onDraw(Canvas canvas) { // TODO paint grid + Start + End
//        mPaint.setStrokeWidth(2);
//        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setPathEffect(new DashPathEffect(new float[]{4,2},0));
//        canvas.drawLine(20, 20, 100, 100, mPaint);
//        canvas.drawLine(100, 100, 200, 20, mPaint);
//        canvas.drawLine(200, 20, 300, 100, mPaint);
//        canvas.drawBitmap(car, new Matrix(),null);
//
//        mPaint.setColor(Color.GREEN);
//        mPaint.setPathEffect(null);
//        canvas.drawCircle(100, 100, 5, mPaint);
//        canvas.drawCircle(200,20,5,mPaint);
////            canvas.drawLine(20, 100, 100, 20, mPaint);

        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[]{4,2},0));

        for (int i = 0; i < canvas.getHeight(); i+= 64) {
            canvas.drawLine(0,i,canvas.getWidth(),i,mPaint);
        }

        mPaint.setColor(Color.BLUE);
        for (int i = 0; i < canvas.getWidth(); i+= 64) {
            canvas.drawLine(i,0,i,canvas.getHeight(),mPaint);
        }

    }
}
