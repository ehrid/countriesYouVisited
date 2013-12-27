package com.countriesyouvisited.database;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.countriesyouvisited.R;

/**
 * @author horodysk
 */
public class CountryListViewAdapter extends ArrayAdapter<CountryObject> {

    private int _resource;

    /***/
    public CountryListViewAdapter(Context context, int resource, List<CountryObject> items) {
        super(context, resource, items);
        _resource = resource;
    }

    /***/
    public CountryObject getEvent(int position) {
        return getItem(position);
    }

    @Override
    public View getView(int position, View convertView, @SuppressWarnings("unused") ViewGroup parent) {
        LinearLayout inventoryItemView = inflateTheView(convertView);
        initializeVIewItems(position, inventoryItemView);

        return inventoryItemView;
    }

    private LinearLayout inflateTheView(View convertView) {
        LinearLayout inventoryItemView;
        if (convertView == null) {
            inventoryItemView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(_resource, inventoryItemView, true);
        }
        else {
            inventoryItemView = (LinearLayout) convertView;
        }
        return inventoryItemView;
    }

    private void initializeVIewItems(int position, LinearLayout inventoryItemView) {
        CountryObject item = getItem(position);

        TextView date = (TextView) inventoryItemView.findViewById(R.id.remove_item_date);
        TextView name = (TextView) inventoryItemView.findViewById(R.id.remove_item_name);

        date.setText(item.getDate());
        name.setText(item.getName());

        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/YouRookMarbelous.ttf");
        date.setTypeface(font);
        name.setTypeface(font);

    }

}