package com.countriesyouvisited.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.countriesyouvisited.R;
import com.countriesyouvisited.dialog.AddDialog;
import com.countriesyouvisited.dialog.PlanDialog;
import com.countriesyouvisited.dialog.RemoveDialog;
import com.countriesyouvisited.dialog.StatsDialog;

/**
 * @author horodysk
 */
public class MainActivity extends Activity implements OnClickListener {

    Button plan;

    Button add;

    Button remove;

    Button stats;

    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initializeButtons();
        setListeners();
    }

    private void initializeButtons() {
        plan = (Button) findViewById(R.id.button_plan);
        add = (Button) findViewById(R.id.button_add);
        remove = (Button) findViewById(R.id.button_remove);
        stats = (Button) findViewById(R.id.button_stats);
        exit = (Button) findViewById(R.id.button_exit);
    }

    private void setListeners() {
        plan.setOnClickListener(this);
        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        stats.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_plan:
                startPlanActivity();
                break;
            case R.id.button_add:
                startAddActivity();
                break;
            case R.id.button_remove:
                startRemoveActivity();
                break;
            case R.id.button_stats:
                startStatsActivity();
                break;
            case R.id.button_exit:
                finish();
                break;
        }
    }

    private void startPlanActivity() {
        Intent intent = new Intent(MainActivity.this, PlanDialog.class);
        startActivity(intent);
    }

    private void startAddActivity() {
        Intent intent = new Intent(MainActivity.this, AddDialog.class);
        startActivity(intent);
    }

    private void startRemoveActivity() {
        Intent intent = new Intent(MainActivity.this, RemoveDialog.class);
        startActivity(intent);
    }

    private void startStatsActivity() {
        Intent intent = new Intent(MainActivity.this, StatsDialog.class);
        startActivity(intent);
    }

}
