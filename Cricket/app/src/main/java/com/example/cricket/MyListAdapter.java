package com.example.cricket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] country;
    private final Integer[] imageid;
    public MyListAdapter( Activity context, String[] country,Integer[] imageid) {
        super(context, R.layout.listmain,country);
        this.context = context;
        this.country=country;
        this.imageid=imageid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listmain, null,true);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        titleText.setText(country[position]);
        imageView.setImageResource(imageid[position]);
        return rowView;
    }
}
