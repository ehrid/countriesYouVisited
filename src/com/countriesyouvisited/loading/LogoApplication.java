package com.countriesyouvisited.loading;

import android.content.Intent;
import android.os.Bundle;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.MainActivity;

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
        Intent intent = new Intent(LogoApplication.this, MainActivity.class);
        startActivity(intent);
    }

}
