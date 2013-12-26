package com.countriesyouvisited.dialog;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.DialogActivity;
import com.countriesyouvisited.database.CountryObject;
import com.countriesyouvisited.database.DataBaseHandler;

/**
 * @author horodysk
 */
public class AddDialog extends DialogActivity {

    Spinner _name;

    DatePicker _date;

    Button _add;

    DataBaseHandler _db;

    @Override
    protected void onCreateDialog(Bundle savedInstanceState) {
        View inflated = initializeDialogBody();

        initializeDialogItems(inflated);
        setButtonFont();
    }

    private View initializeDialogBody() {
        ViewStub stub = (ViewStub) findViewById(R.id.dialog_stub);
        stub.setLayoutResource(R.layout.dialog_add);
        View inflated = stub.inflate();
        return inflated;
    }

    private void initializeDialogItems(View inflated) {
        _name = (Spinner) inflated.findViewById(R.id.add_name);
        _date = (DatePicker) inflated.findViewById(R.id.add_date);
        _add = (Button) inflated.findViewById(R.id.add_button);

        _add.setOnClickListener(this);

        _db = new DataBaseHandler(this);
    }

    private void setButtonFont() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/YouRookMarbelous.ttf");
        _add.setTypeface(font);
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
        String date = _date.getMonth() + "/" + _date.getYear();
        CountryObject country = new CountryObject(name, date);
        _db.addVisitedCountry(country);

        Toast.makeText(getApplicationContext(), "Country " + name + " added", Toast.LENGTH_LONG).show();
    }

}
