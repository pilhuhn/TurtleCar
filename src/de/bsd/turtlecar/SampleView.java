package de.bsd.turtlecar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Draw the background
 * @author Heiko W. Rupp
 */
class SampleView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public SampleView(Context context) {
        super(context);
    }

    public SampleView(Context context,AttributeSet attributeSet) {
        super(context,attributeSet);
    }


    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[]{4,2},0));

        for (int i = 32; i < canvas.getHeight(); i+= 64) {
            canvas.drawLine(0,i,canvas.getWidth(),i,mPaint);
            if (i==32 || i==416)
                canvas.drawLine(0,i+1,canvas.getWidth(),i+1,mPaint);
        }

        mPaint.setColor(Color.BLUE);
        for (int i = 0; i < canvas.getWidth(); i+= 64) {
            canvas.drawLine(i,0,i,canvas.getHeight(),mPaint);
        }

    }
}
