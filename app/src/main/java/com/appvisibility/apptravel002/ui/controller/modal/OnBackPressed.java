package com.appvisibility.apptravel002.ui.controller.modal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import com.appvisibility.apptravel002.R;
import android.os.Bundle;

import com.appvisibility.apptravel002.ui.controller.V_03;

// https://stackoverflow.com/questions/7977392/android-dialogfragment-vs-dialog
// https://developer.android.com/reference/android/app/DialogFragment.html
public class OnBackPressed extends DialogFragment {

    public static final String ARG_TITLE = "OnBackPressed.Title";
    public static final String ARG_MESSAGE = "OnBackPressed.Message";

    public OnBackPressed() {
    }

    public static DialogFragment newInstance(String title, String message) {
        OnBackPressed fragment = new OnBackPressed();
        Bundle bundle = new Bundle();
        bundle.putString(OnBackPressed.ARG_TITLE, title);
        bundle.putString(OnBackPressed.ARG_MESSAGE, message);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = args.getString(ARG_TITLE);
        String message = args.getString(ARG_MESSAGE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
            .setIcon(R.drawable.ico_coche_naranja)
            .setMessage(message)
            .setNegativeButton(android.R.string.no, null)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    V_03.v03_atras.performClick();
                }
            });
        return builder.create();
    }
}
