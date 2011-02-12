package de.bsd.turtlecar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
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

/**
 *  Draw the track and let the user run the race
 * @author Heiko W. Rupp
 */
public class StartActivity extends Activity {

    List<Move> moveList = new ArrayList<Move>();
    ImageView carView;
    Integer dx = 0;
    Integer dy = 0;
    private static final int DELTA = 64;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.start);

        moveList.add(new Move(Moves.STRAIT,3));
        moveList.add(new Move(Moves.LEFT,1));
        moveList.add(new Move(Moves.STRAIT,2));
        moveList.add(new Move(Moves.RIGHT,1));
        moveList.add(new Move(Moves.STRAIT,3));

        SampleView sampleView = (SampleView) findViewById(R.id.graph_view);
        sampleView.setVisibility(View.VISIBLE);
        sampleView.invalidate(); // force drawing

        carView = new ImageView(this);
        carView.setImageDrawable(getResources().getDrawable(R.drawable.turtle_car));
        ViewParent vp = sampleView.getParent();
        if (vp instanceof FrameLayout) {
            FrameLayout fl = (FrameLayout) vp;
            fl.addView(carView);
            Animation anim = new TranslateAnimation(0,0,0,320); // put car on start
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

        Animation anim = new TranslateAnimation(0,0,0,320); // put car on start
        animationSet.addAnimation(anim);
        int x=240; // TODO get from Screen
        int y=320;
        int time =0;
        int cx = carView.getDrawable().getIntrinsicWidth() ;
        int cy = carView.getDrawable().getIntrinsicHeight() ;
        Heading heading = Heading.NORTH;

        for (Move move : moveList) {
            Log.i("StartActivity/Run" ,move + ", start x=" +x + ", start y=" + y);
            switch (move.getMove()) {
            case STRAIT:
                compute(heading, move.getUnits());
                TranslateAnimation ta = new TranslateAnimation(0, dx, 0, dy);
                int deltaTime = move.getUnits() * 1000;
                ta.setDuration(deltaTime);
                ta.setStartOffset(time);
                ta.setInterpolator(new AccelerateDecelerateInterpolator());
                animationSet.addAnimation(ta);
                time += deltaTime;
                x += dx;
                y += dy;
                break;

            case LEFT:
                RotateAnimation rotateLeft = new RotateAnimation(0,-90,
                        x-cx,y+cy/2);
                rotateLeft.setDuration(2000);
                rotateLeft.setStartOffset(time);
                time+=2000;
                animationSet.addAnimation(rotateLeft);
                heading = Heading.toLeft(heading);
                break;
            case RIGHT:
                RotateAnimation rotateRight = new RotateAnimation(0,90,
                        x-cx,y+cy/2);
                rotateRight.setDuration(2000);
                rotateRight.setStartOffset(time);
                time+=2000;
                animationSet.addAnimation(rotateRight);
                heading = Heading.toRight(heading);
                break;
            case BACKUP:
                break;
            case WAIT:
                time+=1000;
                break;
            }
        }

/*

        TranslateAnimation a = new TranslateAnimation(
                Animation.ABSOLUTE,100-cx, Animation.ABSOLUTE,100-cx,
                Animation.ABSOLUTE,200-cy, Animation.ABSOLUTE,200-cy);
        a.setDuration(1000);
        animationSet.addAnimation(a);

        x= 200;
        y= 200;


        a = new TranslateAnimation(0, 0, 0, -50);
        a.setStartOffset(1000);
        a.setDuration(1000);
        animationSet.addAnimation(a);

        y=y-50;

        RotateAnimation r;
        r = new RotateAnimation(0, 90,// x+cx,y+cy); // rechts ; Achtung, andere Ecke
                Animation.RELATIVE_TO_PARENT,0.5f,
                Animation.RELATIVE_TO_PARENT,0.5f);

//        RotateAnimation r = new RotateAnimation(0f, -90f,x+cx,y+cy); // links


        r.setStartOffset(2000);
        r.setDuration(1000);
        animationSet.addAnimation(r);

        a = new TranslateAnimation(0, -50, 0, 0);
        x+=-50;
        a.setDuration(1000);
        a.setStartOffset(3000);
        animationSet.addAnimation(a);

        r = new RotateAnimation(0, 90,x+cx,y+cy); // rechts ; Achtung, andere Ecke
//                Animation.RELATIVE_TO_SELF,0.1f,
//                Animation.RELATIVE_TO_SELF,0.1f);
        r.setStartOffset(4000);
        r.setDuration(1000);
        animationSet.addAnimation(r);

        a = new TranslateAnimation(0, 0, 0, -150);
        y+=-150;
        a.setDuration(1000);
        a.setStartOffset(5000);
        a.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.addAnimation(a);

*/
        carView.startAnimation(animationSet);
        print(carView);

    }

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

    private void print(View v) {
        Log.i("CarView","left="+ v.getLeft() +", top=" + v.getTop() + ", right=" + v.getRight() + ", bottom=" + v.getBottom());
    }
}