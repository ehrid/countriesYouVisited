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
import com.countriesyouvisited.database.DataBaseHandler;
import com.countriesyouvisited.database.objects.VisitedRegionObject;

/**
 * @author horodysk
 */
public class RemoveDialog extends DialogActivity implements OnItemClickListener {

    private TextView _noItems;

    private ListView _container;

    private CountryListViewAdapter _adapter;

    private DataBaseHandler _db;

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
    private class CountryObjectComparator implements Comparator<VisitedRegionObject> {

        public CountryObjectComparator() {
            // not needed
        }

        // TODO replace sorting to sorting by column

        @Override
        public int compare(VisitedRegionObject o1, VisitedRegionObject o2) {
            int yearCompare = Integer.toString(o2.getYear()).compareTo(Integer.toString(o1.getYear()));
            int monthCompare = Integer.toString(o2.getMonth()).compareTo(Integer.toString(o1.getMonth()));
            return yearCompare == 0 ? monthCompare : yearCompare;
        }
    }

}
