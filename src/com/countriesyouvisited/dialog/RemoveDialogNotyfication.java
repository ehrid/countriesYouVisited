package com.countriesyouvisited.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.countriesyouvisited.R;

/**
 * @author horodysk
 */
public class RemoveDialogNotyfication extends Dialog implements android.view.View.OnClickListener {

    RemoveDialog _rootView;

    Button _yes;

    Button _no;

    TextView _title;

    private int _position;

    /***/
    public RemoveDialogNotyfication(Context context, int position) {
        super(context);
        _rootView = (RemoveDialog) context;
        _position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remove_notyfication);

        initializeItems();
        removeNotWantedItems();
        setButtonFont();
    }

    private void initializeItems() {
        _yes = (Button) findViewById(R.id.remove_notyfication_yes);
        _no = (Button) findViewById(R.id.remove_notyfication_no);
        _title = (TextView) findViewById(R.id.remove_notyfication_title);

        _yes.setOnClickListener(this);
        _no.setOnClickListener(this);
    }

    private void removeNotWantedItems() {
        findViewById(android.R.id.title).setVisibility(View.GONE);
    }

    private void setButtonFont() {
        Typeface font = Typeface.createFromAsset(_rootView.getAssets(), "fonts/YouRookMarbelous.ttf");
        _yes.setTypeface(font);
        _no.setTypeface(font);
        _title.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remove_notyfication_yes:
                _rootView.removeCountry(_position);
                dismiss();
                break;
            case R.id.remove_notyfication_no:
                dismiss();
                break;

        }
    }
}
