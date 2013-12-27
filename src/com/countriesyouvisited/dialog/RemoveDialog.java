package com.countriesyouvisited.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.DialogActivity;
import com.countriesyouvisited.database.CountryListViewAdapter;
import com.countriesyouvisited.database.CountryObject;
import com.countriesyouvisited.database.DataBaseHandler;

/**
 * @author horodysk
 */
public class RemoveDialog extends DialogActivity implements OnItemClickListener {

    private TextView _noItems;

    private ListView _container;

    private CountryListViewAdapter _adapter;

    private DataBaseHandler _db;

    private List<CountryObject> _items = new ArrayList<CountryObject>();

    @Override
    protected void onCreateDialog(Bundle savedInstanceState) {
        View inflated = initializeDialogBody();

        initializeItems(inflated);
        setContainerAdapter();

        displayDialogIfNoItems();
    }

    private void displayDialogIfNoItems() {
        if (_items.size() == 0) {
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/YouRookMarbelous.ttf");
            _noItems.setTypeface(font);
            _noItems.setVisibility(View.VISIBLE);
        }
    }

    private View initializeDialogBody() {
        ViewStub stub = (ViewStub) findViewById(R.id.dialog_stub);
        stub.setLayoutResource(R.layout.dialog_remove);
        View inflated = stub.inflate();
        return inflated;
    }

    private void initializeItems(View inflated) {
        _container = (ListView) inflated.findViewById(R.id.remove_list);
        _container.setOnItemClickListener(this);

        _db = new DataBaseHandler(this);
        _items = _db.getAllVisitedCountries();
        Collections.sort(_items, new CountryObjectComparator());

        _noItems = (TextView) inflated.findViewById(R.id.remove_no_items);
    }

    private void setContainerAdapter() {
        _adapter = new CountryListViewAdapter(this, R.layout.dialog_remove_item, _items);
        _container.setAdapter(_adapter);
    }

    @Override
    protected void onClickAction(View v) {
        //
    }

    @Override
    public void onItemClick(AdapterView<?> parentView, View arg0, int position, long arg3) {
        RemoveDialogNotyfication dialog = new RemoveDialogNotyfication(this, position);
        dialog.show();
    }

    /***/
    public void removeCountry(int position) {
        CountryObject country = _items.remove(position);
        _adapter.notifyDataSetChanged();

        _db.deleteVisitedCountry(country);

        displayDialogIfNoItems();

        String message = position + "/" + _items.size() + " Country " + country.getName() + " (" + country.getDate() + ") removed";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    /**
     * @author horodysk
     */
    private class CountryObjectComparator implements Comparator<CountryObject> {

        public CountryObjectComparator() {
            // not needed
        }

        @Override
        public int compare(CountryObject o1, CountryObject o2) {
            int dateCompare = o1.getDate().compareTo(o2.getDate());
            int nameCompare = o1.getName().compareTo(o2.getName());
            return dateCompare == 0 ? nameCompare : dateCompare;
        }
    }

}
