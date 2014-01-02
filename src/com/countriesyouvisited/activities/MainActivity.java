package com.countriesyouvisited.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.countriesyouvisited.R;
import com.countriesyouvisited.database.DataBaseHandler;
import com.countriesyouvisited.database.objects.VisitedRegionObject;
import com.countriesyouvisited.dialog.AddDialog;
import com.countriesyouvisited.dialog.ExitDialogNotyfication;
import com.countriesyouvisited.dialog.PlanDialog;
import com.countriesyouvisited.dialog.RemoveDialog;
import com.countriesyouvisited.dialog.StatsDialog;

/**
 * @author horodysk
 */
public class MainActivity extends Activity implements OnClickListener {

    private Button _plan;

    private Button _add;

    private Button _remove;

    private Button _stats;

    private Button _exit;

    private RelativeLayout _container;

    private DataBaseHandler _db;

    private ScratchMapImageView _map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initializeItems();
        setListeners();
        addScratchMap();
    }

    private void initializeItems() {
        _plan = (Button) findViewById(R.id.button_plan);
        _add = (Button) findViewById(R.id.button_add);
        _remove = (Button) findViewById(R.id.button_remove);
        _stats = (Button) findViewById(R.id.button_stats);
        _exit = (Button) findViewById(R.id.button_exit);

        _container = (RelativeLayout) findViewById(R.id.map_container);
    }

    private void setListeners() {
        _plan.setOnClickListener(this);
        _add.setOnClickListener(this);
        _remove.setOnClickListener(this);
        _stats.setOnClickListener(this);
        _exit.setOnClickListener(this);
    }

    private void addScratchMap() {
        _db = new DataBaseHandler(this);
        List<VisitedRegionObject> visited = _db.getAllVisitedRegions();
        addFullMap(visited);
    }

    private void addFullMap(List<VisitedRegionObject> visited) {
        _map = new ScratchMapImageView(this, visited);
        _map.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        _map.setScaleType(ScaleType.MATRIX);
        _container.addView(_map);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshMap();
    }

    private void refreshMap() {
        List<VisitedRegionObject> visited = _db.getAllVisitedRegions();
        _map.refreshImage(visited);
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
                ExitDialogNotyfication dialog = new ExitDialogNotyfication(this);
                dialog.show();
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
