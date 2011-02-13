package de.bsd.turtlecar;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

/**
 * Get notified on Animations - not sure yet how this is useful
 * @author Heiko W. Rupp
 */
public class RunListener implements Animation.AnimationListener {

    private Context context;

    public RunListener(Context context) {
        this.context = context;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.i("RunListener", "Started animation: " + animation);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.i("RunListener","Ended animation: " + animation);
        if (animation instanceof AnimationSet) {
            AnimationSet animationSet = (AnimationSet) animation;
            // TODO check x,y of car
            // y < 32 -> crossed finish
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO: Customise this generated block
    }
}
