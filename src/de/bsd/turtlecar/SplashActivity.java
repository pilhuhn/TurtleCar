package de.bsd.turtlecar;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class SplashActivity extends Activity
{
    static final long SPLASH_MIN_SECONDS = 1 * 500L;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new ForwardAction(this).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();




    }

    private class ForwardAction extends AsyncTask<Void,Void,Void> {

        Context context;
        long time;

        private ForwardAction(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            time = System.currentTimeMillis();
        }

        @Override
        protected Void doInBackground(Void... params) {

            ////////////// do the initialization work here vvvvvvvv


            ////////////// do the initialization work here ^^^^^^^^


            long diffTime = System.currentTimeMillis() - time;

            if (diffTime < SPLASH_MIN_SECONDS) {
                try {
                    // Display slash a little longer
                    Thread.sleep(SPLASH_MIN_SECONDS - diffTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



            // Forward to the main activity
            Intent i = new Intent(context,StartActivity.class);
            startActivity(i);

        }
    }
}
