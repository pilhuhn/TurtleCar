package de.bsd.turtlecar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

/**
 *  Draw the track and let the user run the race
 * @author Heiko W. Rupp
 */
public class StartActivity extends Activity {

    static final int MOVE_MS = 1000;
    List<Move> moveList = new ArrayList<Move>();
    ImageView carView;
    Integer dx = 0;
    Integer dy = 0;
    private static final int DELTA = 64;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.start);

        moveList.add(new Move(Moves.STRAIGHT,3));
        moveList.add(new Move(Moves.LEFT,1));
        moveList.add(new Move(Moves.STRAIGHT,2));
        moveList.add(new Move(Moves.RIGHT,1));
        moveList.add(new Move(Moves.STRAIGHT,1));
        moveList.add(new Move(Moves.RIGHT,1));
        moveList.add(new Move(Moves.STRAIGHT,1));
        moveList.add(new Move(Moves.LEFT,1));
        moveList.add(new Move(Moves.STRAIGHT,3));

        SampleView sampleView = (SampleView) findViewById(R.id.graph_view);
        sampleView.setVisibility(View.VISIBLE);
        sampleView.invalidate(); // force drawing

        carView = new ImageView(this);
        Drawable drawable = getResources().getDrawable(R.drawable.turtle_car);

        carView.setImageDrawable(drawable);
        ViewParent vp = sampleView.getParent();
        if (vp instanceof FrameLayout) {
            FrameLayout fl = (FrameLayout) vp;
            fl.addView(carView);
            Animation anim = new TranslateAnimation(0,0,0,320+32+64); // put car on start
            anim.setFillAfter(true);
            carView.startAnimation(anim);

        }

    }


    @SuppressWarnings("unused")
    public void run(View v) {

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setFillBefore(true);
        animationSet.setFillAfter(true);
        Animation.AnimationListener listener = new RunListener(this);
        animationSet.setAnimationListener(listener);

        Animation anim = new TranslateAnimation(0,0,0,320+32+64); // put car on start
        animationSet.addAnimation(anim);
        int x=240; // TODO get from Screen
        int y=320+32+64;
        int time =0;
        int cx = carView.getDrawable().getIntrinsicWidth() ;
        int cy = carView.getDrawable().getIntrinsicHeight() ;
        Heading heading = Heading.NORTH;

        for (Move move : moveList) {
            Log.i("StartActivity/Run" ,move + ", start x=" +x + ", start y=" + y);
            switch (move.getMove()) {
            case STRAIGHT:
                // TODO decompose into individual moves, so that we can
                //      pick up items or test for crashes
                compute(heading, move.getUnits());
                TranslateAnimation ta = new TranslateAnimation(0, dx, 0, dy);
                int deltaTime = move.getUnits() * MOVE_MS;
                ta.setDuration(deltaTime);
                ta.setStartOffset(time);
                ta.setInterpolator(new AccelerateDecelerateInterpolator());
                ta.setAnimationListener(listener);
                animationSet.addAnimation(ta);

                time += deltaTime;
                x += dx;
                y += dy;
                break;

            case LEFT:
                RotateAnimation rotateLeft = new RotateAnimation(0,-90,
                        x-cx-cx/4,y+cy/2);
                rotateLeft.setDuration(500);
                rotateLeft.setStartOffset(time);
                time+=500;
                rotateLeft.setAnimationListener(listener);
                animationSet.addAnimation(rotateLeft);
                heading = Heading.toLeft(heading);
                break;
            case RIGHT:
                RotateAnimation rotateRight = new RotateAnimation(0,90,
                        x-cx-cx/4,y+cy/2);
                rotateRight.setAnimationListener(listener);
                rotateRight.setDuration(500);
                rotateRight.setStartOffset(time);
                time+=500;
                animationSet.addAnimation(rotateRight);
                heading = Heading.toRight(heading);
                break;
            case BACKUP:
                break;
            case WAIT:
                time+= MOVE_MS;
                break;
            }
        }
        Log.i("StartActivity/Run" ,"After moves end x=" +x + ", end y=" + y);

        carView.startAnimation(animationSet);

    }


    @SuppressWarnings("unused")
    public void edit(View v) {
        Intent i = new Intent(this,EditRunActivity.class);
        startActivityForResult(i,1);
    }

    protected void onActivityResult(int requestCode, int resultCode,
             Intent data) {
        // TODO ...

        List<String> moves = data.getStringArrayListExtra("data");
        moveList = new ArrayList<Move>(moves.size());
        for (String move: moves ) {
            Moves m = Moves.valueOf(move.toUpperCase()); // TODO i18n of strings
            moveList.add(new Move(m,1));
        }
        Toast.makeText(this,"Moves loaded",Toast.LENGTH_LONG).show();
    }


    /**
     * Compute the next delta values for the car
     * @param heading In which direction does it drive?
     * @param units how many units does it drive
     * @todo make this return/modify grid coordinates and later translate to screen coordinates
     */
    private void compute(Heading heading, int units) {
        switch (heading) {

        case NORTH:
            dx=0;
            dy=-1 * units * DELTA;
            break;
        case EAST:
            dx= units * DELTA;
            dy=0;
            break;
        case SOUTH:
            dx=0;
            dy= units * DELTA;
            break;
        case WEST:
            dx = -1 * units * DELTA;
            dy = 0;
            break;
        }
    }
}