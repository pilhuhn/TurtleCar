package de.bsd.turtlecar;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;

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
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO: Customise this generated block
    }
}
