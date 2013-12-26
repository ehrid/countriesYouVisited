package com.countriesyouvisited.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.countriesyouvisited.R;
import com.countriesyouvisited.activities.DialogActivity;

/**
 * @author horodysk
 */
public class AddDialog extends DialogActivity {

    @Override
    protected void onCreateDialog(Bundle savedInstanceState) {
        View inflated = initializeDialogBody();
    }

    private View initializeDialogBody() {
        ViewStub stub = (ViewStub) findViewById(R.id.dialog_stub);
        stub.setLayoutResource(R.layout.dialog_add);
        View inflated = stub.inflate();
        return inflated;
    }

    @Override
    protected void onClickAction(View v) {
        //
    }

}
