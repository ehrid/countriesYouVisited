package com.countriesyouvisited.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.countriesyouvisited.R;

/**
 * @author horodysk
 */
public abstract class DialogActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_base);
        initializeBaseItems();

        onCreateDialog(savedInstanceState);
    }

    protected abstract void onCreateDialog(Bundle savedInstanceState);

    protected void initializeBaseItems() {
        // close button
        View close = findViewById(R.id.button_close);
        close.setOnClickListener(this);

        // unclickable body
        View body = findViewById(R.id.dialog_frame);
        body.setOnClickListener(this);

        // exit dialog upon clicking outside body
        View background = findViewById(R.id.dialog_background);
        background.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onClickAction(v);
        switch (v.getId()) {
            case R.id.button_close:
                finish();
                break;
            case R.id.dialog_frame:
                // needs to be above R.id.dialog_background
                break;
            case R.id.dialog_background:
                finish();
                break;
        }
    }

    protected abstract void onClickAction(View v);
}
