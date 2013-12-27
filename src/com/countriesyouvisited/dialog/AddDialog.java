package com.countriesyouvisited.dialog;

import java.util.Calendar;
import java.util.Locale;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.DialogActivity;
import com.countriesyouvisited.database.CountryObject;
import com.countriesyouvisited.database.DataBaseHandler;
import com.countriesyouvisited.wheel.DateArrayAdapter;
import com.countriesyouvisited.wheel.DateNumericAdapter;
import com.countriesyouvisited.wheel.WheelView;

/**
 * @author horodysk
 */
public class AddDialog extends DialogActivity implements OnItemSelectedListener {

    private static final String months[] = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December" };

    private Typeface _font;

    private Spinner _name;

    private Button _add;

    private DataBaseHandler _db;

    private Calendar _calendar;

    private WheelView _month;

    private WheelView _year;

    private int NoOfYears = 40;

    @Override
    protected void onCreateDialog(Bundle savedInstanceState) {
        View inflated = initializeDialogBody();

        initializeDialogItems(inflated);
        setButtonFont();

        initializeMonthsWheel();
        initializeYearsWheel();
    }

    private View initializeDialogBody() {
        ViewStub stub = (ViewStub) findViewById(R.id.dialog_stub);
        stub.setLayoutResource(R.layout.dialog_add);
        View inflated = stub.inflate();
        return inflated;
    }

    private void initializeDialogItems(View inflated) {
        _name = (Spinner) inflated.findViewById(R.id.add_name);
        _add = (Button) inflated.findViewById(R.id.add_button);

        _name.setOnItemSelectedListener(this);
        _add.setOnClickListener(this);

        _db = new DataBaseHandler(this);

        _month = (WheelView) findViewById(R.id.add_months);
        _year = (WheelView) findViewById(R.id.add_years);
        _calendar = Calendar.getInstance(Locale.US);
    }

    private void setButtonFont() {
        _font = Typeface.createFromAsset(getAssets(), "fonts/YouRookMarbelous.ttf");
        _add.setTypeface(_font);
    }

    private void initializeMonthsWheel() {
        int curMonth = _calendar.get(Calendar.MONTH);
        _month.setViewAdapter(new DateArrayAdapter(this, months, curMonth));
        _month.setCurrentItem(curMonth);
    }

    private void initializeYearsWheel() {
        Calendar cal = Calendar.getInstance();
        int curYear = _calendar.get(Calendar.YEAR);
        int Year = cal.get(Calendar.YEAR);

        _year.setViewAdapter(new DateNumericAdapter(this, Year - NoOfYears, Year + NoOfYears, NoOfYears));
        _year.setCurrentItem(curYear - (Year - NoOfYears));
    }

    @Override
    protected void onClickAction(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                finish();
                addCountry();
                break;
        }
    }

    private void addCountry() {
        String name = (String) _name.getSelectedItem();
        String date = _month.getCurrentItem() + "/" + (_calendar.get(Calendar.YEAR) + (_year.getCurrentItem() - NoOfYears));
        CountryObject country = new CountryObject(name, date);
        _db.addVisitedCountry(country);

        Toast.makeText(getApplicationContext(), "Country " + name + " (" + date + ") added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parentView, View arg1, int arg2, long arg3) {
        TextView childAt = (TextView) parentView.getChildAt(0);
        childAt.setTextColor(Color.WHITE);
        childAt.setTypeface(_font);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // not needed
    }

}
