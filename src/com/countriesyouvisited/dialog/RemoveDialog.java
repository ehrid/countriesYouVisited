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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.DialogActivity;
import com.countriesyouvisited.database.CountryListViewAdapter;
import com.countriesyouvisited.database.DataBaseHandler;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class RemoveDialog extends DialogActivity implements OnItemClickListener {

    private TextView _noItems;

    private ListView _container;

    private Button _sortByContinent;

    private Button _sortByCountry;

    private Button _sortByRegion;

    private Button _sortByDate;

    private CountryListViewAdapter _adapter;

    DataBaseHandler _db;

    private List<VisitedRegionObject> _items = new ArrayList<VisitedRegionObject>();

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
        _items = _db.getAllVisitedRegions();
        Collections.sort(_items, new DateComaprator());

        _noItems = (TextView) inflated.findViewById(R.id.remove_no_items);

        _sortByContinent = (Button) inflated.findViewById(R.id.remove_sort_continent);
        _sortByCountry = (Button) inflated.findViewById(R.id.remove_sort_country);
        _sortByRegion = (Button) inflated.findViewById(R.id.remove_sort_region);
        _sortByDate = (Button) inflated.findViewById(R.id.remove_sort_date);

        _sortByContinent.setOnClickListener(this);
        _sortByCountry.setOnClickListener(this);
        _sortByRegion.setOnClickListener(this);
        _sortByDate.setOnClickListener(this);
    }

    private void setContainerAdapter() {
        _adapter = new CountryListViewAdapter(this, R.layout.dialog_remove_item, _items);
        _container.setAdapter(_adapter);
    }

    @Override
    protected void onClickAction(View v) {
        switch (v.getId()) {
            case R.id.remove_sort_continent:
                _adapter.sort(new ContinentComaprator());
                _adapter.notifyDataSetChanged();
                break;
            case R.id.remove_sort_country:
                _adapter.sort(new CountryComaprator());
                _adapter.notifyDataSetChanged();
                break;
            case R.id.remove_sort_region:
                _adapter.sort(new RegionComaprator());
                _adapter.notifyDataSetChanged();
                break;
            case R.id.remove_sort_date:
                _adapter.sort(new DateComaprator());
                _adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parentView, View arg0, int position, long arg3) {
        RemoveDialogNotyfication dialog = new RemoveDialogNotyfication(this, position);
        dialog.show();
    }

    /***/
    public void removeCountry(int position) {
        VisitedRegionObject visitedRegion = _items.remove(position);
        _adapter.notifyDataSetChanged();

        _db.deleteVisitedRegion(visitedRegion);

        displayDialogIfNoItems();

        String message = position + "/" + _items.size() + " Region " + visitedRegion.getRegion(_db).getName() + " (" + visitedRegion.getDate() +
            ") removed";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    /**
     * @author horodysk
     */
    private class ContinentComaprator implements Comparator<VisitedRegionObject> {

        public ContinentComaprator() {
            // not needed
        }

        @Override
        public int compare(VisitedRegionObject o1, VisitedRegionObject o2) {
            return o2.getContinent(_db).getName().compareTo(o1.getContinent(_db).getName());
        }
    }

    /**
     * @author horodysk
     */
    private class CountryComaprator implements Comparator<VisitedRegionObject> {

        public CountryComaprator() {
            // not needed
        }

        @Override
        public int compare(VisitedRegionObject o1, VisitedRegionObject o2) {
            return o2.getCountry(_db).getName().compareTo(o1.getCountry(_db).getName());
        }
    }

    /**
     * @author horodysk
     */
    private class RegionComaprator implements Comparator<VisitedRegionObject> {

        public RegionComaprator() {
            // not needed
        }

        @Override
        public int compare(VisitedRegionObject o1, VisitedRegionObject o2) {
            return o2.getRegion(_db).getName().compareTo(o1.getRegion(_db).getName());
        }
    }

    /**
     * @author horodysk
     */
    private class DateComaprator implements Comparator<VisitedRegionObject> {

        public DateComaprator() {
            // not needed
        }

        @Override
        public int compare(VisitedRegionObject o1, VisitedRegionObject o2) {
            int yearCompare = Integer.toString(o2.getYear()).compareTo(Integer.toString(o1.getYear()));
            int monthCompare = Integer.toString(o2.getMonth()).compareTo(Integer.toString(o1.getMonth()));
            return yearCompare == 0 ? monthCompare : yearCompare;
        }
    }

}
