package com.countriesyouvisited.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.MainActivity;

/**
 * @author horodysk
 */
public class ExitDialogNotyfication extends Dialog implements android.view.View.OnClickListener {

    private MainActivity _rootView;

    private Button _yes;

    private Button _no;

    private TextView _title;

    /***/
    public ExitDialogNotyfication(Context context) {
        super(context);
        _rootView = (MainActivity) context;
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
                dismiss();
                _rootView.finish();
                break;
            case R.id.remove_notyfication_no:
                dismiss();
                break;

        }
    }
}
