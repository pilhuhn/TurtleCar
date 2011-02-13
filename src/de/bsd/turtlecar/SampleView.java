package de.bsd.turtlecar;

import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    static final int BASE_OFFSET_Y = 32;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Context context;
    Board board;
    Bitmap wallMap,goldMap,fuelMap;

    @SuppressWarnings("unused")
    public SampleView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    @SuppressWarnings("unused")
    public SampleView(Context context,AttributeSet attributeSet) {
        super(context,attributeSet);
        this.context = context;
        init();
    }

    private void init() {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open("1.xml");
            board = new Board();
            board.loadFromXmlInputStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();  // TODO: Customise this generated block
        }
        wallMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.wall);
        goldMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.gold);
        fuelMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fuel);


    }

    protected void onDraw(Canvas canvas) {

        mPaint.setColor(Color.LTGRAY);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[]{4,2},0));

        for (int i = BASE_OFFSET_Y; i < canvas.getHeight(); i+= 64) {
            canvas.drawLine(0,i,canvas.getWidth(),i,mPaint);
            if (i== BASE_OFFSET_Y || i==416)
                canvas.drawLine(0,i+1,canvas.getWidth(),i+1,mPaint);
        }

        mPaint.setColor(Color.BLUE);
        for (int i = 0; i < canvas.getWidth(); i+= 64) {
            canvas.drawLine(i,0,i,canvas.getHeight(),mPaint);
        }

        /** Now draw the board content */
        if (board!=null) {
            for (Board.Item item : board.items) {
                Bitmap bitmap;
                switch (item.itemType) {

                case WALL:
                    bitmap=wallMap;
                    break;
                case GOLD:
                    bitmap=goldMap;
                    break;
                case FUEL:
                    bitmap=fuelMap;
                    break;
                default:
                    bitmap = null;
                }

                // offset within the grid
                int dx = (64-bitmap.getWidth()) / 2;
                int dy = (64-bitmap.getHeight() / 2);

                canvas.drawBitmap(bitmap,item.x*64+dx,(item.y+1)*64+BASE_OFFSET_Y-dy,null);
            }
        }

    }
}
