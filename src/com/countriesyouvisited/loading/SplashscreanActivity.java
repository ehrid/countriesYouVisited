package com.countriesyouvisited.loading;

import com.countriesyouvisited.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

/**
 * @author horodysk
 */
public abstract class SplashscreanActivity extends Activity {

    private static final int _TIME = 2 * 1000;

    private ImageView _logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        _logo = (ImageView) findViewById(R.id.splashscreen_logo);
        startNextActivityAfterTime();
    }

    protected void setLogo(int drawable) {
        _logo.setImageDrawable(getResources().getDrawable(drawable));
    }

    private void startNextActivityAfterTime() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity();
                finish();
                changeAnimation();
            }

            private void changeAnimation() {
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }

        }, _TIME);
    }

    abstract void startActivity();

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

}
