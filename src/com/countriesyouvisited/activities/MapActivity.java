package com.countriesyouvisited.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.countriesyouvisited.R;
import com.countriesyouvisited.dialog.AddDialog;
import com.countriesyouvisited.dialog.PlanDialog;
import com.countriesyouvisited.dialog.RemoveDialog;
import com.countriesyouvisited.dialog.StatsDialog;

/**
 * @author horodysk
 */
public class MapActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                startPlanActivity();
                break;
            case 1:
                startAddActivity();
                break;
            case 2:
                startRemoveActivity();
                break;
            case 3:
                startStatsActivity();
                break;
            case 4:
                finish();
                break;
        }
    }

    private void startPlanActivity() {
        Intent intent = new Intent(MapActivity.this, PlanDialog.class);
        startActivity(intent);
    }

    private void startAddActivity() {
        Intent intent = new Intent(MapActivity.this, AddDialog.class);
        startActivity(intent);
    }

    private void startRemoveActivity() {
        Intent intent = new Intent(MapActivity.this, RemoveDialog.class);
        startActivity(intent);
    }

    private void startStatsActivity() {
        Intent intent = new Intent(MapActivity.this, StatsDialog.class);
        startActivity(intent);
    }

}
