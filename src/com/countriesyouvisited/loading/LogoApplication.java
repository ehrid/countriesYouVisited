package com.countriesyouvisited.loading;

import android.content.Intent;
import android.os.Bundle;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.MapActivity;

/**
 * @author horodysk
 */
public class LogoApplication extends SplashscreanActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLogo(R.drawable.logo_ehrid);
    }

    @Override
    void startActivity() {
        Intent intent = new Intent(LogoApplication.this, MapActivity.class);
        startActivity(intent);
    }

}
