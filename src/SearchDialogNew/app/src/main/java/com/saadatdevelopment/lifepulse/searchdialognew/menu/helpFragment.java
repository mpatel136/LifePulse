package com.saadatdevelopment.lifepulse.searchdialognew.menu;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saadatdevelopment.lifepulse.searchdialognew.R;

/**
 * A fragment to show help for the app
 */
public class helpFragment extends DialogFragment {

    /**
     * Default constructor
     */
    public helpFragment()
    {

    }

    /**
     * Inflates the view with the corresponding XML file
     * @param inflater Layout to be put with the view
     * @param container Group of views
     * @param savedInstanceState Collection of key value pairs
     * @return The view of the help fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    /**
     * Creates the dialog to show the about section
     * @param savedInstanceState Collection of key value pairs
     * @return A dialog box
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Create a alert dialog builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //Set the message in the dialog
        builder.setMessage(R.string.helpText)
                //Create a button to the dialog
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    //Close the dialog on button press
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        //Return the dialog
        return builder.create();
    }

}
