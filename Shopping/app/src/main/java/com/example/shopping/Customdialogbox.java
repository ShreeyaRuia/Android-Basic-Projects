package com.example.shopping;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

public class Customdialogbox extends AppCompatDialogFragment {
    float rating;
    RatingBar ratingBar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view =inflater.inflate(R.layout.customdialog,null);
        ratingBar=view.findViewById(R.id.ratingBar);
        builder.setView(view).setTitle("RATING")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rating=ratingBar.getRating();
                Toast.makeText(view.getContext(), "RATING: "+rating, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });


        return builder.create();
    }
}
